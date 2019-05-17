package com.example.demo.tool;
//存储所有对sql进行的操作函数


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class SQLOperation {

    public static JSONArray select(String sql) throws Exception {
        return exeQuery(sql);
    }

    public static int update(String sql) throws Exception {
        return exeUpdate(sql);
    }

    public static int insert(String sql) throws Exception {
        return exeUpdate(sql);
    }

    public static int drop(String sql) throws Exception {
        return exeUpdate(sql);
    }


    private static JSONArray exeQuery(String sql) throws Exception {
        DBManager dbManager = new DBManager(sql);
        ResultSet result;
        result = dbManager.preparedStatement.executeQuery();
        JSONArray array = resultToJSON(result);
        close(result, dbManager);
        return array;
    }

    private static int exeUpdate(String sql) throws Exception {
        DBManager dbManager = new DBManager(sql);
        int temp = dbManager.preparedStatement.executeUpdate();
        close(dbManager);
        return temp;
    }

    private static void close(ResultSet resultSet, DBManager dbManager) throws Exception {
        resultSet.close();
        dbManager.close();
    }

    private static void close(DBManager dbManager) throws Exception {
        dbManager.close();
    }

    public static JSONArray resultToJSON(ResultSet resultSet) throws SQLException, JSONException {
        JSONArray array = new JSONArray();

        // 获取列数
        java.sql.ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        // 遍历ResultSet中的每条数据
        while (resultSet.next()) {
            JSONObject jsonObj = new JSONObject();

            // 遍历每一列
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnLabel(i);
                String value = resultSet.getString(columnName);
                jsonObj.put(columnName, value);
            }
            array.add(jsonObj);
        }

        return array;
    }
}
