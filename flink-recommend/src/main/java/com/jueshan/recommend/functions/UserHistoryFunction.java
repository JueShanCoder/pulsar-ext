package com.jueshan.recommend.functions;

import com.jueshan.recommend.client.HbaseClient;
import com.jueshan.recommend.entitys.LogEntity;
import org.apache.flink.api.common.functions.MapFunction;

public class UserHistoryFunction implements MapFunction<String,String> {

    /**
     * 将 用户 - 产品 和 产品 - 用户 分别存储Hbase表
     * @param s kafka消费数据
     */
    @Override
    public String map(String s) throws Exception {
        LogEntity log = LogEntity.LogToEntity(s);
        HbaseClient.increamColumn("u_history",String.valueOf(log.getUserId()),"p",String.valueOf(log.getProductId()));
        HbaseClient.increamColumn("p_history",String.valueOf(log.getProductId()),"p",String.valueOf(log.getUserId()));
        return "";
    }
}