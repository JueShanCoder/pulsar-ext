package com.boxuegu.basis.pulsar.qimoor.function;

import com.boxuegu.basis.pulsar.qimoor.client.QiMoorClient;
import com.boxuegu.basis.pulsar.qimoor.entity.QiMoorWebChat;
import com.boxuegu.basis.pulsar.qimoor.entity.WebChatSink;
import com.boxuegu.basis.pulsar.qimoor.function.config.UnCloseSessionFunctionConfig;
import com.boxuegu.basis.pulsar.qimoor.snowflake.IdWorker;
import com.boxuegu.basis.pulsar.qimoor.utils.gson.GsonBuilderUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import feign.Feign;
import feign.gson.GsonDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.functions.api.Context;
import org.apache.pulsar.functions.api.Function;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.boxuegu.basis.pulsar.qimoor.function.QiMoorSourceFunction.parseSession;
import static com.boxuegu.basis.pulsar.qimoor.source.QiMoorSource.getQiMoorWebChat;

/**
 * @author : Liuzl
 * @description: 7moor unClose Session function
 */
@Slf4j
public class UnCloseSessionFunction implements Function<byte[], Void> {

    final Gson gson = GsonBuilderUtil.create(false);

    @Override
    public Void process(byte[] input, Context context) throws InterruptedException {
        UnCloseSessionFunctionConfig unCloseSessionFunctionConfig = UnCloseSessionFunctionConfig.load(context.getUserConfigMap());
        if (unCloseSessionFunctionConfig.getApiAdapterUrl() == null || unCloseSessionFunctionConfig.getCollectQimoor() == null ||
                unCloseSessionFunctionConfig.getJdbcUrl() == null || unCloseSessionFunctionConfig.getMaxRetryTimes() == null ||
                unCloseSessionFunctionConfig.getPassword() == null || unCloseSessionFunctionConfig.getCourseTypes() == null ||
                unCloseSessionFunctionConfig.getSnowflakeClusterId() == null || unCloseSessionFunctionConfig.getSnowflakeWorkerId() == null ||
                unCloseSessionFunctionConfig.getCrmDatabaseName() == null || unCloseSessionFunctionConfig.getBxgDatabaseName() == null) {
            throw new IllegalArgumentException(" Required parameters are not set... Please check the startup script !!! ");
        }

        QiMoorWebChat qiMoorWebChat = gson.fromJson(new String(input), QiMoorWebChat.class);
        Map<String, String> properties = new HashMap<>();
        properties.put("ACTION", "INSERT");
        properties.put("TARGET", unCloseSessionFunctionConfig.getTableName());
        properties.put("SQLMODE", "INSERT_IGNORE_INVALID");
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("qimoor", unCloseSessionFunctionConfig.getCollectQimoor());
        paramMap.put("_id", qiMoorWebChat.get_id());
        JsonObject jsonObject = null;
        int maxRetryTimes = unCloseSessionFunctionConfig.getMaxRetryTimes();
        // 调用七陌查询会话是否完成
        for (int retry = 1; retry < maxRetryTimes; retry++) {
            try {
                QiMoorClient qiMoorClient = Feign.builder().decoder(new GsonDecoder()).target(QiMoorClient.class, unCloseSessionFunctionConfig.getApiAdapterUrl());
                jsonObject = qiMoorClient.inquireSessionStatus(paramMap);
                break;
            } catch (Exception e) {
                log.error("[UnCloseSessionFunction] 调用7moor接口异常，将进行重试策略... ", e);
                Thread.sleep(unCloseSessionFunctionConfig.getRetryTime());
            }
        }
        if (jsonObject == null) {
            log.info("[UnCloseSessionFunction] 调用7moor接口异常！sessionId {} 已自动重试{}次 !", qiMoorWebChat.get_id(), maxRetryTimes);
            return null;
        }
        IdWorker idWorker;
        try {
            idWorker = new IdWorker(Long.parseLong(unCloseSessionFunctionConfig.getSnowflakeClusterId()),
                    Long.parseLong(unCloseSessionFunctionConfig.getSnowflakeWorkerId()));
        } catch (IllegalAccessException e) {
            log.error("[unCloseSessionFunction] got IllegalAccessException");
            return null;
        }
        List<QiMoorWebChat> webChats = getQiMoorWebChat(jsonObject, idWorker, gson);
        if (!(webChats == null || webChats.isEmpty())) {
            webChats.forEach(webChat -> {
                log.info("[UnCloseSessionFunction ] sessionId is {} , session status is {} ",webChat.get_id(), webChat.getStatus());
                if (!webChat.getStatus().equalsIgnoreCase("finish") && !webChat.getStatus().equalsIgnoreCase("invalid")) {
                    try {
                        context.newOutputMessage(unCloseSessionFunctionConfig.getUnCloseSessionTopicName(), Schema.BYTES).value(gson.toJson(webChat).getBytes(StandardCharsets.UTF_8)).deliverAfter(3L, TimeUnit.MINUTES).send();

                        context.getCurrentRecord().ack();
                        log.info("[UnCloseSessionFunction] 会话仍为未完成状态，[7moor] sessionId {},Id {}, 消息成功发送到 {} 队列...", webChat.get_id(), webChat.getId(), unCloseSessionFunctionConfig.getUnCloseSessionTopicName());
                    } catch (PulsarClientException e) {
                        log.error("[UnCloseSessionFunction] Got PulsarClientException, fail响应，消息即将进入死信队列 ...]", e);
                        context.getCurrentRecord().fail();
                    }
                } else if (webChat.getStatus().equalsIgnoreCase("finish") || webChat.getStatus().equalsIgnoreCase("invalid")) {
                    try {
                        WebChatSink webChatSink = parseSession(webChat, gson, unCloseSessionFunctionConfig.getCourseTypes(), unCloseSessionFunctionConfig.getJdbcUrl(),
                                unCloseSessionFunctionConfig.getUserName(), unCloseSessionFunctionConfig.getPassword(), unCloseSessionFunctionConfig.getCrmDatabaseName(), unCloseSessionFunctionConfig.getBxgDatabaseName());
                        context.newOutputMessage(unCloseSessionFunctionConfig.getCloseSessionTopicName(), Schema.BYTES).value(gson.toJson(webChatSink).getBytes(StandardCharsets.UTF_8)).properties(properties).send();
                        context.getCurrentRecord().ack();
                        log.info("[UnCloseSessionFunction] 会话为已完成状态，[7moor] sessionId {},Id {}, 消息成功发送到 {} 队列...", webChat.get_id(), webChat.getId(), unCloseSessionFunctionConfig.getCloseSessionTopicName());
                    } catch (Exception e) {
                        log.error("[UnCloseSessionFunction] Got exception, fail响应，消息即将进入死信队列 ...]", e);
                        context.getCurrentRecord().fail();
                    }
                }
            });
        }
        return null;
    }
}