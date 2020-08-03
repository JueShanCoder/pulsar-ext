package com.boxuegu.basis.pulsar.jdbc

import com.google.gson.JsonElement
import org.apache.pulsar.functions.api.Record
import java.math.BigDecimal
import java.sql.*
import java.sql.Date
import java.time.*
import java.util.*

class DriverNames {
    companion object {
        const val H2 = "H2 JDBC Driver"
        const val MySQL = "MySQL Connector/J"
        const val MariaDB = "MariaDB Connector/J"
        const val PostgreSQL = "PostgreSQL JDBC Driver"
    }
}

data class JdbcColumn(
        val name: String,
        val type: Int,
        val isKey: Boolean,
        val nullable: Boolean
) {
    fun parseField(field: JsonElement?): JdbcField {
        val value: Any? = if (field == null || field.isJsonNull) null else when (type) {
            Types.BIT -> field.asByte
            Types.TINYINT -> field.asByte
            Types.SMALLINT -> field.asShort
            Types.INTEGER -> field.asInt
            Types.BIGINT -> field.asLong
            Types.FLOAT,
            Types.REAL -> field.asFloat
            Types.DOUBLE -> field.asDouble
            Types.NUMERIC,
            Types.DECIMAL -> field.asBigDecimal
            Types.CHAR,
            Types.VARCHAR,
            Types.LONGVARCHAR,
            Types.NCHAR,
            Types.NVARCHAR,
            Types.LONGNVARCHAR -> field.asString
            Types.BINARY,
            Types.VARBINARY,
            Types.LONGVARBINARY -> Base64.getDecoder().decode(field.asString)
            Types.CLOB -> TODO()
            Types.NCLOB -> TODO()
            Types.BLOB -> TODO()
            Types.DATE -> LocalDate.parse(field.asString)
            Types.TIME -> LocalTime.parse(field.asString)
            Types.TIMESTAMP -> LocalDateTime.parse(field.asString)
            Types.TIME_WITH_TIMEZONE -> TODO()
            Types.TIMESTAMP_WITH_TIMEZONE -> TODO()
            else -> TODO()
        }
        return JdbcField(this.name, this.type, this.isKey, value)
    }
}

data class JdbcTable(
        val catalog: String,
        val schema: String,
        val name: String,
        val columns: List<JdbcColumn>
) {
    val keys: List<JdbcColumn> by lazy { columns.filter(JdbcColumn::isKey) }

    fun isKey(c: String): Boolean = keys.any { it.name == c }

    fun parseFields(entity: JsonElement): List<JdbcField> = columns.filter { entity.asJsonObject.has(it.name) }.map {
        it.parseField(entity.asJsonObject.get(it.name))
    }
}

data class JdbcField(
        val name: String,
        val type: Int,
        val isKey: Boolean,
        val value: Any?
)

fun DatabaseMetaData.quoting(vararg args: String): String {
    val quote = identifierQuoteString.orEmpty().ifBlank { "" }
    return args.filter(String::isNotEmpty).joinToString(separator = catalogSeparator) { "$quote$it$quote" }
}

fun DatabaseMetaData.loadTable(target: String): JdbcTable {
    val p = target.split('.')
    val c = if (p.size > 1) p[0] else null
    val s = if (p.size > 2) p[1] else null
    val t = p.last()
    return getTables(c, s, t, arrayOf("TABLE")).use {
        if (it.next()) {
            if (!it.isLast) {
                throw IllegalArgumentException("Implicit table of target \"$target\"")
            }
            val catalog = it.getString(1)
            val schema = it.getString(2)
            val name = it.getString(3)
            val keys = getPrimaryKeys(catalog, schema, name).use {
                generateSequence { if (it.next()) it.getString(4) else null }.toList()
            }
            val cols = getColumns(catalog, schema, name, null).use {
                generateSequence {
                    if (it.next()) JdbcColumn(
                            it.getString(4),
                            it.getInt(5),
                            keys.contains(it.getString(4)),
                            it.getString(18) == "YES"
                    ) else null
                }.toList()
            }
            JdbcTable(catalog, schema ?: "", name, cols)
        } else {
            throw IllegalArgumentException("Implicit table of target \"$target\"")
        }
    }
}

fun PreparedStatement.setDate(index: Int, date: LocalDate) = setDate(index, Date.valueOf(date))
fun PreparedStatement.setTime(index: Int, time: LocalTime) = setTime(index, Time.valueOf(time))
fun PreparedStatement.setTimestamp(index: Int, datetime: LocalDateTime) = setTimestamp(index, Timestamp.valueOf(datetime))

fun PreparedStatement.setParam(index: Int, field: JdbcField) {
    if (field.value == null) setNull(index, field.type) else when (field.type) {
        Types.BIT,
        Types.TINYINT -> setByte(index, field.value as Byte)
        Types.SMALLINT -> setShort(index, field.value as Short)
        Types.INTEGER -> setInt(index, field.value as Int)
        Types.BIGINT -> setLong(index, field.value as Long)
        Types.FLOAT,
        Types.REAL -> setFloat(index, field.value as Float)
        Types.DOUBLE -> setDouble(index, field.value as Double)
        Types.NUMERIC,
        Types.DECIMAL -> setBigDecimal(index, field.value as BigDecimal)
        Types.CHAR,
        Types.VARCHAR,
        Types.LONGVARCHAR -> setString(index, field.value as String)
        Types.NCHAR,
        Types.NVARCHAR,
        Types.LONGNVARCHAR -> setNString(index, field.value as String)
        Types.BINARY,
        Types.VARBINARY,
        Types.LONGVARBINARY -> setBytes(index, field.value as ByteArray)
        Types.CLOB -> TODO()
        Types.NCLOB -> TODO()
        Types.BLOB -> TODO()
        Types.DATE -> setDate(index, field.value as LocalDate)
        Types.TIME -> setTime(index, field.value as LocalTime)
        Types.TIMESTAMP -> setTimestamp(index, field.value as LocalDateTime)
        Types.TIME_WITH_TIMEZONE -> TODO()
        Types.TIMESTAMP_WITH_TIMEZONE -> TODO()
        else -> TODO()
    }
}

fun PreparedStatement.setParams(args: List<JdbcField>, offset: Int = 0) = args.forEachIndexed { index, field ->
    this.setParam(1 + index + offset, field)
}
