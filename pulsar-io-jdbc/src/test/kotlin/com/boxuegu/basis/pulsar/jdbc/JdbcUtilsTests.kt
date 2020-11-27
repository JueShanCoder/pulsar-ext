package com.boxuegu.basis.pulsar.jdbc

import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.math.BigDecimal
import java.sql.Connection
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.logging.Logger

abstract class JdbcUtilsTests {
    companion object {
        private val LOGGER = Logger.getGlobal()!!
        private val GSON = GsonBuilder().registerTemporalAdapter().create()
    }

    protected lateinit var connection: Connection

    @BeforeMethod
    fun setupTest() {
        connection = setup()
    }

    @AfterMethod
    fun cleanupTest() {
        cleanup()
        if (!connection.isClosed) {
            connection.close()
        }
    }

    abstract fun setup(): Connection

    open fun cleanup() {}

    private fun testAction(target: String, action: JdbcAction, entity: JsonElement) {
        connection.buildSQL(target, action, entity, setOf("INSERT_IGNORE_INVALID")).also {
            LOGGER.info("STATEMENT: $it")
        }.let {
            connection.prepareStatement(it)
        }.also {
            it.bindValue(target, action, entity)
            LOGGER.info("RESULT: ${it.executeUpdate()}")
        }
    }

    private fun testAction(target: String, action: JdbcAction, entity: Map<String, Any>) = testAction(target, action, GSON.toJsonTree(entity))

    @Test
    fun testActions() {
        LOGGER.info("TABLE: ${connection.metaData.loadTable("samples")}")
        testAction("samples", JdbcAction.INSERT, mapOf(
                "id" to 1,
                "f_boolean" to false,
                "f_bit" to 0,
                "f_tinyint" to Byte.MIN_VALUE,
                "f_smallint" to Short.MIN_VALUE,
                "f_integer" to Int.MIN_VALUE,
                "f_bigint" to Long.MIN_VALUE,
                "f_float" to Float.MIN_VALUE,
                "f_real" to Float.MIN_VALUE,
                "f_double" to Double.MIN_VALUE,
                "f_numeric" to BigDecimal.valueOf(12345678, 4),
                "f_decimal" to BigDecimal.valueOf(12345678, 4),
                "f_char" to "Hello, World.",
                "f_varchar" to "Hello, World.",
                "f_longvarchar" to "Hello, World.",
                "f_binary" to "SGVsbG8sIFdvcmxkLg==",
                "f_varbinary" to "SGVsbG8sIFdvcmxkLg==",
                "f_longvarbinary" to "SGVsbG8sIFdvcmxkLg==",
                "f_date" to LocalDate.now(),
                "f_time" to LocalTime.now(),
                "f_datetime" to LocalDateTime.now(),
                "f_timestamp" to LocalDateTime.now()
        ))
        testAction("samples", JdbcAction.UPSERT, mapOf(
                "id" to 2,
                "f_boolean" to false,
                "f_bit" to 0,
                "f_tinyint" to Byte.MIN_VALUE,
                "f_smallint" to Short.MIN_VALUE,
                "f_integer" to Int.MIN_VALUE,
                "f_bigint" to Long.MIN_VALUE,
                "f_float" to Float.MIN_VALUE,
                "f_real" to Float.MIN_VALUE,
                "f_double" to Double.MIN_VALUE,
                "f_numeric" to BigDecimal.valueOf(12345678, 4),
                "f_decimal" to BigDecimal.valueOf(12345678, 4),
                "f_char" to "Hello, World.",
                "f_varchar" to "Hello, World.",
                "f_longvarchar" to "Hello, World.",
                "f_binary" to "SGVsbG8sIFdvcmxkLg==",
                "f_varbinary" to "SGVsbG8sIFdvcmxkLg==",
                "f_longvarbinary" to "SGVsbG8sIFdvcmxkLg==",
                "f_date" to LocalDate.now(),
                "f_time" to LocalTime.now(),
                "f_datetime" to LocalDateTime.now(),
                "f_timestamp" to LocalDateTime.now()
        ))
        testAction("samples", JdbcAction.UPDATE, mapOf(
                "id" to 1,
                "f_boolean" to true,
                "f_bit" to 1,
                "f_tinyint" to Byte.MAX_VALUE,
                "f_smallint" to Short.MAX_VALUE,
                "f_integer" to Int.MAX_VALUE,
                "f_bigint" to Long.MAX_VALUE,
                "f_float" to Float.MAX_VALUE,
                "f_real" to Float.MAX_VALUE,
                "f_double" to Double.MAX_VALUE,
                "f_numeric" to BigDecimal.valueOf(12345678, 4),
                "f_decimal" to BigDecimal.valueOf(12345678, 4),
                "f_char" to "Hello, World.",
                "f_varchar" to "Hello, World.",
                "f_longvarchar" to "Hello, World.",
                "f_binary" to "SGVsbG8sIFdvcmxkLg==",
                "f_varbinary" to "SGVsbG8sIFdvcmxkLg==",
                "f_longvarbinary" to "SGVsbG8sIFdvcmxkLg==",
                "f_date" to LocalDate.now(),
                "f_time" to LocalTime.now(),
                "f_timestamp" to LocalDateTime.now()
        ))
        testAction("samples", JdbcAction.INSERT, mapOf(
                "id" to 3,
                "f_timestamp" to LocalDateTime.now()
        ))
        testAction("samples", JdbcAction.DELETE, mapOf(
                "id" to 3
        ))
    }
}