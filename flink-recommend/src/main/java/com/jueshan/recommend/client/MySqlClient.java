package com.jueshan.recommend.client;

import com.jueshan.recommend.utils.Property;

import java.sql.*;

public class MySqlClient {

    private static final String URL = Property.getStrValue("mysql.url");
    private static final String NAME = Property.getStrValue("mysql.name");
    private static final String PASS = Property.getStrValue("mysql.pass");
    private static Statement stmt;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, NAME, PASS);
            stmt = conn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据Id筛选产品
     */
    public static ResultSet selectById(int id) throws SQLException {
        String sql = String.format("select  * from product where product_id = %s",id);
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectUserById(int id) throws SQLException{
        String sql = String.format("select  * from user where user_id = %s",id);
        return stmt.executeQuery(sql);
    }

    public static void main(String[] args) throws SQLException {
        ResultSet resultSet = selectById(1);
        while (resultSet.next()) {
            System.out.println(resultSet.getString(2));
        }
    }
}