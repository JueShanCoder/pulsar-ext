package com.boxuegu.basis.pulsar.qimoor.sonwflake;

public class IdWorker {
    // 2020-01-01T00:00:00.000Z
    public static final long DEFAULT_TWEPOCH = 1577836800000L;

    public IdWorker(long clusterId, long workerId) throws IllegalAccessException {
        this(clusterId, workerId,5L , 5L, 12L,DEFAULT_TWEPOCH );
    }
    public IdWorker(long clusterId, long workerId, long clusterIdBits, long workerIdBits, long sequenceBits, long twepoch) throws IllegalAccessException {
        this.clusterId = clusterId;
        this.workerId = workerId;
        this.clusterIdBits = clusterIdBits;
        this.workerIdBits = workerIdBits;
        this.sequenceBits = sequenceBits;
        this.twepoch = twepoch;
        this.sequenceMask = ~(-1L << sequenceBits);
        // Preconditions
        long maxClusterId = ~(-1L << clusterIdBits);
        long maxWorkerId = ~(-1L << workerIdBits);
        if (clusterId < 0 || clusterId > maxClusterId) {
            throw new IllegalAccessException(String.format("Value of \"clusterId\" must be in the range 0 to %d.", maxClusterId));
        }
        if (workerId < 0 || workerId > maxWorkerId) {
            throw new IllegalAccessException(String.format("Value of \"workerId\" must be in the range 0 to %d.", maxWorkerId));
        }
        if (twepoch <= 0) {
            throw new IllegalAccessException("Value of \"twepoch\" must be greater than 0.");
        }
        if (clusterIdBits <= 0) {
            throw new IllegalAccessException("Value of \"clusterIdBits\" must be greater than 0.");
        }
        if (workerIdBits <= 0) {
            throw new IllegalAccessException("Value of \"workerIdBits\" must be greater than 0.");
        }
        if (sequenceBits <= 0) {
            throw new IllegalAccessException("Value of \"sequenceBits\" must be greater than 0.");
        }
        if (clusterIdBits + workerIdBits + sequenceBits >= 23) {
            throw new IllegalAccessException("Sum of \"clusterIdBits\" \"workerIdBits\" and \"sequenceBits\" must be less than 23.");
        }
    }

    private final long clusterId;
    private final long workerId;
    private final long twepoch;
    private final long clusterIdBits;
    private final long workerIdBits;
    private final long sequenceBits;
    private final long sequenceMask;

    private long sequence;
    private long lastMillis;

    public synchronized long nextLong() {
        long timestamp = System.currentTimeMillis();
        if (timestamp == lastMillis) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0L) {
                long nextMillis = System.currentTimeMillis();
                while (nextMillis <= lastMillis) {
                    nextMillis = System.currentTimeMillis();
                }
                timestamp = nextMillis;
            }
        } else {
            sequence = 0L;
        }
        lastMillis = timestamp;
        return (timestamp - twepoch << clusterIdBits + workerIdBits + sequenceBits) |
                (clusterId << workerIdBits + sequenceBits) |
                (workerId << sequenceBits) |
                sequence;
    }
}
