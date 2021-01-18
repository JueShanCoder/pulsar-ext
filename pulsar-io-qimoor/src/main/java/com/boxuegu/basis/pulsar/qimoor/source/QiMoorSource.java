package com.boxuegu.basis.pulsar.qimoor.source;

import com.boxuegu.basis.pulsar.qimoor.client.QiMoorClient;
import com.boxuegu.basis.pulsar.qimoor.entity.QiMoorWebChat;
import com.boxuegu.basis.pulsar.qimoor.entity.WebChatState;
import com.boxuegu.basis.pulsar.qimoor.service.GetObjectService;
import com.boxuegu.basis.pulsar.qimoor.service.JdbcService;
import com.boxuegu.basis.pulsar.qimoor.service.impl.GetStateServiceImpl;
import com.boxuegu.basis.pulsar.qimoor.service.impl.JdbcServiceImpl;
import com.boxuegu.basis.pulsar.qimoor.snowflake.IdWorker;
import com.boxuegu.basis.pulsar.qimoor.source.config.QiMoorSourceConfig;
import com.boxuegu.basis.pulsar.qimoor.source.record.QiMoorSourceRecord;
import com.boxuegu.basis.pulsar.qimoor.utils.MessyCodeCheckUtil;
import com.boxuegu.basis.pulsar.qimoor.utils.TimeUtil;
import com.boxuegu.basis.pulsar.qimoor.utils.gson.GsonBuilderUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.zaxxer.hikari.HikariDataSource;
import feign.Feign;
import feign.gson.GsonDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.io.core.PushSource;
import org.apache.pulsar.io.core.SourceContext;
import org.apache.pulsar.io.core.annotations.Connector;
import org.apache.pulsar.io.core.annotations.IOType;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static com.boxuegu.basis.pulsar.qimoor.service.impl.GetStateServiceImpl.GetStateSQL;
import static com.boxuegu.basis.pulsar.qimoor.service.impl.GetStateServiceImpl.insertState;
import static com.boxuegu.basis.pulsar.qimoor.utils.ConvertBuffer.byteBuffer2String;
import static com.boxuegu.basis.pulsar.qimoor.utils.ConvertBuffer.string2ByteBuffer;
import static com.boxuegu.basis.pulsar.qimoor.utils.TimeUtil.timeDiff;


@Connector(
        name = "pulsar-io-qimoor-source",
        type = IOType.SOURCE,
        help = "pulsar-io-qimoor-source",
        configClass = QiMoorSource.class
)
@Slf4j
public class QiMoorSource extends PushSource<byte[]> {

    private String stateKey;
    private String apiAdapterUrl;
    private String collectQimoor;
    private String offsetBeginTime;
    private String timeDiff;
    private Boolean isOpenTimeDiff;
    private Integer clusterId;
    private Integer workerId;
    private String databaseName;
    private HikariDataSource dataSource;

    final Gson gson = GsonBuilderUtil.create(false);

    @Override
    public void open(Map<String, Object> config, SourceContext sourceContext) {
        QiMoorSourceConfig qiMoorSourceConfig = QiMoorSourceConfig.load(config);
        String jdbcUrl = qiMoorSourceConfig.getJdbcUrl();
        String password = qiMoorSourceConfig.getPassword();
        String userName = qiMoorSourceConfig.getUserName();
        databaseName = qiMoorSourceConfig.getDatabaseName();
        apiAdapterUrl = qiMoorSourceConfig.getApiAdapterUrl();
        collectQimoor = qiMoorSourceConfig.getCollectQimoor();
        offsetBeginTime = qiMoorSourceConfig.getOffsetBeginTime();
        timeDiff = qiMoorSourceConfig.getTimeDifference();
        isOpenTimeDiff = qiMoorSourceConfig.getIsOpenTimeDiff();
        stateKey = qiMoorSourceConfig.getOffsetStateKey();
        clusterId = qiMoorSourceConfig.getSnowflakeClusterId();
        workerId = qiMoorSourceConfig.getSnowflakeWorkerId();
        if (apiAdapterUrl == null || collectQimoor == null || offsetBeginTime == null || timeDiff == null ||
                isOpenTimeDiff == null || stateKey == null || clusterId == null || workerId == null ||
                jdbcUrl == null || userName == null || password == null || databaseName == null) {
            throw new IllegalArgumentException(" Required parameters are not set... Please check the startup script !!! ");
        }
        dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        Executors.newSingleThreadExecutor().submit(() -> taskJob(sourceContext));

    }

