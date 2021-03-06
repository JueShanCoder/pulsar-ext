package com.boxuegu.basis.pulsar.qimoor.source;

import com.boxuegu.basis.pulsar.qimoor.client.QiMoorClient;
import com.boxuegu.basis.pulsar.qimoor.entity.QiMoorWebChat;
import com.boxuegu.basis.pulsar.qimoor.entity.WebChatState;
import com.boxuegu.basis.pulsar.qimoor.service.GetObjectService;
import com.boxuegu.basis.pulsar.qimoor.service.impl.GetStateServiceImpl;
import com.boxuegu.basis.pulsar.qimoor.snowflake.IdWorker;
import com.boxuegu.basis.pulsar.qimoor.source.config.QiMoorSourceConfig;
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
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.io.core.PushSource;
import org.apache.pulsar.io.core.SourceContext;
import org.apache.pulsar.io.core.annotations.Connector;
import org.apache.pulsar.io.core.annotations.IOType;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static com.boxuegu.basis.pulsar.qimoor.service.impl.GetStateServiceImpl.GetStateSQL;
import static com.boxuegu.basis.pulsar.qimoor.service.impl.GetStateServiceImpl.insertState;
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
        AtomicReference<String> beginTime = new AtomicReference<>("");
        AtomicReference<String> endTime = new AtomicReference<>("");
        AtomicInteger pageNum = new AtomicInteger(1);
        try {
            idWorker = new IdWorker(clusterId.longValue(), workerId.longValue());
        } catch (Exception e) {
            throw new IllegalArgumentException(" Initialization snowFlake fail ... ");
        }
        for (; ; ) {
            Connection hiConnection = null;
            try {
                JsonObject jsonObject;
                hiConnection = dataSource.getConnection();
                // state storage by mysql
                GetObjectService getObjectService = new GetStateServiceImpl();
                WebChatState webChatState = (WebChatState) getObjectService.getObject(hiConnection, GetStateSQL(databaseName, stateKey));
                if (webChatState == null) {
                    insertState(hiConnection, databaseName, stateKey, null);
                    webChatState = (WebChatState) getObjectService.getObject(hiConnection, GetStateSQL(databaseName, stateKey));
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
                        log.info("??? beginTime ??? endTime ????????????????????? continue ???");
                        continue;
                    }
                }

                jsonObject = dealWithSession(beginTime.get(), endTime.get(), pageNum.get());
                if (jsonObject == null) {
                    throw new IllegalArgumentException(" Argument jsonObject should not be null ");
                }
                int count = jsonObject.getAsJsonObject("data").get("count").getAsInt();
                // ?????????????????? ?????????
                if (count == 0) {
                    pageNum.set(1);
                    beginTime.set(endTime.get());
                    endTime.set(TimeUtil.getNowWithNoSecond());
                    // state storage by mysql
                    updateOperation(stateKey, beginTime + "_" + endTime + "_" + pageNum);
                } else {
                    List<QiMoorWebChat> qiMoorWebChat = getQiMoorWebChat(jsonObject, idWorker, gson);
                    AtomicBoolean isSuccess = new AtomicBoolean(true);
                    if (!(qiMoorWebChat == null || qiMoorWebChat.isEmpty())) {
                        // ?????? consume ??????????????? sourceContext.newOutputMessage() ??????
                        qiMoorWebChat.forEach(webChat -> {
                            log.info(" webchat status is {} , sessionId is {}",webChat.getStatus(),webChat.get_id());
                            // state storage by mysql
                            try {
                                sourceContext.newOutputMessage(sourceContext.getOutputTopic(), Schema.BYTES).value(gson.toJson(webChat).getBytes(StandardCharsets.UTF_8)).send();
                            } catch (PulsarClientException e) {
                                for (int i = 0; i < 5; i++) {
                                    try {
                                        sourceContext.newOutputMessage(sourceContext.getOutputTopic(), Schema.BYTES).value(gson.toJson(webChat).getBytes(StandardCharsets.UTF_8)).send();
                                        break;
                                    } catch (PulsarClientException exception) {
                                        if (i == 4) {
                                            log.info(" [QimoorSource send message fail ,Maximum number of retries reached, record the current offset value ....]");
                                            pageNum.set(1);
                                            endTime.set(webChat.getCreateTime());
                                            updateOperation(stateKey, beginTime.get() + "_" + endTime.get() + "_" + pageNum.get());
                                            isSuccess.set(false);
                                        }
                                    }
                                }
                            }
                        });
                        if (isSuccess.get()) {
                            pageNum.incrementAndGet();
                            updateOperation(stateKey, beginTime.get() + "_" + endTime.get() + "_" + pageNum.get());
                        }
                    }
                }
            } catch (Exception e) {
                try {
                    log.error(" [QiMoorSource] got Exception, Thread will sleep 1000ms ...", e);
                    Thread.sleep(5000);
                } catch (InterruptedException interruptedException) {
                    log.error(" [QiMoorSource] sleep got exception ...", e);
                }
            } finally {
                if (hiConnection != null) {
                    try {
                        hiConnection.close();
                    } catch (SQLException e) {
                        log.error(" [QiMoorSource] close connection fail ...", e);
                    }
                }
            }
        }
    }

    private JsonObject dealWithSession(String beginTime, String endTime, Integer pageNum) {
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("qimoor", collectQimoor);
            paramMap.put("beginTime", beginTime);
            paramMap.put("endTime", endTime);
            paramMap.put("pageNum", pageNum);
            log.info("???????????????????????? beginTime {}, endTime {}, pageNum {}", beginTime, endTime, pageNum);
            QiMoorClient qiMoorClient = Feign.builder().decoder(new GsonDecoder()).target(QiMoorClient.class, apiAdapterUrl);
            JsonObject jsonObject = qiMoorClient.pastWebChatCollect(paramMap);
            if (jsonObject == null) {
                throw new IllegalArgumentException(" Argument jsonObject should not be null ");
            }
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
    public void close() {
        if (dataSource != null) {
            dataSource.close();
        }
    }

    private void updateOperation(String stateKey, String stateValue) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            int i = GetStateServiceImpl.updateState(connection, databaseName, stateKey, stateValue);
            if (!(i > 0)) {
                log.info(" [ update database fail , Please check the params !!!] ");
            }
        } catch (Exception e) {
            log.error(" [ update state got error... ] ", e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error(" [QiMoorSource] close connection fail ...", e);
                }
            }
        }

    }
}
