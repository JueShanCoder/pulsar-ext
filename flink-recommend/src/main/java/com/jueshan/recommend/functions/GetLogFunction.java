package com.jueshan.recommend.functions;

import com.jueshan.recommend.entitys.LogEntity;
import org.apache.flink.api.common.functions.MapFunction;

public class GetLogFunction implements MapFunction<String, LogEntity> {

    @Override
    public LogEntity map(String s) throws Exception {
        return LogEntity.LogToEntity(s);
    }
}