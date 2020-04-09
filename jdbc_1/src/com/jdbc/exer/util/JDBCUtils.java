package com.jdbc.exer.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-04-06 0:17
 */
public class JDBCUtils {
    /**
     * 获取数据的连接
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {

        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

        Properties properties = new Properties();

        properties.load(is);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");

        Class.forName(driverClass);

        Connection connection = DriverManager.getConnection(url, user, password);

        return connection;


    }
    /**
     * 关闭连接Statement的操作
     * @param connection
     * @param ps
     */
    public static void closeResource(Connection connection, Statement ps){

        try {
            if (ps != null){

                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null){

                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeResource(Connection connection, Statement ps, ResultSet resultSet){

        try {
            if (ps != null){

                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null){

                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }try {
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}


