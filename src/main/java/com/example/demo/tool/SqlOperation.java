package com.example.demo.tool;
//存储所有对sql进行的操作函数


import java.sql.Connection;
import java.sql.PreparedStatement;

public class SqlOperation {
//定义数据库的连接
    private static final String url = "jdbc:mysql://66.98.124.133:3306/SEXManagementSystem?serverTimezone=UTC&useSSL=true&characterEncoding=utf-8";
    private static final String name = "com.mysql.jdbc.Driver";
    private static final String username = "root";
    private static final String password = "123456lys";
    public Connection connection = null;
    public PreparedStatement preparedStatement = null;

    //数据库的连接
    public static void SQLConnect() throws Exception{

    }
}
