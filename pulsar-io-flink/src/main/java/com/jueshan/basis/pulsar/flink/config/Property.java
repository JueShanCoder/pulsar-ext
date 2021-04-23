package com.jueshan.basis.pulsar.flink.config;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Slf4j
public class Property {
    private final static String CONF_NAME = "config.properties";

    private static Properties contextProperties;

    static {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(CONF_NAME);
        contextProperties = new Properties();
        assert in != null;
        InputStreamReader inputStream = new InputStreamReader(in, StandardCharsets.UTF_8);
        try {
            contextProperties.load(inputStream);
        } catch (IOException e) {
            log.error(" WARNING [请求配置资源加载失败... ]");
            e.printStackTrace();
        }
        log.info(" SUCCESS [请求配置资源加载成功...]");
    }

    public static String getStrValue(String key){
        return contextProperties.getProperty(key);
    }

    public static int getIntValue(String key){
        String strValue = getStrValue(key);
        return Integer.parseInt(strValue);
    }
}