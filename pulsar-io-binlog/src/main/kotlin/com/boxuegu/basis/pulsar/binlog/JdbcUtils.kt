package com.boxuegu.basis.pulsar.binlog

import com.github.shyiko.mysql.binlog.event.Event
import com.github.shyiko.mysql.binlog.event.EventHeader
import com.github.shyiko.mysql.binlog.event.EventType
import com.github.shyiko.mysql.binlog.event.deserialization.ColumnType
import com.github.shyiko.mysql.binlog.event.deserialization.json.JsonBinary
import com.mysql.cj.jdbc.JdbcConnection
import java.math.BigDecimal
import java.sql.Connection
import java.sql.DatabaseMetaData
import java.sql.JDBCType
import java.sql.PreparedStatement
import java.util.*

fun Connection.loadBinlogOffset(): BinlogOffset {
    val rs = this.createStatement().executeQuery("SHOW MASTER STATUS")
    return if (rs.next()) {
        BinlogOffset(
            rs.getString(1),
            rs.getLong(2),
            rs.getString(5)
        )
    } else {
        BinlogOffset()
    }
}

fun Connection.setDatabase(database: String?) {
    if (this is JdbcConnection) {
        this.database = database
    }
}

data class JdbcColumn(
    val name: String,
    val type: Int,
    val size: Int,
    val index: Int,
    val isKey: Boolean,
    val nullable: Boolean,
    val unsigned: Boolean,
) {
    override fun toString(): String {
        return "JdbcColumn(name='$name', type=${JDBCType.valueOf(type).name}, size=$size, isKey=$isKey, nullable=$nullable, unsigned=$unsigned)"
    }
}

data class JdbcTable(
    val catalog: String,
    val schema: String,
    val name: String,
    val columns: List<JdbcColumn>,
) {
    val keys: List<JdbcColumn> by lazy { columns.filter(JdbcColumn::isKey) }

    fun hasKey(c: String): Boolean = keys.any { it.name == c }

    fun hasColumn(c: String): Boolean = columns.any { it.name == c }

    fun parseFields(row: List<DmlEventData.Column>): List<JdbcField> = row.map {
        val c = columns.singleOrNull() { jc -> jc.index == it.index }
            ?: throw IllegalStateException("Unknown column in table '${name}' at index ${it.index}")
        JdbcField(c.name, c.type, it.type, c.isKey, it.value, c.unsigned)
    }
}

data class JdbcField(
    val name: String,
    val type: Int,
    val bType: Byte,
    val isKey: Boolean,
    val value: Any?,
    val unsigned: Boolean,
) {
    override fun toString(): String {
        return "JdbcField(name='$name', type=${JDBCType.valueOf(type).name}, bType=${ColumnType.byCode(bType.toInt() and 0xff)}, isKey=$isKey, value=$value)"
    }
}

fun DatabaseMetaData.loadTable(database: String, tableName: String): JdbcTable {
    return getTables(database, null, tableName, arrayOf("TABLE")).use {
        if (it.next()) {
            val catalog = it.getString(1) ?: database
            val name = it.getString(3) ?: tableName
            val keys = getPrimaryKeys(catalog, null, name).use {
                generateSequence { if (it.next()) it.getString(4) else null }.toList()
            }
            val cols = getColumns(catalog, null, name, null).use {
                generateSequence {
                    if (it.next()) JdbcColumn(
                        it.getString(4),
                        it.getInt(5),
                        it.getInt(7),
                        it.getInt(17),
                        keys.contains(it.getString(4)),
                        it.getString(18).equals("YES", true),
                        it.getString(6).contains("UNSIGNED", true)
                    ) else null
                }.toList()
            }
            JdbcTable(catalog, "", name, cols)
        } else {
            throw IllegalArgumentException("Implicit table: `$database`.`$tableName`")
        }
    }
}

//region Param Binders

