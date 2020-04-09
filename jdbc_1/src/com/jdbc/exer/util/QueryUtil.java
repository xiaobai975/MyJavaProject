package com.jdbc.exer.util;

import com.jdbc.exer.bean.Customer;
import com.jdbc3.bean.Order;
import org.junit.Test;
import org.omg.CORBA.ORB;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-04-06 0:22
 */
public class QueryUtil {
    @Test
    public void testGetForList(){

        String sql = "select id,name,email from customers where id < ?";
        List<Customer> list = getListQuery(Customer.class,sql,12);
        list.forEach(System.out::println);

        String sql1 = "select order_id orderId,order_name orderName from `order`";
        List<Order> orderList = getListQuery(Order.class, sql1);
        orderList.forEach(System.out::println);
    }

    public <T> List<T> getListQuery(Class<T> clazz, String sql, Object... args) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            //执行，获取结果集
            rs = ps.executeQuery();
            //获取结果集的元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取列数
            int columnCount = rsmd.getColumnCount();
            ArrayList<T> list = new ArrayList<>();
            while (rs.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    //获取每个列的列值:通过ResultSet
                    Object columnValue = rs.getObject(i + 1);
                    //通过ResultSetMetaData
                    //获取列的列名：getColumnName() --不推荐使用
                    //获取列的别名：getColumnLabel()
//					String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    //通过反射，将对象指定名columnName的属性赋值为指定的值columnValue
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            JDBCUtils.closeResource(conn, ps, rs);
        }


        return null;
    }

    @Test
    public void testGetOneQuery() {
        String sql = "select id,name,email from customers where id = ?";
        Customer customer = getOneQuery(Customer.class, sql, 12);
        System.out.println(customer);

        String sql1 = "select order_id orderId,order_name orderName from `order` where order_id = ?";
        Order order = getOneQuery(Order.class, sql1, 1);
        System.out.println(order);
    }

    public static  <T> T getOneQuery(Class<T> clazz, String sql, Object... args) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            //执行，获取结果集
            rs = ps.executeQuery();
            //获取结果集的元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取列数
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    //获取每个列的列值:通过ResultSet
                    Object columnValue = rs.getObject(i + 1);
                    //通过ResultSetMetaData
                    //获取列的列名：getColumnName() --不推荐使用
                    //获取列的别名：getColumnLabel()
//					String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    //通过反射，将对象指定名columnName的属性赋值为指定的值columnValue
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }

                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            JDBCUtils.closeResource(conn, ps, rs);
        }


        return null;

    }

    @Test
    public void testOrderQuery() {

        String sql = "select order_id orderId,order_name orderName from `order` where order_id = ?";
        Object orderQuery = getOrderQuery(sql, 3);
        System.out.println(orderQuery);

    }

    public Object getOrderQuery(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            //获取连接
            connection = JDBCUtils.getConnection();
            //获得实例
            ps = connection.prepareStatement(sql);

            //填充？？？
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            //获得结果集
            resultSet = ps.executeQuery();
            //获得元结果集
            ResultSetMetaData metaData = resultSet.getMetaData();
            //获得列数
            int columnCount = metaData.getColumnCount();
            if (resultSet.next()) {

                Order order = new Order();
                for (int i = 0; i < columnCount; i++) {

                    Object columnValue = resultSet.getObject(i + 1);

                    String columnLabel = metaData.getColumnLabel(i + 1);

                    //通过反射增添进去

                    Field declaredField = Order.class.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(order, columnValue);


                }

                return order;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, ps, resultSet);
        }

        //


        return null;
    }


}
