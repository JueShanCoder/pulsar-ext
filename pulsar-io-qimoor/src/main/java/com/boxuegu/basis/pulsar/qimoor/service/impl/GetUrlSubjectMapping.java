package com.boxuegu.basis.pulsar.qimoor.service.impl;

import com.boxuegu.basis.pulsar.qimoor.entity.remote.RemoteUrlSubjectMapping;
import com.boxuegu.basis.pulsar.qimoor.service.GetObjectService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.boxuegu.basis.pulsar.qimoor.service.impl.JdbcServiceImpl.closeSession;
import static com.boxuegu.basis.pulsar.qimoor.service.impl.JdbcServiceImpl.convertResultToEntity;

@Slf4j
public class GetUrlSubjectMapping implements GetObjectService {
    @Override
    public Object getObject(Connection connection, String sql) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet row = null;
        RemoteUrlSubjectMapping getUrlSubjectMapping = null;
        try{
            preparedStatement = connection.prepareStatement(sql);
            row = preparedStatement.executeQuery();
            while (row.next()) {
                // map to entity
                getUrlSubjectMapping = convertResultToEntity(row, RemoteUrlSubjectMapping.class);
            }
        }finally {
            closeSession(connection,row,preparedStatement);
        }
        log.info(" remoteCourse entity is {}",new Gson().toJson(getUrlSubjectMapping));
        return getUrlSubjectMapping;
    }

    public static String getUrlSubjectMapper(String url){
        return "SELECT\n" +
                "\t`subject_id`,\n" +
                "\t`subject_name`\n" +
                "FROM\n" +
                "\t`d_bxg_crm`.`t_url_subject_mapping`\n" +
                "WHERE\n" +
                "\t`url` = '"+url+"'";
    }

}