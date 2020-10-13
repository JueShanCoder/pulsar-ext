package com.boxuegu.basis.pulsar.binlog

import com.github.shyiko.mysql.binlog.BinaryLogClient
import com.github.shyiko.mysql.binlog.event.Event
import org.apache.pulsar.functions.api.Record
import org.apache.pulsar.io.core.Sink
import org.apache.pulsar.io.core.SinkContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

@Suppress("unused")
class BinlogSink : Sink<ByteArray>, BinaryLogClient.EventListener {
    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(BinlogSink::class.java)
    }

    private lateinit var sinkContext: SinkContext
    private lateinit var sinkConfig: BinlogSinkConfig
    private lateinit var connection: Connection

    override fun open(config: Map<String, Any?>, context: SinkContext) {
        sinkContext = context
        sinkConfig = config.toBinlogSinkConfig().also { it.validate() }

        // Create jdbc connection
        Class.forName("com.mysql.cj.jdbc.Driver")
        connection = DriverManager.getConnection(
                "jdbc:mysql://${sinkConfig.hostname}:${sinkConfig.port}" +
                        "?user=${sinkConfig.username}&password=${sinkConfig.password}" +
                        "&useSSL=false&serverTimezone=${sinkConfig.timezone}&useUnicode=true&characterEncoding=utf8" +
                        "&yearIsDateType=false&tinyInt1isBit=false&disableMariaDbDriver=true",
        ).also { it.autoCommit = false } // Disable auto commit
    }

    override fun close() {
        connection.close()
    }

    override fun write(record: Record<ByteArray>) {
        record.getEvents().forEach(this::onEvent)
        record.ack()
    }

    override fun onEvent(event: Event) {
        LOGGER.info("Received: $event")
        try {
            connection.relayBinlogEvent(event)
            connection.commit()
        } catch (t: Throwable) {
            LOGGER.error("Error when relay binlog event", t)
            try {
                connection.rollback()
            } catch (e: SQLException) {
                LOGGER.error("Error when rollback transaction", e)
            }
            throw t
        }
    }
}
