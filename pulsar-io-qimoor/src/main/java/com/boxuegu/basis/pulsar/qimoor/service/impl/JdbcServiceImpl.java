package com.boxuegu.basis.pulsar.qimoor.service.impl;

import com.boxuegu.basis.pulsar.qimoor.enums.JdbcDriverType;
import com.boxuegu.basis.pulsar.qimoor.service.JdbcService;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.boxuegu.basis.pulsar.qimoor.utils.PropertiesUtils.getProperties;

public class JdbcServiceImpl implements JdbcService {

    @Override
    public Connection getConnection(String jdbcUrl, String userName, String password) throws Exception {
        Class.forName(getDriverClassName(jdbcUrl));
        Properties jdbcProperties = new Properties();
        jdbcProperties.setProperty("user",userName);
        jdbcProperties.setProperty("password",password);
        return DriverManager.getConnection(jdbcUrl, jdbcProperties);
    }

    private String getDriverClassName(String jdbcUrl) throws Exception {
        for (JdbcDriverType type : JdbcDriverType.values()) {
            if (type.matches(jdbcUrl)) {
                return type.getDriverClass();
            }
        }
        throw new Exception("Provided JDBC connection string contains unknown driver: " + jdbcUrl);
    }

    public static <T> T convertResultToEntity(ResultSet resultSet, Class<T> tClass) throws Exception {
        T t = tClass.newInstance();
        ResultSetMetaData metaData = resultSet.getMetaData();
        for (int i = 0; i < metaData.getColumnCount(); i++) {
            String columnName = metaData.getColumnName(i + 1);
            String cloName = lineToHump(columnName);
            PropertyDescriptor descriptor = new PropertyDescriptor(cloName, tClass);
            Method method = descriptor.getWriteMethod();
            method.invoke(t, resultSet.getObject(columnName));
        }
        return t;
    }

    private static final Pattern linePattern = Pattern.compile("_(\\w)");

    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


    public static void closeSession(Connection connection,ResultSet row, PreparedStatement preparedStatement) throws Exception{
        if (connection != null) {
            connection.close();
        }
        if (row != null) {
            row.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
}