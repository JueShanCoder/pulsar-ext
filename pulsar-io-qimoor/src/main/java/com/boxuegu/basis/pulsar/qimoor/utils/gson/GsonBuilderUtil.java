package com.boxuegu.basis.pulsar.qimoor.utils.gson;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;

/**
 * @description: 创建Gson对象工具类
 * @create: 2019-06-26 17:54
 **/
public class GsonBuilderUtil {
    public static Gson create(Boolean isNeedPolicy) {
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(java.util.Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG);
        gb.registerTypeAdapter(java.util.Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG);
        if (isNeedPolicy) {
            gb.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        }
        gb.setDateFormat("yyyy-MM-dd HH:mm:ss");
        return gb.create();
    }
}
