package com.boxuegu.basis.pulsar.binlog

import com.google.gson.Gson

class BinlogSourceConfig {
}

fun BinlogSourceConfig.validate() {
}

fun Map<String, Any>.toBinlogSourceConfig(): BinlogSourceConfig {
    return Gson().let {
        it.fromJson(it.toJsonTree(this), BinlogSourceConfig::class.java)
    }
}
