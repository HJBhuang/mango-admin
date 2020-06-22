package com.louis.mango.admin.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author huangjiabao
 * @data 2020/6/19/0019
 * @time 10:25:33
 */
public  class ConnectionMysql {
    public static Connection getConn() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接对象
        String url = "jdbc:mysql://115.29.110.8:8066/TESTDB?characterEncoding=UTF-8&&autoReconnect=true&failOverReadOnly=false&useSSL=false";
        Connection conn = null;
        conn = DriverManager.getConnection(url, "root", "123456");
        return conn;
    }
}
