package com.jdbc.connection;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-04-07 12:53
 */
public class DBCPTest {

    @Test
    public void testgetConnection() throws SQLException {

        BasicDataSource source = new BasicDataSource();

        source.setDriverClassName("com.mysql.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:2001/test");
        source.setUsername("root");
        source.setPassword("20010303");

        source.setInitialSize(10);
        source.setMaxActive(10);


        Connection connection = source.getConnection();

        System.out.println(connection);


    }

    @Test
    public void test() throws Exception {


        Properties properties = new Properties();

        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");

        properties.load(is);

        DataSource dataSource = BasicDataSourceFactory.createDataSource(properties);

        Connection connection = dataSource.getConnection();
        System.out.println(connection);


    }

}
