package com.jdbc.transaction;

import com.jdbc.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-04-06 19:35
 */
public class TransactionTest {

    /*
     * update user_table set balance = balance - 100 where user = 'AA';
     * update user_table set balance = balance + 100 where user = 'BB';
     */
    @Test
    public void tset2() {

        String sql = "update user_table set balance = balance - 100 where user = ?";
        update(sql, "AA");

        String sql1 = "update user_table set balance = balance + 100 where user = ?";
        update(sql1, "BB");

        System.out.println("修改成功！");

    }

    //通用的增删改操作
    public int update(String sql, Object... args) {//sql中占位符的个数与可变形参的长度相同！
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1.获取数据库的连接
            conn = JDBCUtils.getConnection();
            //2.预编译sql语句，返回PreparedStatement的实例
            ps = conn.prepareStatement(sql);
            //3.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);//小心参数声明错误！！
            }
            //4.执行
//            ps.execute();
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5.资源的关闭
            JDBCUtils.closeResource(conn, ps);
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return 0;

    }

    //================================升级操作=================================
    @Test
    public void tset1() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();

            System.out.println(connection.getAutoCommit());

            connection.setAutoCommit(false);

            String sql = "update user_table set balance = balance - 100 where user = ?";
            update(connection, sql, "AA");

            System.out.println(10 / 0);

            String sql1 = "update user_table set balance = balance + 100 where user = ?";
            update(connection, sql1, "BB");

            System.out.println("修改成功！");

            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();

            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } finally {
            JDBCUtils.closeResource(connection, null);
        }


    }

    //通用的增删改操作
    public int update(Connection conn, String sql, Object... args) {//sql中占位符的个数与可变形参的长度相同！
        PreparedStatement ps = null;
        try {

            //2.预编译sql语句，返回PreparedStatement的实例
            ps = conn.prepareStatement(sql);
            //3.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);//小心参数声明错误！！
            }
            //4.执行
//            ps.execute();
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5.资源的关闭
            JDBCUtils.closeResource(null, ps);

        }

        return 0;

    }


    //=================================================================

    @Test
    public void testTransactionSelect() throws Exception{

        Connection connection = JDBCUtils.getConnection();

        System.out.println(connection.getTransactionIsolation());


        String sql = "select user,password,balance from user_table where user = ?";


        PreparedStatement ps = connection.prepareStatement(sql);

        User cc = getInstance(connection, User.class, sql, "CC");

        System.out.println(cc);


    }


    @Test
    public void testTransactionUpdate() throws Exception{

        Connection connection = JDBCUtils.getConnection();

        String sql = "update into set balance = ? where user = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        update(connection,sql,5000,"CC");


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