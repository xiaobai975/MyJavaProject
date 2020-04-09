package com.jdbc.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author JUNSHI 405773808@qq.com
 * @description: JDBC获取连接的工具类
 * @create 2020-04-07 20:35
 */
public class JDBCUtils {

    /**
     * 仅创建一个数据源
     */
    private static DataSource source;

    static {//静态代码块随着类加载只执行一次

        try {
            Properties properties = new Properties();
            //获得资源流传入配置文件
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            //加载配置文件
            properties.load(is);
            //得到druid数据源
            source = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获得数据连接对象
     *
     * @return connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {

        Connection connection = source.getConnection();

        return connection;
    }

    /**
     * 仅关闭数据库连接
     * @param conn
     */
    public static void closeResource(Connection conn) {

        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 关闭连接和statement
     *
     * @param conn
     * @param ps
     */
    public static void closeResource(Connection conn, Statement ps) {
        try {
            if (ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 关闭资源操作
     *
     * @param conn 连接
     * @param ps   Statement对象
     * @param rs   结果集
     */
    public static void closeResource(Connection conn, Statement ps, ResultSet rs) {
        try {
            if (ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
