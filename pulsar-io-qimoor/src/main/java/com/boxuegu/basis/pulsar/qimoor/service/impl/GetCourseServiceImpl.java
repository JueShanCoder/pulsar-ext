package com.boxuegu.basis.pulsar.qimoor.service.impl;

import com.boxuegu.basis.pulsar.qimoor.entity.remote.RemoteCourse;
import com.boxuegu.basis.pulsar.qimoor.service.GetObjectService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.boxuegu.basis.pulsar.qimoor.service.impl.JdbcServiceImpl.closeSession;
import static com.boxuegu.basis.pulsar.qimoor.service.impl.JdbcServiceImpl.convertResultToEntity;

@Slf4j
public class GetCourseServiceImpl implements GetObjectService {

    @Override
    public Object getObject(Connection connection, String sql) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet row = null;
        RemoteCourse remoteCourse = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            row = preparedStatement.executeQuery();
            while (row.next()) {
                // map to entity
                remoteCourse = convertResultToEntity(row, RemoteCourse.class);
            }
        } finally {
            closeSession(connection, row, preparedStatement);
        }
        log.info(" remoteCourse entity is {}", new Gson().toJson(remoteCourse));
        return remoteCourse;
    }

    public static String getRemoteCourseSQL(Integer id) {
        return "SELECT\n" +
                "\t`grade_name`,\n" +
                "\t`menu_id`,\n" +
                "\t`current_price`,\n" +
                "\t`course_type`,\n" +
                "\t`status` \n" +
                "FROM\n" +
                "\t`d_bxg`.`oe_course` \n" +
                "WHERE\n" +
                "\t`id` = '" + id + "'";
    }
}