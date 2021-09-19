package com.heyongqiang.note.util;

import cn.hutool.db.DbUtil;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class dbUtil {
    //得到配置对象
    private static Properties properties = new Properties();
    static {
        //加载配置文件对象
        try {
            InputStream in = DbUtil.class.getClassLoader().getResourceAsStream("db.properties");
            properties.load(in);
            //加载驱动
            Class.forName(properties.getProperty("jdbcName"));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库链接
     * @return
     */
    public static Connection getConnection(){
        Connection connection = null;
        try {
            String dbUrl = properties.getProperty("dbUrl");
            String dbName = properties.getProperty("dbName");
            String dbPwd = properties.getProperty("dbPwd");
            connection = DriverManager.getConnection(dbUrl,dbName,dbPwd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭数据库链接
     * @param resultSet
     * @param preparedStatement
     * @param connection
     */
    public static void close(ResultSet resultSet, PreparedStatement preparedStatement , Connection connection){
        //判断资源为空就关闭
        try{
            if(resultSet != null){
                resultSet.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
            }
            if(connection != null){
                connection.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
