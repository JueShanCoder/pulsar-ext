package com.boxuegu.basis.pulsar.binlog

import org.apache.pulsar.io.core.SinkContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.ByteBuffer
import java.util.concurrent.CompletableFuture

class MockSinkContext : SinkContext {
    companion object {
        private val LOGGER = LoggerFactory.getLogger(MockSinkContext::class.java)
        private val STATUS = mutableMapOf<String, ByteArray>()
        private val COUNTS = mutableMapOf<String, Long>()
    }

    override fun getInstanceId(): Int = 0

    override fun getNumInstances(): Int = 1

    override fun recordMetric(metricName: String, value: Double) {
        LOGGER.trace("Logged metric $metricName: $value")
    }

    override fun getInputTopics(): Collection<String> = listOf("persistent://public/default/unittest")

    override fun getTenant(): String = "public"

    override fun getNamespace(): String = "default"

    override fun getSinkName(): String = "SinkForUnitTest"

    override fun getLogger(): Logger = LOGGER

    override fun getSecret(secretName: String): String? = null

    override fun incrCounter(key: String, amount: Long) {
        COUNTS[key] = getCounter(key) + amount
    }

    override fun incrCounterAsync(key: String, amount: Long): CompletableFuture<Void> {
        return CompletableFuture.runAsync { incrCounter(key, amount) }
    }

    override fun getCounter(key: String): Long {
        return COUNTS[key] ?: 0
    }

    override fun getCounterAsync(key: String): CompletableFuture<Long> {
        return CompletableFuture.completedFuture(getCounter(key))
    }

    override fun putState(key: String, value: ByteBuffer?) {
        if (value == null) {
            STATUS.remove(key)
        } else {
            val bytes = ByteArray(value.remaining())
            value.get(bytes)
            STATUS[key] = bytes
        }
    }

    override fun putStateAsync(key: String, value: ByteBuffer?): CompletableFuture<Void> {
        return CompletableFuture.runAsync { putState(key, value) }
    }

    override fun getState(key: String): ByteBuffer? {
        return STATUS[key]?.let { ByteBuffer.wrap(it) }
    }

    override fun getStateAsync(key: String): CompletableFuture<ByteBuffer?> {
        return CompletableFuture.completedFuture(getState(key))
    }
}
