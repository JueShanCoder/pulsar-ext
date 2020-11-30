package com.boxuegu.basis.pulsar.qimoor.function;

import com.boxuegu.basis.pulsar.qimoor.client.QiMoorClient;
import com.boxuegu.basis.pulsar.qimoor.entity.WebChatMsgSink;
import com.boxuegu.basis.pulsar.qimoor.entity.WebChatSink;
import com.boxuegu.basis.pulsar.qimoor.entity.WebchatMessage;
import com.boxuegu.basis.pulsar.qimoor.function.config.WebChatMsgFunctionConfig;
import com.boxuegu.basis.pulsar.qimoor.sonwflake.IdWorker;
import com.boxuegu.basis.pulsar.qimoor.utils.gson.GsonBuilderUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import feign.Feign;
import feign.gson.GsonDecoder;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.beans.BeanCopier;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.functions.api.Context;
import org.apache.pulsar.functions.api.Function;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static com.boxuegu.basis.pulsar.qimoor.utils.TimeUtil.getISO8601TimeByStr;

/**
 * @author : Liuzl
 * @description: 7moor chat msg function
 */
@Slf4j
public class WebChatMsgFunction implements Function<byte[], Void> {
    private  WebChatMsgFunctionConfig webChatMsgFunctionConfig;
    private IdWorker idWorker;
    @Override
    public Void process(byte[] input, Context context) throws InterruptedException {

        webChatMsgFunctionConfig = WebChatMsgFunctionConfig.load(context.getUserConfigMap());
        if (webChatMsgFunctionConfig.getSnowflakeClusterId() == null || webChatMsgFunctionConfig.getSnowflakeWorkerId() == null ||
                webChatMsgFunctionConfig.getWebChatMsgTopicName() == null || webChatMsgFunctionConfig.getTableName() == null ||
                webChatMsgFunctionConfig.getMaxRetryTimes() == null || webChatMsgFunctionConfig.getRetryTime() == null ||
                webChatMsgFunctionConfig.getCollectQimoor() == null || webChatMsgFunctionConfig.getApiAdapterUrl() == null ){
            throw new IllegalArgumentException(" Required parameters are not set... Please check the startup script !!! ");
        }

        try {
            idWorker = new IdWorker(webChatMsgFunctionConfig.getSnowflakeClusterId(),webChatMsgFunctionConfig.getSnowflakeWorkerId());
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(" Snowflake initialization fail ... ");
        }

        Gson gson = GsonBuilderUtil.create(true);
        WebChatSink webChatSink = gson.fromJson(new String(input), WebChatSink.class);
        Map<String, String> properties = new HashMap<>();
        properties.put("ACTION", "INSERT");
        properties.put("TARGET", webChatMsgFunctionConfig.getTableName());
        properties.put("SQLMODE", "INSERT_IGNORE_INVALID");
        if (webChatSink.getMsgCount() != null || webChatSink.getReplyMsgCount() != null) {
            List<WebChatMsgSink> webChatMessages = collectWebChatMessage(webChatMsgFunctionConfig.getCollectQimoor(), webChatSink);
            if (!(webChatMessages == null || webChatMessages.isEmpty())) {
                webChatMessages.forEach(webChat -> {
                    try {
                        context.newOutputMessage(webChatMsgFunctionConfig.getWebChatMsgTopicName(), Schema.BYTES).value(gson.toJson(webChat).getBytes(StandardCharsets.UTF_8)).properties(properties).send();
                        log.info("[WebChatMsgFunction] 聊天记录消息成功发送到 {} 队列 ，[7moor] sid {}  ...", webChatMsgFunctionConfig.getWebChatMsgTopicName(), webChat.getSid());
                    } catch (PulsarClientException e) {
                        log.error("[WebChatMsgFunction] Got PulsarClientException, fail响应，消息即将进入死信队列 ... ", e);
                        context.getCurrentRecord().fail();
                    }
                });
            }
        }
        return null;
    }

