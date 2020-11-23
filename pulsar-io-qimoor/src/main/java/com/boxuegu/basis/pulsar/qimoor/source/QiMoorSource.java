package com.boxuegu.basis.pulsar.qimoor.source;

import com.boxuegu.basis.pulsar.qimoor.client.QiMoorClient;
import com.boxuegu.basis.pulsar.qimoor.entity.QiMoorWebChat;
import com.boxuegu.basis.pulsar.qimoor.sonwflake.IdWorker;
import com.boxuegu.basis.pulsar.qimoor.utils.MessyCodeCheckUtil;
import com.boxuegu.basis.pulsar.qimoor.utils.TimeUtil;
import com.boxuegu.basis.pulsar.qimoor.utils.gson.GsonBuilderUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import feign.Feign;
import feign.gson.GsonDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.io.core.PushSource;
import org.apache.pulsar.io.core.SourceContext;
import org.apache.pulsar.io.core.annotations.Connector;
import org.apache.pulsar.io.core.annotations.IOType;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import static com.boxuegu.basis.pulsar.qimoor.utils.ConvertBuffer.byteBuffer2String;
import static com.boxuegu.basis.pulsar.qimoor.utils.ConvertBuffer.string2ByteBuffer;
import static com.boxuegu.basis.pulsar.qimoor.utils.PropertiesUtils.getProperties;
import static com.boxuegu.basis.pulsar.qimoor.utils.TimeUtil.timeDiff;


@Connector(
        name = "pulsar-io-qimoor-source",
        type = IOType.SOURCE,
        help = "pulsar-io-qimoor-source",
        configClass = QiMoorSource.class
)
@Slf4j
public class QiMoorSource extends PushSource<byte[]> {

    private IdWorker idWorker;
    private String stateKey;
    private String apiAdapterUrl;
    private String qimoorSourceTopicName;
    private String offsetBeginTime;
    private String timeDiff;
    private Boolean isOpenTimeDiff;
    private String collectQimoor;

    private static final ExecutorService executorService;
    static {
        int nThreads = Runtime.getRuntime().availableProcessors() * 2;
        int queueSize = 1024;
        ThreadPoolExecutor es = new ThreadPoolExecutor(1, nThreads, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(queueSize));
        es.setRejectedExecutionHandler((runnable, executor) -> {
            log.warn("mail queue overflow, nThreads:{} queueSize:{}", nThreads, queueSize);
            runnable.run();
        });
        executorService = es;
    }

    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void open(Map<String, Object> config, SourceContext sourceContext) {
        Gson gson = GsonBuilderUtil.create(false);
        try {
            idWorker = new IdWorker(((Double) config.get("snowflake-cluster-id")).longValue(), ((Double) config.get("snowflake-worker-id")).longValue());
            apiAdapterUrl = (String) config.get("api-adapter.url");
            qimoorSourceTopicName = (String) config.get("qimoor-source-topic-name");
            offsetBeginTime = (String) config.get("offset-begin-time");
            timeDiff = (String) config.get("time-difference");
            isOpenTimeDiff = (Boolean) config.get("is-open-time-diff");
            stateKey = (String) config.get("offset-state-key");
            collectQimoor = (String) config.get("collect-qimoor");
        }catch (Exception e){
            log.error(" [QiMoorSource] got Config Exception",e);
            return;
        }
        for (;;) {
            try {
                ByteBuffer buffer = sourceContext.getState(stateKey);
                String beginTime;
                String endTime;
                String pageNum;
                JsonObject jsonObject;
                if (buffer != null) {
                    String state = byteBuffer2String(buffer, StandardCharsets.UTF_8);
                    String[] offSet = state.split("_");
                    beginTime = offSet[0];
                    endTime = offSet[1];
                    pageNum = offSet[2];
                } else {
                    beginTime = offsetBeginTime;
                    endTime = TimeUtil.getNowWithNoSecond();
                    pageNum = "1";
                }
                if (isOpenTimeDiff) {
                    if (timeDiff(beginTime, endTime) > Integer.parseInt(timeDiff)) {
                        log.info("【 beginTime 和 endTime 时间间隔太短！ continue 】");
                        continue;
                    }
                }

                jsonObject = dealWithSession(beginTime, endTime, pageNum, sourceContext);
                if (jsonObject == null){
                    throw new IllegalArgumentException(" Argument jsonObject should not be null ");
                }
                CompletableFuture.runAsync(() -> {
                    List<QiMoorWebChat> qiMoorWebChat = getQiMoorWebChat(jsonObject, idWorker);
                    if (!(qiMoorWebChat == null || qiMoorWebChat.isEmpty())) {
                        qiMoorWebChat.forEach(webChat -> {
                            byte[] webChatByte = gson.toJson(webChat).getBytes(StandardCharsets.UTF_8);
                            try {
                                sourceContext.newOutputMessage(qimoorSourceTopicName, Schema.BYTES).value(webChatByte).send();
                            } catch (PulsarClientException e) {
                                log.error("[QiMoorSource] sourceContext 发送异常！ ", e);
                            }
                        });
                    }
                }, executorService);
            }catch (Exception e){
                log.error("[QiMoorSource] got Exception ...", e);
            }
        }
    }

