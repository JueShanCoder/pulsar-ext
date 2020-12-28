package com.boxuegu.basis.pulsar.qimoor.service;

import java.sql.Connection;

public interface GetObjectService {

    Object getObject(Connection connection, String sql) throws Exception;

}