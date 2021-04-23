package com.jueshan.recommend.task;

import com.jueshan.recommend.functions.LogMapFunction;
import com.jueshan.recommend.utils.Property;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

public class LogTask {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        Properties properties = Property.getKafkaProperties("log");
        DataStream<String> dataStream = env.addSource(new FlinkKafkaConsumer<String>("con", new SimpleStringSchema(), properties));
        dataStream.map(new LogMapFunction());
        env.execute("Log message receive");
    }
}