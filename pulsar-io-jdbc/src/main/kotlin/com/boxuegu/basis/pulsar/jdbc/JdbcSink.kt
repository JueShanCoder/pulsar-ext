package com.boxuegu.basis.pulsar.jdbc

import com.google.gson.JsonElement
import com.google.gson.JsonParser
import org.apache.pulsar.functions.api.Record
import org.apache.pulsar.io.core.Sink
import org.apache.pulsar.io.core.SinkContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

@Suppress("unused")
class JdbcSink : Sink<ByteArray> {
    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(JdbcSink::class.java)
    }

    private lateinit var connection: Connection
    private lateinit var sinkConfig: JdbcSinkConfig

    override fun open(config: MutableMap<String, Any?>, context: SinkContext) {
        sinkConfig = config.toJdbcSinkConfig().also { it.validate() }
        Class.forName(sinkConfig.driver)
        connection = DriverManager.getConnection(sinkConfig.jdbcUrl, sinkConfig.username, sinkConfig.password).also {
            it.autoCommit = false
        }
        LOGGER.info("Instance {} connected to {}", context.instanceId, sinkConfig.jdbcUrl)
    }

    override fun close() {
        if (!connection.autoCommit) {
            connection.commit()
        }
        connection.close()
    }

    override fun write(record: Record<ByteArray>) {
        LOGGER.info("ACTION: {}, TARGET: {}, ENTITY: {}, RECORD: {}", record.action, record.target, record.entity, record::class)
        val sql = connection.buildSQL(record.target, record.action, record.entity)
        val statement = connection.prepareStatement(sql)
        LOGGER.info("STATEMENT: {}", sql)
        statement.bindValue(record.target, record.action, record.entity)
        try {
            statement.execute()
            connection.commit()
            record.ack()
        } catch (e: SQLException) {
            LOGGER.warn("Caught SQLException when execute statement", e)
            connection.rollback()
            throw e
        }
    }
}

inline val Record<ByteArray>.action: JdbcAction
    get() = JdbcAction.valueOf(properties.getOrDefault("ACTION", "INSERT"))

inline val Record<ByteArray>.target: String
    get() = properties["TARGET"] ?: throw IllegalArgumentException("Required property 'TARGET' for action '$action' not set.")

inline val Record<ByteArray>.entity: JsonElement
    get() = JsonParser().parse(String(value))