    @Override
    public void close() {
    }

    private JsonObject dealWithSession(String beginTime, String endTime, String pageNum,SourceContext sourceContext){
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("qimoor", collectQimoor);
            paramMap.put("beginTime", beginTime);
            paramMap.put("endTime", endTime);
            paramMap.put("pageNum", pageNum);
            QiMoorClient qiMoorClient = Feign.builder().decoder(new GsonDecoder()).target(QiMoorClient.class,apiAdapterUrl);
            JsonObject jsonObject = qiMoorClient.pastWebChatCollect(paramMap);
            if (jsonObject == null){
                throw new IllegalArgumentException(" Argument jsonObject should not be null ");
            }
            jsonObject.getAsJsonObject("data").addProperty("beginTimeSource",beginTime);
            if (jsonObject.get("code").getAsInt() != 200)
                return null;

            int count = jsonObject.getAsJsonObject("data").get("count").getAsInt();
            log.info("[  beginTime {} ，endTime {} ， pageNumber {} ，count {} ] ", beginTime, endTime, pageNum, count);
            if (count == 0) {
                pageNum = "1";
                beginTime = endTime;
                endTime = TimeUtil.getNowWithNoSecond();
                sourceContext.putState(stateKey, string2ByteBuffer(beginTime + "_" + endTime + "_" + pageNum, StandardCharsets.UTF_8));
            } else {
                int i = Integer.parseInt(pageNum);
                i++;
                sourceContext.putState(stateKey, string2ByteBuffer(beginTime + "_" + endTime + "_" + i, StandardCharsets.UTF_8));
            }
            return jsonObject;
        }catch (Exception e){
            log.error("[ Got exception ]",e);
            throw e;
        }
    }

    public static List<QiMoorWebChat> getQiMoorWebChat(JsonObject jsonObject,IdWorker idWorker) {
        List<QiMoorWebChat> list;
        Gson gson = GsonBuilderUtil.create(false);
        JsonArray ja = jsonObject.getAsJsonObject("data").getAsJsonArray("webchatSession");
        list = gson.fromJson(ja.toString(), new TypeToken<List<QiMoorWebChat>>() {
        }.getType());
        for (QiMoorWebChat webChat : list) {
            if (webChat.getUbaInfo() != null) {
                Map<String, String> map = webChat.getUbaInfo();
                if (map.containsKey("platformDescription")) {
                    webChat.setPlatformDescription(map.get("platformDescription"));
                }
                if (map.containsKey("browserName")) {
                    webChat.setBrowserName(map.get("browserName"));
                }
                if (map.containsKey("osInfo")) {
                    webChat.setOsInfo(map.get("osInfo"));
                }
            }
            if (webChat.getOtherParams() != null) {
                webChat.setOtherParamsStr(gson.toJson(webChat.getOtherParams()));
            }
            if (webChat.getHistory() != null) {
                webChat.setHistoryStr(gson.toJson(webChat.getHistory()));
            }
            if (webChat.getUrlTitle() != null && MessyCodeCheckUtil.isMessyCode(webChat.getUrlTitle())) {
                webChat.setUrlTitle("");
            }
            if (idWorker != null)
                webChat.setId(idWorker.nextLong());
        }
        return list;
    }
}
