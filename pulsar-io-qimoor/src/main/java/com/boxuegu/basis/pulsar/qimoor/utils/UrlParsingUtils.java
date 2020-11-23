package com.boxuegu.basis.pulsar.qimoor.utils;

import com.google.common.collect.Lists;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
public class UrlParsingUtils {

    public static final List<String> NEWS = Lists.newArrayList("news");

    public static Utm parseUrl(String url) {
        Map<String, String> maps;
        try {
            url = URLDecoder.decode(url, "UTF-8");
            url = URLDecoder.decode(url, "UTF-8");
            maps = parse(url);
        } catch (Exception e) {
            log.error("url[{}]解析失败:", url);
            return Utm.builder()
                    .utmCampaign("")
                    .utmSource("")
                    .utmTerm("")
                    .utmMedium("")
                    .utmContent("")
                    .build();
        }
        return Utm.builder()
                .utmCampaign(maps.getOrDefault("utm_campaign", ""))
                .utmSource(maps.getOrDefault("utm_source", ""))
                .utmTerm(maps.getOrDefault("utm_term", ""))
                .utmMedium(maps.getOrDefault("utm_medium", ""))
                .utmContent(maps.getOrDefault("utm_content", ""))
                .build();
    }

    private static Map<String, String> parse(String url) {
        Map<String, String> maps = new HashMap<>();
        String[] urls = url.split("\\?");
        if (urls.length < 2) {
            return maps;
        }
        String[] kvs = urls[1].split("&");
        for (String kv : kvs) {
            String[] items = kv.split("=");
            if (items.length == 2) {
                maps.put(items[0], items[1]);
            }
        }
        return maps;
    }

    public static int courseId(String url) {
        try {
            String[] params = url.split("/");
            String courseId = Stream.of(params)
                    .filter(param ->
                            param.contains("-") && param.contains(".html"))
                    .findFirst()
                    .map(param -> param.substring(param.indexOf("-") + 1, param.indexOf(".html")))
                    .orElse(null);
            if (courseId == null) {
                return 0;
            }
            return Integer.parseInt(courseId);
        } catch (Exception ex) {
            return 0;
        }
    }

    public static int articleId(String url) {
        try {
            String[] params = url.split("/");
            String courseId = Stream.of(params)
                    .filter(param -> param.contains(".html"))
                    .findFirst()
                    .map(param -> param.substring(0, param.indexOf(".html")))
                    .orElse(null);
            if (courseId == null) {
                return 0;
            }
            return Integer.parseInt(courseId);
        } catch (Exception ex) {
            return 0;
        }
    }

    /**
     * 转换数据 JDBC结果集转实体
     *
     * @param resultSet 结果集
     * @param clazz	实体字节码文件对象
     * @param <T>	实体类型
     * @throws Exception  sql异常 NoClass异常等等
     */
    public static <T> List<T> exchangeData(ResultSet resultSet, Class<T> clazz) throws Exception {
        //获取全部类方法  包括父类的
        Method[] declaredMethods = clazz.getMethods();
        List<T> list = new ArrayList<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        while (resultSet.next()) {
            //反射实例化对象
            T obj = clazz.newInstance();
            //遍历类调用方法
            for (Method method : declaredMethods) {
                //获取方法名
                String name = method.getName();
                if (!name.startsWith("set")){
                    //只要setter
                    continue;
                }
                //获取数据库名 驼峰命名法转数据库字段命名法
                String dbName = getDbName(name);
                //遍历数据库所有列
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    //抓取指定列赋值
                    if (metaData.getColumnName(i).equals(dbName)) {
                        if (resultSet.getObject(i) != null) {
                            //赋值
                            setValue(obj,method,resultSet,i);
                        }
                        break;
                    }
                }
            }
            list.add(obj);
        }
        return list;
    }

    /**
     *  赋值操作，
     *  	主要是处理数据类型
     *  		此处只简单处理下基本数据类型和Date类型
     * @param obj	泛型对象
     * @param method	方法
     * @param resultSet	结果集
     * @param i	脚标
     * @param <T>	泛型
     */
    private static <T> void setValue(T obj, Method method, ResultSet resultSet, int i) throws SQLException, InvocationTargetException, IllegalAccessException {
        //Setter方法只有一个参数，获取参数类型名称
        String name = method.getParameterTypes()[0].getName().toLowerCase();
        if (name.contains("string")){
            method.invoke(obj, resultSet.getString(i));
        }
        else if (name.contains("short")){
            method.invoke(obj,resultSet.getShort(i));
        }
        else if (name.contains("int") || name.contains("integer")){
            method.invoke(obj,resultSet.getInt(i));
        }
        else if (name.contains("long")){
            method.invoke(obj,resultSet.getLong(i));
        }
        else if (name.contains("float")){
            method.invoke(obj,resultSet.getFloat(i));
        }
        else if (name.contains("double")){
            method.invoke(obj,resultSet.getDouble(i));
        }
        else if (name.contains("boolean")){
            method.invoke(obj,resultSet.getBoolean(i));
        }
        else if (name.contains("date")){
            method.invoke(obj,resultSet.getDate(i));
        }

        else {
            method.invoke(obj, resultSet.getObject(i));
        }

    }

    /**
     * 实体setter名称转对应数据库列的列名
     * 		需要遵守命名规范，java（驼峰命名法），数据库（全小写，单词间用'_'隔开）
     * @param name setter名称
     * @return	数据库列名
     */
    private static String getDbName(String name) {
        //根据setter命名规则获取对应的属性名
        name = name.substring(3,4).toLowerCase()+name.substring(4);
        //获取数据库对应列名
        StringBuilder buffer = new StringBuilder();
        char[] nameChars = name.toCharArray();
        for (char nameChar : nameChars) {
            if (nameChar >= 'A' && nameChar <= 'Z') {
                //将大写字母转换为下划线和对应的小写字母组合
                buffer.append("_").append(String.valueOf(nameChar).toLowerCase());
            } else {
                buffer.append(nameChar);
            }
        }
        return buffer.toString();
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    public static class Utm {

        private String utmCampaign;

        private String utmSource;

        private String utmTerm;

        private String utmMedium;

        private String utmContent;
    }
}