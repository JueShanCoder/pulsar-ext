package com.boxuegu.basis.pulsar.qimoor.function.config;


import com.google.gson.Gson;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.pulsar.io.core.annotations.FieldDoc;

import java.io.Serializable;
import java.util.Map;

@Data
@Accessors(chain = true)
public class QiMoorSourceFunctionConfig implements Serializable {

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
            help = " qimoor course types "
    )
    private String courseTypes;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " unclose session topic name  "
    )
    private String unCloseSessionTopicName;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " close session topic name  "
    )
    private String closeSessionTopicName;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " database table name  "
    )
    private String tableName;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " CRM database name  "
    )
    private String crmDatabaseName;

    @FieldDoc(
            required = true,
            defaultValue = "",
            sensitive = true,
            help = " BXG database name  "
    )
    private String bxgDatabaseName;


    public static QiMoorSourceFunctionConfig load(Map<String, Object> map) {
        Gson gson = new Gson();
        return gson.fromJson(gson.toJsonTree(map), QiMoorSourceFunctionConfig.class);
    }

}