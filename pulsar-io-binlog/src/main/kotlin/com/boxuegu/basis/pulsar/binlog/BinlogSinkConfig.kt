package com.boxuegu.basis.pulsar.binlog

import com.google.gson.Gson

class BinlogSinkConfig {
}

fun BinlogSinkConfig.validate() {
}

fun Map<String, Any>.toBinlogSinkConfig(): BinlogSinkConfig {
    return Gson().let {
        it.fromJson(it.toJsonTree(this), BinlogSinkConfig::class.java)
    }
}
