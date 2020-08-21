package com.boxuegu.basis.pulsar.binlog

import org.apache.pulsar.functions.api.Record

class BinlogRecord : Record<ByteArray> {
    override fun getValue(): ByteArray {
        TODO("Not yet implemented")
    }
}
