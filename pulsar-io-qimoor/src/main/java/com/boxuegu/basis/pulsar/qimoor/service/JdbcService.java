package com.boxuegu.basis.pulsar.qimoor.service;

import java.sql.Connection;

public interface JdbcService {

    Connection getConnection(String jdbcUrl, String userName, String password) throws Exception;

}
