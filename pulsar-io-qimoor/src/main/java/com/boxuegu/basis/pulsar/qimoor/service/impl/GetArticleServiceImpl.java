package com.boxuegu.basis.pulsar.qimoor.service.impl;

import com.boxuegu.basis.pulsar.qimoor.entity.remote.RemoteArticle;
import com.boxuegu.basis.pulsar.qimoor.service.GetObjectService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.boxuegu.basis.pulsar.qimoor.service.impl.JdbcServiceImpl.closeSession;
import static com.boxuegu.basis.pulsar.qimoor.service.impl.JdbcServiceImpl.convertResultToEntity;

@Slf4j
public class GetArticleServiceImpl implements GetObjectService {
    @Override
    public Object getObject(Connection connection, String sql) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet row = null;
        RemoteArticle remoteArticle = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            row = preparedStatement.executeQuery();
            while (row.next()) {
                // map to entity
                remoteArticle = convertResultToEntity(row, RemoteArticle.class);
            }
        } finally {
            closeSession(connection, row, preparedStatement);
        }
        log.info(" remoteCourse entity is {}", new Gson().toJson(remoteArticle));
        return remoteArticle;
    }

    public static String getArticleSQL(Integer id) {
        return "SELECT\n" +
                "\t`menu_id`\n" +
                "FROM\n" +
                "\t`d_bxg`.`oe_article` \n" +
                "WHERE\n" +
                "\t`id` = '" + id + "'";
    }
}