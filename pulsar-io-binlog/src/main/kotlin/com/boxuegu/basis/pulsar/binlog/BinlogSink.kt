package com.boxuegu.basis.pulsar.binlog

import org.apache.pulsar.functions.api.Record
import org.apache.pulsar.io.core.Sink
import org.apache.pulsar.io.core.SinkContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Suppress("unused")
class BinlogSink : Sink<ByteArray> {
    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(BinlogSink::class.java)
    }

    private lateinit var sinkConfig: BinlogSinkConfig

    override fun open(config: Map<String, Any>, context: SinkContext) {
        sinkConfig = config.toBinlogSinkConfig().also { it.validate() }
        TODO("Not yet implemented")
    }

    override fun close() {
        TODO("Not yet implemented")
    }

    override fun write(record: Record<ByteArray>?) {
        TODO("Not yet implemented")
    }
}
