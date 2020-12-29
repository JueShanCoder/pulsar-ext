package com.boxuegu.basis.pulsar.binlog

import com.google.gson.Gson
import org.apache.pulsar.io.core.annotations.FieldDoc

data class BinlogSinkConfig(
    @FieldDoc(
        required = true,
        defaultValue = "",
        help = "Hostname of the mysql server"
    )
    val hostname: String = "",

    @FieldDoc(
        required = true,
        defaultValue = "3306",
        help = "Port of the mysql server"
    )
    val port: Int = 3306,

    @FieldDoc(
        defaultValue = "",
        sensitive = true,
        help = "Login username of the mysql server"
    )
    val username: String = "",

    @FieldDoc(
        defaultValue = "",
        sensitive = true,
        help = "Login password of the mysql server"
    )
    val password: String = "",

    @FieldDoc(
        defaultValue = "GMT",
        help = "Timezone of mysql client connections",
    )
    val timezone: String = "GMT",
) {
    fun validate() {
        if (hostname.isEmpty()) {
            throw IllegalArgumentException("Required hostname not set.")
        }
    }

    fun toMap(): Map<String, Any?> {
        return mapOf(
            "hostname" to this.hostname,
            "port" to this.port,
            "username" to this.username,
            "password" to this.password,
            "timezone" to this.timezone,
        )
    }
}

fun Map<String, Any?>.toBinlogSinkConfig(): BinlogSinkConfig {
    return Gson().let {
        it.fromJson(it.toJsonTree(this), BinlogSinkConfig::class.java)
    }
}
