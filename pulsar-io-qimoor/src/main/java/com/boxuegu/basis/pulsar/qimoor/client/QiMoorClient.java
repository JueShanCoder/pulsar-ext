package com.boxuegu.basis.pulsar.qimoor.client;

import com.google.gson.JsonObject;
import feign.QueryMap;
import feign.RequestLine;

import java.util.Map;

public interface QiMoorClient {

    /**
     * 查询7moor会话（根据时间区间）
     */
    @RequestLine("GET /clue/pastWebChatCollect")
    JsonObject pastWebChatCollect(@QueryMap Map<String,Object> map);

    /**
     * 查询7moor聊天记录
     */
    @RequestLine("GET /clue/pastWebChatMessageCollect")
    JsonObject pastWebChatMessageCollect(@QueryMap Map<String,Object> map);

    /**
     * 查询7moor会话（根据会话主键id）
     */
    @RequestLine("GET /clue/inquireSessionStatus")
    JsonObject inquireSessionStatus(@QueryMap Map<String,Object> map);
}
