package com.jdbc.dao;

import com.jdbc.util.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-04-06 22:14
 */
public abstract class BaseDAO {

    /**
     * 通用的增删改方法v2.0 考虑事务
     *
     * @param conn
     * @param sql
     * @param args
     * @return
     */
    public int update(Connection conn, String sql, Object... args) {
        PreparedStatement ps = null;

        try {
            //预编译
            ps = conn.prepareStatement(sql);
            //填充占位符
            for (int i = 0; i < args.length; i++) {

                ps.setObject(i + 1, args[i]);
            }

            //执行
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //资源的关闭
            JDBCUtils.closeResource(null, ps);
        }


        return 0;
    }

    /**
     * 通用的查询操作，用于返回数据表中的一条记录（version 2.0：考虑上事务）
     *
     * @param connection
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T> T getOneQuery(Connection connection, Class<T> clazz, String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            //预编译
            ps = connection.prepareStatement(sql);

            //填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);

            }

            //获得结果集
            resultSet = ps.executeQuery();

            //获得元数据
            ResultSetMetaData metaData = resultSet.getMetaData();

            //获得列数
            int columnCount = metaData.getColumnCount();

            if (resultSet.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {//遍历列数

                    Object columnValue = resultSet.getObject(i + 1);//获得每列的结果集value

                    //获得列的别名
                    String columnLabel = metaData.getColumnLabel(i + 1);

                    //给t对象指定的columnName属性，赋值为columValue：通过反射
                    Field declaredField = clazz.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);//设置权限
                    declaredField.set(t, columnValue);//赋值

                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            //关流
            JDBCUtils.closeResource(null, ps, resultSet);
        }


        return null;
    }

    /**
     * 通用的查询操作，用于返回数据表中的多条记录构成的集合（version 2.0：考虑上事务）
     * @param conn
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     */

    public <T> List<T> getAllQuery(Connection conn, Class<T> clazz, String sql, Object... args) {

        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {

                ps.setObject(i + 1, args[i]);
            }

            resultSet = ps.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();

            int columnCount = metaData.getColumnCount();

            ArrayList<T> list = new ArrayList<>();
            while (resultSet.next()) {

                T t = clazz.newInstance();

                for (int i = 0; i < columnCount; i++) {

                    Object columnValue = resultSet.getObject(i + 1);

                    String columnLabel = metaData.getColumnLabel(i + 1);

                    Field declaredField = clazz.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(t, columnValue);


                }
                list.add(t);

            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            JDBCUtils.closeResource(null, ps, resultSet);
        }

        return null;
    }

    //用于查询特殊值的通用的方法
    public <E> E getValue(Connection conn, String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);

            }

            rs = ps.executeQuery();
            if (rs.next()) {
                return (E) rs.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);

        }
        return null;

    }

    public <T> T getValue(Connection conn, Class clazz, String sql, Object... args) {

        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {

                ps.setObject(i + 1, args[i]);
            }

            resultSet = ps.executeQuery();

            if (resultSet.next()) {

                return (T) resultSet.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null,ps,resultSet);
        }

        return null;
    }

}
