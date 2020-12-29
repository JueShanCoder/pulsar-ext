package com.boxuegu.basis.pulsar.binlog

import com.github.shyiko.mysql.binlog.event.Event
import com.google.gson.GsonBuilder
import org.apache.pulsar.functions.api.Record
import org.slf4j.LoggerFactory

private val LOGGER = LoggerFactory.getLogger(BinlogRecord::class.java)
private val GSON = GsonBuilder().registerBinlogEventAdapter().create()

class BinlogRecord(
    private val events: Array<Event>,
    private val commit: () -> Unit,
) : Record<ByteArray> {
    override fun getValue(): ByteArray {
        try {
            return GSON.toJson(events).encodeToByteArray()
        } catch (ex: RuntimeException) {
            LOGGER.warn("Error when serialize event to bytes", ex)
            throw ex
        }
    }

    override fun ack() {
        commit()
    }
}

fun Record<ByteArray>.getEvents(): Array<Event> {
    try {
        return GSON.fromJson(this.value.decodeToString(), Array<Event>::class.java)
    } catch (ex: Throwable) {
        LOGGER.warn("Error when deserialize event from bytes", ex)
        throw ex
    }
}
