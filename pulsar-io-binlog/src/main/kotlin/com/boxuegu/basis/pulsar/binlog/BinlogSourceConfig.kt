package com.boxuegu.basis.pulsar.binlog

import com.google.gson.Gson
import org.apache.pulsar.io.core.annotations.FieldDoc

data class BinlogSourceConfig(
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

        @FieldDoc(
                defaultValue = "",
                help = "Comma-separated list of databases to be monitored",
        )
        val databases: String = "",

        @FieldDoc(
                defaultValue = "",
                help = "Binlog filename of initial binlog offset",
        )
        val offsetFilename: String = "",

        @FieldDoc(
                defaultValue = "",
                help = "Binlog position of initial binlog offset",
        )
        val offsetPosition: Long = 4L,

        @FieldDoc(
                defaultValue = "",
                help = "GTID set of initial binlog offset",
        )
        val offsetGtidSet: String = "",
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
                "offsetFilename" to this.offsetFilename,
                "offsetPosition" to this.offsetPosition,
                "offsetGtidSet" to this.offsetGtidSet,
        )
    }
}

fun Map<String, Any?>.toBinlogSourceConfig(): BinlogSourceConfig {
    return Gson().let {
        it.fromJson(it.toJsonTree(this), BinlogSourceConfig::class.java)
    }
}
