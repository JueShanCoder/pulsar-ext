package com.jueshan.recommend.functions;

import com.jueshan.recommend.client.HbaseClient;
import com.jueshan.recommend.client.MySqlClient;
import com.jueshan.recommend.entitys.LogEntity;
import org.apache.flink.api.common.functions.MapFunction;

import java.sql.ResultSet;

public class UserPortraitMapFunction implements MapFunction<String,String> {

    @Override
    public String map(String s) throws Exception {
        LogEntity logEntity = LogEntity.LogToEntity(s);
        ResultSet rst = MySqlClient.selectById(logEntity.getProductId());
        if (rst != null){
            while (rst.next()){
                String userId = String.valueOf(logEntity.getUserId());
                String country = rst.getString("country");
                HbaseClient.increamColumn("user",userId,"country",country);
                String color = rst.getString("color");
                HbaseClient.increamColumn("user",userId,"color",color);
                String style = rst.getString("style");
                HbaseClient.increamColumn("user",userId,"style",style);
            }
        }
        return null;
    }
}