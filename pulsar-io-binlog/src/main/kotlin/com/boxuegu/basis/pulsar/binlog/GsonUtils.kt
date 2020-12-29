package com.boxuegu.basis.pulsar.binlog

import com.github.shyiko.mysql.binlog.event.Event
import com.github.shyiko.mysql.binlog.event.EventData
import com.github.shyiko.mysql.binlog.event.EventHeader
import com.google.gson.*
import java.io.Serializable
import java.lang.reflect.Type
import java.util.*

class ByteArrayAdapter : JsonSerializer<ByteArray>, JsonDeserializer<ByteArray> {
    override fun serialize(src: ByteArray, type: Type, context: JsonSerializationContext): JsonElement {
        return JsonPrimitive(Base64.getEncoder().encodeToString(src))
    }

    override fun deserialize(json: JsonElement, type: Type, context: JsonDeserializationContext): ByteArray {
        return Base64.getDecoder().decode(json.asString)
    }
}

class ColumnAdapter : JsonSerializer<DmlEventData.Column>, JsonDeserializer<DmlEventData.Column> {
    override fun serialize(column: DmlEventData.Column, type: Type, context: JsonSerializationContext): JsonElement {
        val o = JsonObject()
        o.add("index", context.serialize(column.index))
        o.add("type", context.serialize(column.type))
        o.add("nullable", context.serialize(column.nullable))
        o.add("metadata", context.serialize(column.metadata))
        o.add("value", context.serialize(column.value))
        if (column.value != null) {
            o.addProperty("value.type", column.value::class.java.name)
        }
        return o
    }

    override fun deserialize(json: JsonElement, type: Type, context: JsonDeserializationContext): DmlEventData.Column {
        val o = json.asJsonObject
        val i = o.getAsJsonPrimitive("index")
        val t = o.getAsJsonPrimitive("type")
        val n = o.getAsJsonPrimitive("nullable")
        val m = o.getAsJsonPrimitive("metadata")
        val v = if (!o.has("value.type")) {
            null
        } else {
            context.deserialize<Serializable>(
                o.get("value"),
                Class.forName(o.getAsJsonPrimitive("value.type").asString)
            )
        }
        return DmlEventData.Column(i.asInt, t.asByte, v, n.asBoolean, m.asInt)
    }

}

class EventAdapter : JsonSerializer<Event>, JsonDeserializer<Event> {
    override fun serialize(src: Event, type: Type, context: JsonSerializationContext): JsonElement {
        val e = JsonObject()
        val h = src.getHeader<EventHeader>().let { h ->
            context.serialize(h).also { j ->
                j.asJsonObject.addProperty("@type", h::class.java.name)
            }
        }
        val d = src.getData<EventData>().let { d ->
            context.serialize(d).also { j ->
                j.asJsonObject.addProperty("@type", d::class.java.name)
            }
        }
        e.add("header", h)
        e.add("data", d)
        return e
    }

    override fun deserialize(json: JsonElement, type: Type, context: JsonDeserializationContext): Event {
        val e = json.asJsonObject
        val h: EventHeader = e.getAsJsonObject("header").let { j ->
            context.deserialize(j, Class.forName(j.getAsJsonPrimitive("@type").asString))
        }
        val d: EventData = e.getAsJsonObject("data").let { j ->
            context.deserialize(j, Class.forName(j.getAsJsonPrimitive("@type").asString))
        }
        return Event(h, d)
    }

}

fun GsonBuilder.registerBinlogEventAdapter(): GsonBuilder = this
    .registerTypeAdapter(ByteArray::class.java, ByteArrayAdapter())
    .registerTypeAdapter(DmlEventData.Column::class.java, ColumnAdapter())
    .registerTypeAdapter(Event::class.java, EventAdapter())
