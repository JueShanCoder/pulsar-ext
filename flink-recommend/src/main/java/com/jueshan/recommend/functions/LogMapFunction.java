package com.jueshan.recommend.functions;

import com.jueshan.recommend.client.HbaseClient;
import com.jueshan.recommend.entitys.LogEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.MapFunction;

@Slf4j
public class LogMapFunction implements MapFunction<String, LogEntity> {

    @Override
    public LogEntity map(String s) throws Exception {
        log.info("[LogFunction receive {} ]", s);
        LogEntity log = LogEntity.LogToEntity(s);
        String rowKey = log.getUserId() + "_" + log.getProductId()+ "_"+ log.getTime();
        HbaseClient.putData("con",rowKey,"log","userid",String.valueOf(log.getUserId()));
        HbaseClient.putData("con",rowKey,"log","productid",String.valueOf(log.getProductId()));
        HbaseClient.putData("con",rowKey,"log","time",log.getTime().toString());
        HbaseClient.putData("con",rowKey,"log","action",log.getAction());
        return log;
    }
}