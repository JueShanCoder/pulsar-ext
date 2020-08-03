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
import java.sql.PreparedStatement
import java.sql.SQLException

class JdbcSink : Sink<ByteArray> {
    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(JdbcSink::class.java)
    }

    private lateinit var connection: Connection
    private lateinit var sinkConfig: JdbcSinkConfig
    private val tables: MutableMap<String, JdbcTable> = mutableMapOf<String, JdbcTable>()

    override fun open(config: MutableMap<String, Any>, context: SinkContext) {
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
        val sql = buildSql(record)
        val statement = connection.prepareStatement(sql)
        LOGGER.info("STATEMENT: {}", sql)
        bindValue(statement, record)
        try {
            statement.execute()
            connection.commit()
            record.ack()
        } catch (e: SQLException) {
            LOGGER.warn("SQLException when execute statement: ", e)
            throw e
        }
    }

    // quoting
    private fun q(vararg args: String) = connection.metaData.quoting(*args)

    // table identifier
    private fun t(table: JdbcTable): String = q(table.catalog, table.schema, table.name)

    // key list
    private fun k(table: JdbcTable): String = table.keys.joinToString { q(it.name) }

    // key predicate
    private fun p(table: JdbcTable): String = table.keys.joinToString { "${q(it.name)} = ?" }

    private fun buildSql(record: Record<ByteArray>): String {
        val table = tables.computeIfAbsent(record.target, connection.metaData::loadTable)
        val fields = record.entity.asJsonObject.entrySet()
        val nonKeys = fields.filterNot { table.isKey(it.key) }
        return when (record.action) {
            "INSERT" -> {
                "INSERT INTO ${t(table)} (${fields.joinToString { q(it.key) }}) VALUES (${fields.joinToString { "?" }})"
            }

            "UPSERT" -> {
                when (connection.metaData.driverName) {
                    DriverNames.PostgreSQL -> {
                        "INSERT INTO ${t(table)} (${fields.joinToString { q(it.key) }}) VALUES (${fields.joinToString { "?" }}) ON CONFLICT(${k(table)}) DO UPDATE SET ${nonKeys.joinToString { "${q(it.key)}=?" }}"
                    }
                    DriverNames.MariaDB,
                    DriverNames.MySQL -> {
                        "INSERT INTO ${t(table)} (${fields.joinToString { q(it.key) }}) VALUES (${fields.joinToString { "?" }}) ON DUPLICATE KEY UPDATE ${nonKeys.joinToString { "${q(it.key)}=?" }}"
                    }
                    DriverNames.H2 -> {
                        "MERGE INTO ${t(table)} (${fields.joinToString { q(it.key) }}) VALUES (${fields.joinToString { "?" }})"
                    }
                    else -> {
                        throw java.lang.IllegalArgumentException("Unsupported action ${record.action} with JDBC driver ${connection.metaData.driverName}")
                    }
                }
            }

            "UPDATE" -> {
                "UPDATE ${t(table)} SET ${nonKeys.joinToString { "${q(it.key)} = ?" }} WHERE ${p(table)}"
            }

            "DELETE" -> {
                "DELETE FROM ${t(table)} WHERE ${p(table)}"
            }

            "SCHEMA" -> TODO()

            else -> {
                throw IllegalArgumentException("Unsupported action ${record.action}, can be one of ${arrayOf("INSERT", "UPDATE", "UPSERT", "DELETE", "SCHEMA")}")
            }
        }
    }

    private fun bindValue(statement: PreparedStatement, record: Record<ByteArray>) {
        val table = tables.computeIfAbsent(record.target, connection.metaData::loadTable)
        val fields = table.parseFields(record.entity)
        val keys = fields.filter(JdbcField::isKey)
        val nonKeys = fields.filterNot(JdbcField::isKey)
        when (record.action) {
            "INSERT" -> {
                statement.setParams(fields)
            }

            "UPSERT" -> {
                when (connection.metaData.driverName) {
                    DriverNames.PostgreSQL,
                    DriverNames.MariaDB,
                    DriverNames.MySQL -> {
                        statement.setParams(fields)
                        statement.setParams(nonKeys, fields.size)
                    }
                    DriverNames.H2 -> {
                        statement.setParams(fields)
                    }
                    else -> {
                        throw java.lang.IllegalArgumentException("Unsupported action ${record.action} with JDBC driver ${connection.metaData.driverName}")
                    }
                }
            }

            "UPDATE" -> {
                statement.setParams(nonKeys)
                statement.setParams(keys, nonKeys.size)
            }

            "DELETE" -> {
                statement.setParams(keys)
            }

            "SCHEMA" -> TODO()

            else -> {
                throw IllegalArgumentException("Unsupported action ${record.action}, can be one of ${arrayOf("INSERT", "UPDATE", "UPSERT", "DELETE", "SCHEMA")}")
            }
        }
    }
}

inline val Record<ByteArray>.action: String
    get() = this.properties.getOrDefault("ACTION", "INSERT")

inline val Record<ByteArray>.target: String
    get() = this.properties["TARGET"] ?: throw IllegalArgumentException("Required property 'TARGET' not set.")

inline val Record<ByteArray>.entity: JsonElement
    get() = JsonParser().parse(String(this.value))
