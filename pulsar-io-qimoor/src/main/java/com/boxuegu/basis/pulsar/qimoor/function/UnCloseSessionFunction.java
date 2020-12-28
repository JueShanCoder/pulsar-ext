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

    Gson gson = GsonBuilderUtil.create(false);

    @Override
    public Void process(byte[] input, Context context) throws InterruptedException {
        UnCloseSessionFunctionConfig unCloseSessionFunctionConfig = UnCloseSessionFunctionConfig.load(context.getUserConfigMap());
        if (unCloseSessionFunctionConfig.getApiAdapterUrl() == null || unCloseSessionFunctionConfig.getCollectQimoor() == null ||
                unCloseSessionFunctionConfig.getJdbcUrl() == null || unCloseSessionFunctionConfig.getMaxRetryTimes() == null ||
                unCloseSessionFunctionConfig.getPassword() == null || unCloseSessionFunctionConfig.getCourseTypes() == null){
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
            idWorker = new IdWorker((Long.parseLong((String) context.getUserConfigMap().get("snowflake-cluster-id"))),
                    (Long.parseLong((String) context.getUserConfigMap().get("snowflake-worker-id"))));
        } catch (IllegalAccessException e) {
            log.error("[unCloseSessionFunction] got IllegalAccessException");
            return null;
        }
        List<QiMoorWebChat> webChats = getQiMoorWebChat(jsonObject, idWorker,gson);
        if (!(webChats == null || webChats.isEmpty())) {
            webChats.forEach(webChat -> {
                if (!webChat.getStatus().equalsIgnoreCase("finish") && !webChat.getStatus().equalsIgnoreCase("invalid")) {
                    context.getUserConfigValue("un-close-session-topic-name").ifPresent(topicName -> {
                        try {
                            context.newOutputMessage((String) topicName, Schema.BYTES).value(gson.toJson(webChat).getBytes(StandardCharsets.UTF_8)).deliverAfter(3L, TimeUnit.MINUTES);
                            context.getCurrentRecord().ack();
                            log.info("[UnCloseSessionFunction] 会话仍为未完成状态，[7moor] sessionId {},Id {}, 消息成功发送到 {} 队列...", webChat.get_id(), webChat.getId(), topicName);
                        } catch (PulsarClientException e) {
                            log.error("[UnCloseSessionFunction] Got PulsarClientException, fail响应，消息即将进入死信队列 ...]", e);
                            context.getCurrentRecord().fail();
                        }
                    });
                } else if (qiMoorWebChat.getStatus().equalsIgnoreCase("finish") || qiMoorWebChat.getStatus().equalsIgnoreCase("invalid")) {
                    context.getUserConfigValue("close-session-topic-name").ifPresent(topicName -> {
                        try {
                            WebChatSink webChatSink = parseSession(qiMoorWebChat, gson, unCloseSessionFunctionConfig.getCourseTypes(), unCloseSessionFunctionConfig.getJdbcUrl(), unCloseSessionFunctionConfig.getUserName(), unCloseSessionFunctionConfig.getPassword());
                            context.newOutputMessage((String) topicName, Schema.BYTES).value(gson.toJson(webChatSink).getBytes(StandardCharsets.UTF_8)).properties(properties).send();
                            context.getCurrentRecord().ack();
                            log.info("[UnCloseSessionFunction] 会话为已完成状态，[7moor] sessionId {},Id {}, 消息成功发送到 {} 队列...", webChat.get_id(), webChat.getId(), topicName);
                        } catch (Exception e) {
                            log.error("[UnCloseSessionFunction] Got exception, fail响应，消息即将进入死信队列 ...]", e);
                            context.getCurrentRecord().fail();
                        }

                    });
                }
            });
        }
        return null;
    }
}