package com.boxuegu.basis.pulsar.qimoor.function;

import com.boxuegu.basis.pulsar.qimoor.client.QiMoorClient;
import com.boxuegu.basis.pulsar.qimoor.entity.QiMoorWebChat;
import com.boxuegu.basis.pulsar.qimoor.entity.WebChatSink;
import com.boxuegu.basis.pulsar.qimoor.sonwflake.IdWorker;
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

    private String jdbcUrl;
    private String userName;
    private String password;
    private String courseTypes;

    Gson gson;

    @Override
    public Void process(byte[] input, Context context) throws InterruptedException {
        String tableName;
        String apiAdapterUrl;
        String collectQimoor;
        String maxTimes;
        String retryTime;
        try {
            tableName = (String) context.getUserConfigMap().get("table-name");
            apiAdapterUrl = (String) context.getUserConfigMap().get("api-adapter-url");
            jdbcUrl = (String) context.getUserConfigMap().get("jdbc-url");
            userName = (String) context.getUserConfigMap().get("user-name");
            password = (String) context.getUserConfigMap().get("password");
            collectQimoor = (String) context.getUserConfigMap().get("collect-qimoor");
            maxTimes = (String) context.getUserConfigMap().get("max-retry-times");
            retryTime = (String) context.getUserConfigMap().get("retry-time");
            courseTypes = (String) context.getUserConfigMap().get("course-types");
        } catch (Exception e) {
            log.error("[UnCloseSessionFunction] init config got exception ", e);
            return null;
        }
        gson = GsonBuilderUtil.create(false);
        QiMoorWebChat qiMoorWebChat = gson.fromJson(new String(input), QiMoorWebChat.class);
        Map<String, String> properties = new HashMap<>();
        properties.put("ACTION", "INSERT");
        properties.put("TARGET", tableName);
        properties.put("SQLMODE", "INSERT_IGNORE_INVALID");
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("qimoor", collectQimoor);
        paramMap.put("_id", qiMoorWebChat.get_id());
        JsonObject jsonObject = null;
        int maxRetryTimes = Integer.parseInt(maxTimes);
        // 调用七陌查询会话是否完成
        for (int retry = 1; retry < maxRetryTimes; retry++) {
            try {
                QiMoorClient qiMoorClient = Feign.builder().decoder(new GsonDecoder()).target(QiMoorClient.class, apiAdapterUrl);
                jsonObject = qiMoorClient.inquireSessionStatus(paramMap);
                break;
            } catch (Exception e) {
                log.error("[UnCloseSessionFunction] 调用7moor接口异常，将进行重试策略... ", e);
                Thread.sleep(Long.parseLong(retryTime));
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
        List<QiMoorWebChat> webChats = getQiMoorWebChat(jsonObject, idWorker);
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
                            WebChatSink webChatSink = parseSession(qiMoorWebChat, gson, courseTypes, jdbcUrl, userName, password);
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