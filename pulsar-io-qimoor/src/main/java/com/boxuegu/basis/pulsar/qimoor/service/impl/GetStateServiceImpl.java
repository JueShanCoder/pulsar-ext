package com.boxuegu.basis.pulsar.qimoor.service.impl;

import com.boxuegu.basis.pulsar.qimoor.entity.WebChatState;
import com.boxuegu.basis.pulsar.qimoor.service.GetObjectService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.boxuegu.basis.pulsar.qimoor.service.impl.JdbcServiceImpl.closeSession;
import static com.boxuegu.basis.pulsar.qimoor.service.impl.JdbcServiceImpl.convertResultToEntity;

@Slf4j
public class GetStateServiceImpl implements GetObjectService {

    @Override
    public Object getObject(Connection connection, String sql) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet row = null;
        WebChatState webChatState = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            row = preparedStatement.executeQuery();
            while (row.next()) {
                // map to entity
                webChatState = convertResultToEntity(row, WebChatState.class);
            }
        } finally {
            closeSession(null, row, preparedStatement);
        }
        log.info(" webChatState entity is {}", new Gson().toJson(webChatState));
        return webChatState;
    }


    public static int updateState(Connection connection, Long id, String stateValues) throws Exception {
        PreparedStatement preparedStatement = null;
        try {
            String sql = "UPDATE `web_chat_state` SET `state_value` = ? WHERE `id` = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, stateValues);
            preparedStatement.setLong(2, id);
            return preparedStatement.executeUpdate();
        } finally {
            closeSession(null,null,preparedStatement);
        }
    }


    public static String GetStateSQL(Long id){
        return "SELECT\n" +
                "\t`state_key`,\n" +
                "\t`state_value` \n" +
                "FROM\n" +
                "\t`d_bxg_dvb`.`web_chat_state` \n" +
                "WHERE\n" +
                "\t`id` = '" + id + "'";
    }
}