fun PreparedStatement.setParam(index: Int, field: JdbcField) {
    when (field.value) {
        null -> {
            setNull(index, field.type)
        }
        is Int -> {
            if (field.unsigned) {
                when (ColumnType.byCode(field.bType.toInt() and 0xff)) {
                    ColumnType.TINY -> setInt(index, field.value shl 24 ushr 24)
                    ColumnType.SHORT -> setInt(index, field.value shl 16 ushr 16)
                    ColumnType.INT24 -> setInt(index, field.value shl 8 ushr 8)
                    ColumnType.LONG -> setLong(index, field.value.toLong() shl 32 ushr 32)
                    else -> setInt(index, field.value)
                }
            } else {
                setInt(index, field.value)
            }
        }
        is Long -> {
            if (field.unsigned) {
                setBigDecimal(index, BigDecimal(java.lang.Long.toUnsignedString(field.value)))
            } else {
                when (JDBCType.valueOf(field.type)) {
                    JDBCType.DATE -> setDate(index, java.sql.Date(field.value))
                    JDBCType.TIME -> setTime(index, java.sql.Time(field.value))
                    JDBCType.TIMESTAMP -> setTimestamp(index, java.sql.Timestamp(field.value))
                    else -> setLong(index, field.value)
                }
            }
        }
        is Float -> {
            setFloat(index, field.value)
        }
        is Double -> {
            setDouble(index, field.value)
        }
        is BigDecimal -> {
            setBigDecimal(index, field.value)
        }
        is ByteArray -> {
            when (ColumnType.byCode(field.bType.toInt() and 0xff)) {
                ColumnType.JSON -> setString(index, JsonBinary.parseAsString(field.value))
                else -> setBytes(index, field.value)
            }
        }
        is BitSet -> {
            setBytes(index, field.value.toByteArray())
        }
        else -> {
            throw IllegalArgumentException("Parameter binding of type '${field.value::class.java.name}' is not supported")
        }
    }
}

fun PreparedStatement.setParams(args: List<JdbcField>) = args.forEachIndexed { index, field ->
    setParam(1 + index, field)
}

//endregion

//region SQL Helpers

fun DatabaseMetaData.quoting(vararg args: String): String {
    val quote = identifierQuoteString.orEmpty().ifBlank { "" }
    return args.filter(String::isNotEmpty).joinToString(separator = catalogSeparator) { "$quote$it$quote" }
}

// quoting
private fun Connection.q(vararg args: String): String = metaData.quoting(*args)

// table identifier
private fun Connection.t(table: JdbcTable): String = q(table.catalog, table.schema, table.name)

// key list
private fun Connection.k(table: JdbcTable): String = table.keys.joinToString { q(it.name) }

// key predicate
private fun Connection.p(table: JdbcTable): String = table.keys.joinToString { "${q(it.name)} = ?" }

//endregion

fun Connection.relayBinlogEvent(event: Event) {
    val header = event.getHeader<EventHeader>()
    when (header.eventType) {
        EventType.QUERY -> {
            this.relayDdlEventData(event.getData())
        }
        EventType.WRITE_ROWS,
        EventType.EXT_WRITE_ROWS,
        EventType.UPDATE_ROWS,
        EventType.EXT_UPDATE_ROWS,
        EventType.DELETE_ROWS,
        EventType.EXT_DELETE_ROWS -> {
            this.relayDmlEventData(event.getData())
        }
        else -> {
            throw IllegalArgumentException("Ignoring unsupported event by type: ${header.eventType}")
        }
    }
}

private fun Connection.relayDdlEventData(data: DdlEventData) {
    if (!data.statement.contains("CREATE DATABASE", true) && data.database.isNotEmpty()) {
        this.setDatabase(data.database)
    }
    this.createStatement().execute(data.statement)
}

private fun Connection.relayDmlEventData(data: DmlEventData) {
    val table = this.metaData.loadTable(data.database, data.tableName)
    this.setDatabase(data.database)
    if (data.rowsBefore != null && data.rowsAfter != null) { // update
        for (i in data.rowsBefore.indices) {
            // fields after
            val fsa = table.parseFields(data.rowsAfter[i])
            // keys before
            val ksb = table.parseFields(data.rowsBefore[i]).filter { it.isKey }
            // prepare statement
            val sql = this.prepareStatement(
                "UPDATE ${t(table)} SET ${fsa.joinToString { "${q(it.name)} = ?" }} WHERE ${p(table)}"
            )
            // set params
            sql.setParams(fsa + ksb)
            sql.executeUpdate()
        }
    } else if (data.rowsBefore != null && data.rowsAfter == null) { // delete
        for (i in data.rowsBefore.indices) {
            // keys before
            val ksb = table.parseFields(data.rowsBefore[i]).filter { it.isKey }
            // prepare statement
            val sql = this.prepareStatement("DELETE FROM ${t(table)} WHERE ${p(table)}")
            // set params
            sql.setParams(ksb)
            sql.executeUpdate()
        }
    } else if (data.rowsBefore == null && data.rowsAfter != null) { // insert
        for (i in data.rowsAfter.indices) {
            // fields after
            val fsa = table.parseFields(data.rowsAfter[i])
            val sql = this.prepareStatement("INSERT INTO ${t(table)} (${fsa.joinToString { q(it.name) }}) VALUES (${fsa.joinToString { "?" }})")
            // set params
            sql.setParams(fsa)
            sql.executeUpdate()
        }
    } else { // unknown
        throw IllegalArgumentException("Both 'beforeRows' and 'afterRows' are null")
    }
}
