package com.boxuegu.basis.pulsar.binlog

import com.github.shyiko.mysql.binlog.event.EventData
import com.github.shyiko.mysql.binlog.event.TableMapEventData
import com.github.shyiko.mysql.binlog.event.deserialization.DeleteRowsEventDataDeserializer
import com.github.shyiko.mysql.binlog.event.deserialization.EventDataDeserializer
import com.github.shyiko.mysql.binlog.event.deserialization.UpdateRowsEventDataDeserializer
import com.github.shyiko.mysql.binlog.event.deserialization.WriteRowsEventDataDeserializer
import com.github.shyiko.mysql.binlog.io.ByteArrayInputStream
import java.io.Serializable
import java.util.*

data class DmlEventData(
        val database: String,
        val tableName: String,
        val rowsBefore: List<List<Column>>?,
        val rowsAfter: List<List<Column>>?,
) : EventData {
    companion object {
        fun parseColumns(values: Array<Serializable>, ics: BitSet, tableMap: TableMapEventData): List<Column> {
            val columns = mutableListOf<Column>()
            val iterator = values.iterator()
            for (i in 0 until ics.size()) {
                if (ics.get(i)) {
                    columns.add(Column(
                            i + 1,
                            tableMap.columnTypes[i],
                            iterator.next(),
                            tableMap.columnNullability.get(i),
                            tableMap.columnMetadata[i]
                    ))
                }
            }
            return columns
        }
    }

    data class Column(
            val index: Int,
            val type: Byte,
            val value: Serializable?,
            val nullable: Boolean,
            val metadata: Int,
    )
}

data class DdlEventData(
        val database: String,
        val statement: String,
) : EventData

class InsertDmlEventDataDeserializer(
        private val tableMaps: MutableMap<Long, TableMapEventData>,
        private val deserializer: WriteRowsEventDataDeserializer,
) : EventDataDeserializer<DmlEventData> {
    override fun deserialize(inputStream: ByteArrayInputStream): DmlEventData {
        val data = deserializer.deserialize(inputStream)
        val table = tableMaps[data.tableId] ?: throw IllegalStateException()
        return DmlEventData(
                table.database,
                table.table,
                null,
                data.rows.map {
                    DmlEventData.parseColumns(it, data.includedColumns, table)
                },
        )
    }
}

class UpdateDmlEventDataDeserializer(
        private val tableMaps: MutableMap<Long, TableMapEventData>,
        private val deserializer: UpdateRowsEventDataDeserializer,
) : EventDataDeserializer<DmlEventData> {
    override fun deserialize(inputStream: ByteArrayInputStream): DmlEventData {
        val data = deserializer.deserialize(inputStream)
        val table = tableMaps[data.tableId] ?: throw IllegalStateException()
        return DmlEventData(
                table.database,
                table.table,
                data.rows.map {
                    DmlEventData.parseColumns(it.key, data.includedColumns, table)
                },
                data.rows.map {
                    DmlEventData.parseColumns(it.value, data.includedColumns, table)
                },
        )
    }
}

class DeleteDmlEventDataDeserializer(
        private val tableMaps: MutableMap<Long, TableMapEventData>,
        private val deserializer: DeleteRowsEventDataDeserializer,
) : EventDataDeserializer<DmlEventData> {
    override fun deserialize(inputStream: ByteArrayInputStream): DmlEventData {
        val data = deserializer.deserialize(inputStream)
        val table = tableMaps[data.tableId] ?: throw IllegalStateException()
        return DmlEventData(
                table.database,
                table.table,
                data.rows.map {
                    DmlEventData.parseColumns(it, data.includedColumns, table)
                },
                null,
        )
    }
}
