package com.jdbc3.preparedstatement.crud;

import com.jdbc3.util.JDBCUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-04-05 13:13
 */
public class PrepardStatementUpdateTest {


    @Test
    public void testUpdate1(){

//        String sql = "delete from customers where id = ?";
//
//        update(sql,19);

//        String sql = "update `order` set order_id = ? where order_id = ?";
//
//        update(sql,4,3);

        String sql = "insert into `order`(order_id,order_name,order_date)values(?,?,?)";

        update(sql,"3","傻逼","200-3-3");
    }

    public void update(String sql,Object ...args) {

        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtils.getConnection();

            ps = connection.prepareStatement(sql);

            for (int i = 0;i < args.length;i++){
                ps.setObject(i + 1,args[i]);
            }
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,ps);
        }


    }



    @Test
    public void tsetUpdate()  {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            //1.获取数据库的连接
            connection = JDBCUtils.getConnection();

            //2.预编译sql语句，返回PreparedStatement的实例
            String sql = "update customers set name = ? where id = ?";

            ps = connection.prepareStatement(sql);

            //3.填充占位符

            ps.setObject(1,"莫扎特");
            ps.setObject(2,18);

            //4.执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            JDBCUtils.closeResource(connection,ps);
        }

        //5.资源的关闭


    }

    @Test
    public void testInsert(){

        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

        Properties properties = new Properties();

        Connection connection = null;
        PreparedStatement ps = null;
        try {
            properties.load(is);

            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String url = properties.getProperty("url");
            String driverClass = properties.getProperty("driverClass");

            Class.forName(driverClass);

            connection = DriverManager.getConnection(url, user, password);

            String sql = "insert into customers(name,email,birth) values(?,?,?)";

//            String  sql = "insert into customers(name,email,birth) values(?,?,?)";
            ps = connection.prepareStatement(sql);

            ps.setString(1,"傻逼");

            ps.setString(2,"232@qq.com");

            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date date = sd.parse("1000-2-2");

            ps.setDate(3,new Date(date.getTime()));//需要传入long型变量

            //执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
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

    }




}
