package com.boxuegu.basis.pulsar.qimoor.source.config;


import com.google.gson.Gson;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.pulsar.io.core.annotations.FieldDoc;

import java.io.Serializable;
import java.util.Map;

@Data
@Accessors(chain = true)
public class QiMoorSourceConfig implements Serializable {

    private static final long serialVersionUID = 1L;

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
            help = " database username  "
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
            help = " snowflakeID clusterID "
    )
    private Integer snowflakeClusterId;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " snowflakeID workerID "
    )
    private Integer snowflakeWorkerId;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " qimoor api adapter services url "
    )
    private String apiAdapterUrl;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " qimoor collect mark "
    )
    private String collectQimoor;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " record the offset of reading qimoor "
    )
    private String offsetBeginTime;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " use state storage to save offset "
    )
    private String offsetStateKey;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " two adjacent requests time interval "
    )
    private String timeDifference;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " time interval switch  "
    )
    private Boolean isOpenTimeDiff;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " database name  "
    )
    private String databaseName;


    public static QiMoorSourceConfig load(Map<String, Object> map) {
        Gson gson = new Gson();
        return gson.fromJson(gson.toJsonTree(map), QiMoorSourceConfig.class);
    }

}