package com.boxuegu.basis.pulsar.qimoor.function.config;


import com.google.gson.Gson;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.pulsar.io.core.annotations.FieldDoc;

import java.io.Serializable;
import java.util.Map;

@Data
@Accessors(chain = true)
public class WebChatMsgFunctionConfig implements Serializable {

    private static final long serialVersionUID = 1L;
    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " snowflake clusterID "
    )
    private Long snowflakeClusterId;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " snowflake workerID  "
    )
    private Long snowflakeWorkerId;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " webChatMsg topic name"
    )
    private String webChatMsgTopicName;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " database table name "
    )
    private String tableName;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " max retry times  "
    )
    private Integer maxRetryTimes;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " retry sleep time "
    )
    private Long retryTime;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " qimoor collect enum  "
    )
    private String collectQimoor;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " api adapter link url  "
    )
    private String apiAdapterUrl;

    public static WebChatMsgFunctionConfig load(Map<String, Object> map) {
        Gson gson = new Gson();
        return gson.fromJson(gson.toJsonTree(map), WebChatMsgFunctionConfig.class);
    }

}