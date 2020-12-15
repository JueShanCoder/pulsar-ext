package com.boxuegu.basis.pulsar.qimoor.function.config;


import com.google.gson.Gson;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.pulsar.io.core.annotations.FieldDoc;

import java.io.Serializable;
import java.util.Map;

@Data
@Accessors(chain = true)
public class UnCloseSessionFunctionConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " snowflake clusterID "
    )
    private String snowflakeClusterId;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " snowflake workerID "
    )
    private String snowflakeWorkerId;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " unclose session topic name "
    )
    private String unCloseSessionTopicName;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " close session topic name "
    )
    private String closeSessionTopicName;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " database table name"
    )
    private String tableName;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " qimoor api adapter link url"
    )
    private String apiAdapterUrl;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " database link url"
    )
    private String jdbcUrl;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " database username "
    )
    private String userName;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " database password "
    )
    private String password;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " max retry times "
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
            help = " qimoor collect enum "
    )
    private String collectQimoor;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " qimoor course types "
    )
    private String courseTypes;

    public static UnCloseSessionFunctionConfig load(Map<String, Object> map) {
        Gson gson = new Gson();
        return gson.fromJson(gson.toJsonTree(map), UnCloseSessionFunctionConfig.class);
    }

}