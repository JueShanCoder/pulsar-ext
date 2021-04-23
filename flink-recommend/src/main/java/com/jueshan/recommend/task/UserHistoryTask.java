package com.jueshan.recommend.task;

import com.jueshan.recommend.functions.UserHistoryFunction;
import com.jueshan.recommend.utils.Property;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

public class UserHistoryTask {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        Properties properties = Property.getKafkaProperties("history");
        DataStreamSource<String> dataStream = env.addSource(new FlinkKafkaConsumer<String>("conn", new SimpleStringSchema(), properties));
        dataStream.map(new UserHistoryFunction());
        env.execute("User Product History");
    }
}