    private void taskJob(SourceContext sourceContext) {
        IdWorker idWorker;
        final AtomicInteger counter = new AtomicInteger(0);
        AtomicReference<String> beginTime = new AtomicReference<>("");
        AtomicReference<String> endTime = new AtomicReference<>("");
        AtomicInteger pageNum = new AtomicInteger(0);
        Map<String, ByteBuffer> paramsMap = new HashMap<>();
        try {
            idWorker = new IdWorker(clusterId.longValue(), workerId.longValue());
        } catch (Exception e) {
            throw new IllegalArgumentException(" Initialization snowFlake fail ... ");
        }
        for (; ; ) {
            try {
                JsonObject jsonObject;

                // state storage by BK
//                ByteBuffer buffer = sourceContext.getState(stateKey);
//                if (buffer != null) {
//                    String state = byteBuffer2String(buffer, StandardCharsets.UTF_8);
//                    String[] offSet = state.split("_");
//                    beginTime.set(offSet[0]);
//                    endTime.set(offSet[1]);
//                    pageNum.set(Integer.parseInt(offSet[2]));
//                } else {
//                    beginTime.set(offsetBeginTime);
//                    endTime.set(TimeUtil.getNowWithNoSecond());
//                    pageNum.set(1);
//                }

                // state storage by mysql
                GetObjectService getObjectService = new GetStateServiceImpl();
                WebChatState webChatState = (WebChatState) getObjectService.getObject(dataSource.getConnection(), GetStateSQL(databaseName,stateKey));
                if (webChatState == null) {
                    insertState(dataSource.getConnection(), databaseName ,stateKey, null);
                    webChatState = (WebChatState) getObjectService.getObject(dataSource.getConnection(), GetStateSQL(databaseName,stateKey));
                }
                String stateValue = webChatState.getValue();
                if (stateValue != null) {
                    String[] offSet = stateValue.split("_");
                    beginTime.set(offSet[0]);
                    endTime.set(offSet[1]);
                    pageNum.set(Integer.parseInt(offSet[2]));
                } else {
                    beginTime.set(offsetBeginTime);
                    endTime.set(TimeUtil.getNowWithNoSecond());
                    pageNum.set(1);
                }

                if (isOpenTimeDiff) {
                    if (timeDiff(beginTime.get(), endTime.get()) > Integer.parseInt(timeDiff)) {
                        log.info("【 beginTime 和 endTime 时间间隔太短！ continue 】");
                        continue;
                    }
                }

                jsonObject = dealWithSession(beginTime.get(), endTime.get(), pageNum);
                if (jsonObject == null) {
                    throw new IllegalArgumentException(" Argument jsonObject should not be null ");
                }
                int count = jsonObject.getAsJsonObject("data").get("count").getAsInt();
                log.info("[  beginTime {} ，endTime {} ， pageNumber {} ，count {} ] ", beginTime, endTime.get(), pageNum, count);
                // 当前时间区间 无数据
                if (count == 0) {
                    pageNum.set(1);
                    beginTime.set(endTime.get());
                    endTime.set(TimeUtil.getNowWithNoSecond());
                    // state storage by BK
//                    sourceContext.putState(stateKey, string2ByteBuffer(beginTime + "_" + endTime + "_" + pageNum, StandardCharsets.UTF_8));
                    // state storage by mysql
                    updateOperation(stateKey, beginTime + "_" + endTime + "_" + pageNum);
                } else {
                    List<QiMoorWebChat> qiMoorWebChat = getQiMoorWebChat(jsonObject, idWorker, gson);
                    if (!(qiMoorWebChat == null || qiMoorWebChat.isEmpty())) {
                        qiMoorWebChat.forEach(webChat -> consume(new QiMoorSourceRecord(webChat,
                                (v) -> {
                                    if (counter.get() < qiMoorWebChat.size()) {
                                        counter.incrementAndGet();
                                        paramsMap.put(stateKey, string2ByteBuffer(beginTime.get() + "_" + endTime.get() + "_" + pageNum.get(), StandardCharsets.UTF_8));
                                    } else {
                                        counter.set(0);
                                        pageNum.incrementAndGet();
                                        // state storage by BK
//                                        sourceContext.putState(stateKey, string2ByteBuffer(beginTime.get() + "_" + endTime.get() + "_" + pageNum.get(), StandardCharsets.UTF_8));

                                        // state storage by mysql
                                        updateOperation(stateKey, beginTime.get() + "_" + endTime.get() + "_" + pageNum.get());
                                    }
                                },
                                (v) -> {
                                    endTime.set(webChat.getCreateTime());
                                    pageNum.set(1);
                                    log.info(" receive fail response .. ");
                                    // state storage by BK
//                                    sourceContext.putState(stateKey, paramsMap.get(stateKey));

                                    // state storage by mysql
                                    String state = byteBuffer2String(paramsMap.get(stateKey), StandardCharsets.UTF_8);
                                    updateOperation(stateKey, state);

                                })));
                    }
                }
            } catch (Exception e) {
                log.error("[QiMoorSource] got Exception ...", e);
            }
        }
    }

    private JsonObject dealWithSession(String beginTime, String endTime, AtomicInteger pageNum) {
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("qimoor", collectQimoor);
            paramMap.put("beginTime", beginTime);
            paramMap.put("endTime", endTime);
            paramMap.put("pageNum", pageNum.get());
            QiMoorClient qiMoorClient = Feign.builder().decoder(new GsonDecoder()).target(QiMoorClient.class, apiAdapterUrl);
            JsonObject jsonObject = qiMoorClient.pastWebChatCollect(paramMap);
            if (jsonObject == null) {
                throw new IllegalArgumentException(" Argument jsonObject should not be null ");
            }
            jsonObject.getAsJsonObject("data").addProperty("beginTimeSource", beginTime);
            if (jsonObject.get("code").getAsInt() != 200)
                return null;
            return jsonObject;
        } catch (Exception e) {
            log.error("[ Got exception ]", e);
            throw e;
        }
    }

    public static List<QiMoorWebChat> getQiMoorWebChat(JsonObject jsonObject, IdWorker idWorker, Gson gson) {
        List<QiMoorWebChat> list;

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

    @Override
    public void close() throws Exception {
        if (dataSource != null) {
            dataSource.close();
        }
    }

    private void updateOperation(String stateKey, String stateValue) {
        try {
            int i = GetStateServiceImpl.updateState(dataSource.getConnection(), databaseName, stateKey, stateValue);
            if (!(i > 0)) {
                log.info(" [ update database fail , Please check the params !!!] ");
            }
        } catch (Exception e) {
            log.error(" [ update state got error... ] ", e);
        }

    }
}
