package com.jdbc.connection;


import com.jdbc.transaction.User;
import com.jdbc.util.JDBCUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.lang.reflect.Field;
import java.sql.*;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-04-07 2:03
 */
public class C3P0Test {

    @Test
    public void test() throws PropertyVetoException, SQLException {

        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass( "com.mysql.jdbc.Driver" ); //loads the jdbc driver
        cpds.setJdbcUrl( "jdbc:mysql://localhost:2001/test" );
        cpds.setUser("root");
        cpds.setPassword("20010303");

        cpds.setInitialPoolSize(10);

        Connection connection = cpds.getConnection();
        System.out.println(connection);

    }

    public static void main(String[] args) {

        ComboPooledDataSource cpds = new ComboPooledDataSource("c3p0");

        Connection connection = null;
        try {
            connection = cpds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(connection);
    }

    @Test
    public void testTransactionSelect() throws Exception{

        Connection connection = com.jdbc.connection.util.JDBCUtils.getConnection1();


        System.out.println(connection.getTransactionIsolation());


        String sql = "select user,password,balance from user_table where user = ?";


        PreparedStatement ps = connection.prepareStatement(sql);

        User cc = getInstance(connection, User.class, sql, "CC");

        System.out.println(cc);


    }




    //通用的查询操作，用于返回数据表中的一条记录（version 2.0：考虑上事务）

    public <T> T getInstance(Connection conn,Class<T> clazz,String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();
            // 获取结果集的元数据 :ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            // 通过ResultSetMetaData获取结果集中的列数
            int columnCount = rsmd.getColumnCount();

            if (rs.next()) {
                T t = clazz.newInstance();
                // 处理结果集一行数据中的每一个列
                for (int i = 0; i < columnCount; i++) {
                    // 获取列值
                    Object columValue = rs.getObject(i + 1);

                    // 获取每个列的列名
                    // String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    // 给t对象指定的columnName属性，赋值为columValue：通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);

        }

        return null;
    }





}