    private List<WebChatMsgSink> collectWebChatMessage(String moorEnum, WebChatSink webChat) throws InterruptedException {
        AtomicInteger count = new AtomicInteger();
        List<WebChatMsgSink> webChatMessages = new ArrayList<>();
        boolean success = false;
        long start = System.currentTimeMillis();
        try {
            count.getAndIncrement();
            webChatMessages = pastWebChatMessageCollect(moorEnum, webChat.getSid(), webChat.getSessionId());
            success = true;
        } finally {
            long end = System.currentTimeMillis();
            log.info("\t\tindex:" + count + "\t\tsize:" + webChatMessages.size() + "\t\t\tresult:" + success + "\t\t\ttime:" + (end - start));
        }
        return webChatMessages;
    }

    private List<WebChatMsgSink> pastWebChatMessageCollect(String qimoor, String sid, String sessionId) throws InterruptedException {
        return queryWebchatMessages(qimoor, sid, sessionId);
    }

    /**
     * 查询七陌会话聊天记录
     *
     * @param sid 访客id
     */
    private List<WebChatMsgSink> queryWebchatMessages(String moorEnum, String sid, String sessionId) throws InterruptedException {
        List<WebchatMessage> webchatMessages;
        List<WebChatMsgSink> webChatMsgSinks = new ArrayList<>();
        QiMoorClient qiMoorClient = Feign.builder().decoder(new GsonDecoder()).target(QiMoorClient.class, webChatMsgFunctionConfig.getApiAdapterUrl());
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("qimoor", moorEnum);
        paramMap.put("sid", sid);
        int maxRetryTimes = webChatMsgFunctionConfig.getMaxRetryTimes();
        JsonObject jsonObject = null;
        Gson gson = GsonBuilderUtil.create(false);
        for (int i = 1; i < maxRetryTimes; i++) {
            try {
                jsonObject = qiMoorClient.pastWebChatMessageCollect(paramMap);
                break;
            } catch (Exception e) {
                log.error("[WebChatMsgFunction] 调用7moor接口异常，将进行重试策略... ", e);
                Thread.sleep(webChatMsgFunctionConfig.getRetryTime());
            }
        }
        if (jsonObject == null) {
            log.info("[WebChatMsgFunction] 调用7moor接口异常！sessionId {} 已自动重试{}次 !", sessionId, maxRetryTimes);
            return webChatMsgSinks;
        }
        //返回response对象
        if (jsonObject.get("code").getAsInt() == 200) {
            int count = jsonObject.getAsJsonObject("data").get("count").getAsInt();
            if (count > 0) {
                JsonArray array = jsonObject.getAsJsonObject("data").getAsJsonArray("webchatMessage");
                Type listType = new com.google.gson.reflect.TypeToken<List<WebchatMessage>>() {
                }.getType();
                webchatMessages = gson.fromJson(array, listType);
                for (WebchatMessage msg : webchatMessages) {
                    WebChatMsgSink webChatMsgSink = new WebChatMsgSink();
                    msg.setMessage(filterEmoji(msg.getMessage(), ""));
                    BeanCopier beanCopier = BeanCopier.create(WebchatMessage.class, WebChatMsgSink.class, false);
                    beanCopier.copy(msg, webChatMsgSink, null);
                    webChatMsgSink.setId(idWorker.nextLong());
                    webChatMsgSink.setWeb_chat(gson.toJson(msg));
                    webChatMsgSink.setDateTime(getISO8601TimeByStr(webChatMsgSink.getDateTime()));
                    webChatMsgSinks.add(webChatMsgSink);
                }
            }
        }
        return webChatMsgSinks;
    }

    /**
     * emoji表情替换
     *
     * @param source  原字符串
     * @param slipStr emoji表情替换成的字符串
     * @return 过滤后的字符串
     */
    public static String filterEmoji(String source, String slipStr) {
        if (!(source == null || "".equals(source))) {
            return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", slipStr);
        } else {
            return source;
        }
    }
}