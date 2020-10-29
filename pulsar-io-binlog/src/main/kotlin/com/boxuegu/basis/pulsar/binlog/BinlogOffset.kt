package com.boxuegu.basis.pulsar.binlog

import com.google.gson.Gson
import org.apache.pulsar.io.core.SourceContext
import java.io.Serializable
import java.nio.ByteBuffer

private val GSON = Gson()

data class BinlogOffset(
        var filename: String? = null,
        var position: Long = 4L,
        var gtidSet: String? = null,
) : Serializable

fun SourceContext.getBinlogOffset(key: String): BinlogOffset? {
    return getState(key)?.let {
        GSON.fromJson(it.array().decodeToString(), BinlogOffset::class.java)
    }
}

fun SourceContext.putBinlogOffset(key: String, offset: BinlogOffset) {
    this.putState(key, ByteBuffer.wrap(GSON.toJson(offset).encodeToByteArray()))
}

fun BinlogSourceConfig.getBinlogOffset(): BinlogOffset? {
    return when {
        this.offsetGtidSet.isNotEmpty() -> {
            BinlogOffset(gtidSet = this.offsetGtidSet)
        }
        this.offsetFilename.isNotEmpty() -> {
            BinlogOffset(this.offsetFilename, this.offsetPosition)
        }
        else -> {
            null
        }
    }
}
