package com.boxuegu.basis.pulsar.qimoor.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * @ClassName: PropertiesUtils
 * @Description: 获取配置文件信息
 * @date: 2017年11月25日 上午10:56:00
 * @version: 1.0.0
 */
public class PropertiesUtils {

    public static Properties getProperties() {
        Properties props = null;
        InputStream is = null;
        try {
            URL url = Thread.currentThread().getContextClassLoader().getResource("QiMoor.properties");
            if (url == null) {
                throw new IllegalArgumentException(" Argument url should not be null ");
            }
            is = url.openStream();
            if (is == null) {
                throw new FileNotFoundException("" + "file is not found");
            }
            props = new Properties();
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ignored) {
                }
            }
        }
        return props;
    }
}
