package com.jueshan.recommend.task;

import com.jueshan.recommend.entitys.LogEntity;
import com.jueshan.recommend.functions.GetLogFunction;
import com.jueshan.recommend.functions.UserHistoryWithInterestMapFunction;
import com.jueshan.recommend.utils.Property;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

public class UserInterestTask {

    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        Properties properties = Property.getKafkaProperties("interest");
        DataStream<String> dataStream = env.addSource(new FlinkKafkaConsumer<String>("con", new SimpleStringSchema(), properties));
        dataStream.map(new GetLogFunction()).keyBy(LogEntity::getUserId).map(new UserHistoryWithInterestMapFunction());
    }
}