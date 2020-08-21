package com.boxuegu.basis.pulsar.binlog

import org.apache.pulsar.functions.api.Record
import org.apache.pulsar.io.core.Source
import org.apache.pulsar.io.core.SourceContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Suppress("unused")
class BinlogSource : Source<ByteArray> {
    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(BinlogSource::class.java)
    }

    private lateinit var sourceConfig: BinlogSourceConfig

    override fun open(config: Map<String, Any>, context: SourceContext) {
        sourceConfig = config.toBinlogSourceConfig().also { it.validate() }
        TODO("Not yet implemented")
    }

    override fun close() {
        TODO("Not yet implemented")
    }

    override fun read(): Record<ByteArray> {
        return BinlogRecord()
    }
}
