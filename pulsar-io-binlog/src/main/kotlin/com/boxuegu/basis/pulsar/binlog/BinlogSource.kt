package com.boxuegu.basis.pulsar.binlog

import com.github.shyiko.mysql.binlog.BinaryLogClient
import com.github.shyiko.mysql.binlog.GtidSet
import com.github.shyiko.mysql.binlog.event.*
import com.github.shyiko.mysql.binlog.event.deserialization.*
import org.apache.pulsar.io.core.PushSource
import org.apache.pulsar.io.core.SourceContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.sql.Connection
import java.sql.DriverManager
import java.time.Instant
import java.util.*
import java.util.concurrent.*

@Suppress("unused")
class BinlogSource : PushSource<ByteArray>(), BinaryLogClient.EventListener {
    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(BinlogSource::class.java)
        private const val BINLOG_OFFSET_STATE_KEY = "BINLOG_OFFSET"
    }

    private lateinit var sourceContext: SourceContext
    private lateinit var sourceConfig: BinlogSourceConfig
    private lateinit var connection: Connection
    private lateinit var binlogClient: BinaryLogClient
    private lateinit var connectFuture: Future<Unit>
    private lateinit var nextOffset: BinlogOffset
    private lateinit var dbMatchers: List<String>
    private val pendingEvents: MutableList<Event> = LinkedList()

    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    override fun open(config: Map<String, Any?>, context: SourceContext) {

        sourceContext = context
        sourceConfig = config.toBinlogSourceConfig().also { it.validate() }
        dbMatchers = sourceConfig.databases.split(',')

        // Create jdbc connection
        Class.forName("com.mysql.cj.jdbc.Driver")
        connection = DriverManager.getConnection(
            "jdbc:mysql://${sourceConfig.hostname}:${sourceConfig.port}" +
                    "?user=${sourceConfig.username}&password=${sourceConfig.password}" +
                    "&useSSL=false&serverTimezone=${sourceConfig.timezone}&useUnicode=true&characterEncoding=utf8" +
                    "&yearIsDateType=false&tinyInt1isBit=false&disableMariaDbDriver=true",
        ).also { it.isReadOnly = true } // Readonly connection

        sourceContext.putState(BINLOG_OFFSET_STATE_KEY, null)
        // Load server status
        nextOffset = sourceContext.getBinlogOffset(BINLOG_OFFSET_STATE_KEY)?.also {
            LOGGER.info("Loaded offset from state: $it")
        } ?: sourceConfig.getBinlogOffset()?.also {
            LOGGER.info("Loaded offset from config: $it")
            sourceContext.putBinlogOffset(BINLOG_OFFSET_STATE_KEY, it)
        } ?: connection.loadBinlogOffset().also {
            LOGGER.info("Loaded offset from mysql: $it")
            sourceContext.putBinlogOffset(BINLOG_OFFSET_STATE_KEY, it)
        }

        connection.close()

        // Create binlog client
        val tableMaps: MutableMap<Long, TableMapEventData> = LRUCache(100, 0.75f, 10000)
        binlogClient = BinaryLogClient(
            sourceConfig.hostname,
            sourceConfig.port,
            sourceConfig.username,
            sourceConfig.password,
        ).also {
            it.setEventDeserializer(object : EventDeserializer(
                EventHeaderV4Deserializer(),
                NullEventDataDeserializer(),
                mutableMapOf(
                    EventType.FORMAT_DESCRIPTION to FormatDescriptionEventDataDeserializer(),
                    EventType.ROTATE to RotateEventDataDeserializer(),
                    EventType.INTVAR to IntVarEventDataDeserializer(),
                    EventType.QUERY to QueryEventDataDeserializer(),
                    EventType.TABLE_MAP to TableMapEventDataDeserializer(),
                    EventType.XID to XidEventDataDeserializer(),
                    // region Default dml deserializers with internal compatibility properties
                    EventType.WRITE_ROWS to WriteRowsEventDataDeserializer(tableMaps),
                    EventType.UPDATE_ROWS to UpdateRowsEventDataDeserializer(tableMaps),
                    EventType.DELETE_ROWS to DeleteRowsEventDataDeserializer(tableMaps),
                    EventType.EXT_WRITE_ROWS to WriteRowsEventDataDeserializer(tableMaps).setMayContainExtraInformation(true),
                    EventType.EXT_UPDATE_ROWS to UpdateRowsEventDataDeserializer(tableMaps).setMayContainExtraInformation(true),
                    EventType.EXT_DELETE_ROWS to DeleteRowsEventDataDeserializer(tableMaps).setMayContainExtraInformation(true),
                    // endregion
                    EventType.ROWS_QUERY to RowsQueryEventDataDeserializer(),
                    EventType.GTID to GtidEventDataDeserializer(),
                    EventType.PREVIOUS_GTIDS to PreviousGtidSetDeserializer(),
                    EventType.XA_PREPARE to XAPrepareEventDataDeserializer(),
                ),
                tableMaps
            ) {
                init {
                    setCompatibilityMode(
                        CompatibilityMode.DATE_AND_TIME_AS_LONG,
                        CompatibilityMode.CHAR_AND_BINARY_AS_BYTE_ARRAY,
                        CompatibilityMode.INVALID_DATE_AND_TIME_AS_MIN_VALUE,
                    )

                    // region Replace dml deserializers
                    setEventDataDeserializer(
                        EventType.WRITE_ROWS, InsertDmlEventDataDeserializer(
                            tableMaps,
                            getEventDataDeserializer(EventType.WRITE_ROWS) as WriteRowsEventDataDeserializer
                        )
                    )
                    setEventDataDeserializer(
                        EventType.UPDATE_ROWS, UpdateDmlEventDataDeserializer(
                            tableMaps,
                            getEventDataDeserializer(EventType.UPDATE_ROWS) as UpdateRowsEventDataDeserializer
                        )
                    )
                    setEventDataDeserializer(
                        EventType.DELETE_ROWS, DeleteDmlEventDataDeserializer(
                            tableMaps,
                            getEventDataDeserializer(EventType.DELETE_ROWS) as DeleteRowsEventDataDeserializer
                        )
                    )
                    setEventDataDeserializer(
                        EventType.EXT_WRITE_ROWS, InsertDmlEventDataDeserializer(
                            tableMaps,
                            getEventDataDeserializer(EventType.EXT_WRITE_ROWS) as WriteRowsEventDataDeserializer
                        )
                    )
                    setEventDataDeserializer(
                        EventType.EXT_UPDATE_ROWS, UpdateDmlEventDataDeserializer(
                            tableMaps,
                            getEventDataDeserializer(EventType.EXT_UPDATE_ROWS) as UpdateRowsEventDataDeserializer
                        )
                    )
                    setEventDataDeserializer(
                        EventType.EXT_DELETE_ROWS, DeleteDmlEventDataDeserializer(
                            tableMaps,
                            getEventDataDeserializer(EventType.EXT_DELETE_ROWS) as DeleteRowsEventDataDeserializer
                        )
                    )
                    // endregion
                }
            })
            it.binlogFilename = nextOffset.filename
            it.binlogPosition = nextOffset.position
            it.gtidSet = nextOffset.gtidSet
            it.registerEventListener(this)
        }

        connectFuture = Executors.newSingleThreadExecutor().submit<Unit> {
            binlogClient.connect()
        }
    }

    override fun close() {
        binlogClient.disconnect()
        connectFuture.get()
        LOGGER.info("Disconnected from ${sourceConfig.hostname}:${sourceConfig.port}")
    }

    override fun onEvent(event: Event) {
        val header = event.getHeader<EventHeader>()
        // Record binlog sync delay
        sourceContext.recordMetric(
            "pulsar_io_binlog_source_sync_delay_in_ms",
            (Instant.now().toEpochMilli() - header.timestamp).toDouble()
        )
        when (header.eventType) {
            EventType.ROTATE -> {
                val data = event.getData<RotateEventData>()
                LOGGER.debug("Rotating binlog to ${data.binlogFilename}/${data.binlogPosition}: $event")
                nextOffset.filename = data.binlogFilename
                nextOffset.position = data.binlogPosition
            }
            EventType.GTID -> {
                val data = event.getData<GtidEventData>()
                val gtidSet = GtidSet(nextOffset.gtidSet)
                gtidSet.add(data.gtid)
                LOGGER.debug("Record gtid set $gtidSet: $event")
                nextOffset.gtidSet = gtidSet.toString()
            }
            EventType.WRITE_ROWS,
            EventType.EXT_WRITE_ROWS,
            EventType.UPDATE_ROWS,
            EventType.EXT_UPDATE_ROWS,
            EventType.DELETE_ROWS,
            EventType.EXT_DELETE_ROWS -> {
                val data = event.getData<DmlEventData>()
                if (dbMatchers.contains(data.database)) {
                    LOGGER.info("Handling DML event: $event")
                    pendingEvents.add(event)
                    commit()
                } else {
                    LOGGER.info("Ignoring DML event: $event")
                }
            }
            EventType.QUERY -> {
                val data = event.getData<QueryEventData>()
                when {
                    "BEGIN".equals(data.sql, true) -> {
                        LOGGER.info("Beginning transaction: $event")
                    }
                    "COMMIT".equals(data.sql, true) -> {
                        commit()
                    }
                    // TODO: DDL Filter
                    else -> {
                        if (dbMatchers.contains(data.database)) {
                            LOGGER.info("Handling DDL event: $event")
                            pendingEvents.add(Event(event.getHeader(), DdlEventData(data.database, data.sql)))
                            commit()
                        } else {
                            LOGGER.info("Ignoring DDL event: $event")
                        }
                    }
                }
            }
            EventType.XID -> {
                nextOffset.position = event.getHeader<EventHeaderV4>().nextPosition
                commit()
            }
            EventType.HEARTBEAT -> {
                // Do nothing here
            }
            else -> {
                LOGGER.debug("Ignoring event by type ${header.eventType}: $event")
            }
        }
    }

    private fun commit() {
        val offset = nextOffset.copy()
        LOGGER.info("Committing transaction: \n\t$offset\n\t${pendingEvents.joinToString(separator = "\n\t") { it.toString() }}")
        val events = pendingEvents.toTypedArray()
        pendingEvents.clear()
        consume(BinlogRecord(events) {
            LOGGER.info("Saving offset into state: $nextOffset")
            sourceContext.putBinlogOffset(BINLOG_OFFSET_STATE_KEY, offset)
        })
    }

    // for unit test only
    internal fun block(timeout: Long = 0L) {
        if (timeout == 0L) {
            connectFuture.get()
        } else {
            try {
                connectFuture.get(timeout, TimeUnit.MILLISECONDS)
            } catch (ex: TimeoutException) {
                LOGGER.debug("Exit due to timeout ${timeout}ms")
            }
        }
    }
}
