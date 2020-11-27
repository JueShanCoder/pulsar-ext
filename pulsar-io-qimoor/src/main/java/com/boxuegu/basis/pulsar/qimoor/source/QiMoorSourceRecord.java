package com.boxuegu.basis.pulsar.qimoor.source;

import com.boxuegu.basis.pulsar.qimoor.entity.QiMoorWebChat;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.functions.api.Record;

import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

@Slf4j
public class QiMoorSourceRecord implements Record<byte[]> {

    private final QiMoorWebChat qiMoorWebChat;
    private final Consumer<QiMoorWebChat> ackCommit;
    private final Consumer<QiMoorWebChat> failCommit;

    public QiMoorSourceRecord(QiMoorWebChat qiMoorWebChat, Consumer<QiMoorWebChat> ackCommit,
                              Consumer<QiMoorWebChat> failCommit) {
        this.qiMoorWebChat = qiMoorWebChat;
        this.ackCommit = ackCommit;
        this.failCommit = failCommit;
    }

    @Override
    public byte[] getValue() {
        try {
            return new Gson().toJson(qiMoorWebChat).getBytes(StandardCharsets.UTF_8);
        } catch (RuntimeException ex) {
            log.warn("Error when serialize event to bytes", ex);
            throw ex;
        }
    }

    @Override
    public void ack() {
        log.info(" ack receive ...");
        ackCommit.accept(qiMoorWebChat);
    }

    @Override
    public void fail() {
        log.info(" fail receive ...");
        failCommit.accept(qiMoorWebChat);
    }
}
