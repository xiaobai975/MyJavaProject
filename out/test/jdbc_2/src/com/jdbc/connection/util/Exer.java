package com.jdbc.connection.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-04-07 14:08
 */
public class Exer {

    private static DataSource source;

    static {

        try {
            Properties pros = new Properties();

            FileInputStream fis = new FileInputStream(new File("src/dbcp.properties"));

            pros.load(fis);

            source = BasicDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static Connection getDBCPConnection() throws SQLException {

        Connection connection = source.getConnection();

        return connection;

    }



    public static DataSource source1;

    static {
        try {
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(new File("src/druid.properties"));

            properties.load(fis);

            source1 = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static Connection getConnection1() throws SQLException {

        Connection connection1 = source1.getConnection();
        return connection1;
    }

    @Test
    public void testC() throws SQLException {

        Connection connection = getConnection1();

        System.out.println(connection);
    }

}
