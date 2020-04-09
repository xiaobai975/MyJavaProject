package com.jdbc3.preparedstatement.crud;

import com.jdbc3.bean.Customer;
import com.jdbc3.bean.Order;
import com.jdbc3.util.JDBCUtils;
import org.junit.Test;

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
 * @create 2020-04-05 23:00
 */
public class PreparedStatementQueryTest {

    @Test
    public void testListQuery(){

        String sql = "select id,name,birth from customers where id < ? ";
        List<Customer> listForQuery = getListForQuery(Customer.class,sql,10);

//        listForQuery.forEach(s-> System.out.println(s));
        listForQuery.forEach(System.out::println);

        String sql1 = "select order_id orderId,order_name orderName,order_date orderDate from `order` where order_id < ?";
        List<Order> orderList = getListForQuery(Order.class, sql1, 4);
        orderList.forEach(System.out::println);

    }


    public <T> List<T> getListForQuery(Class<T> clazz, String sql, Object ...args){

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            //1.获取连接
            conn = JDBCUtils.getConnection();
            //2.预编译sql语句，返回PreparedStatement的实例
            ps = conn.prepareStatement(sql);
            //填充占位符
            for (int i = 0;i <args.length;i++){
                ps.setObject(i + 1,args[i]);
            }
            //3.返回结果集
            resultSet = ps.executeQuery();
            //4.获取结果集元数据
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            //5.获取列数
            int columnCount = resultSetMetaData.getColumnCount();
            ArrayList<T> list = new ArrayList<>();
            while (resultSet.next()){//对接到某一行了

                T t = clazz.newInstance();

                for (int i = 0; i < columnCount; i++) {

                    Object columnValue = resultSet.getObject(i + 1);
                    //columnValue如
//                    resultSet.getInt(1);


                    //获取每个列的列名
                    String columnLabel = resultSetMetaData.getColumnLabel(i + 1);

                    //给customer对象某个属性赋值columnValue:通过反射

                    Field field = t.getClass().getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,columnValue);

                }

                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,ps,resultSet);
        }

        return null;
    }




    @Test
    public void testGetInstance(){

        String sql = "select id,birth,name,email from customers where id = ?";

        Customer instance = getInstance(Customer.class, sql, 6);
        System.out.println(instance);

        String sql1 = "select order_id orderId,order_name orderName from `order` where order_id = ?";

        Order instance1 = getInstance(Order.class, sql1, 3);
        System.out.println(instance1);

    }

    public <T> T getInstance(Class<T> clazz,String sql,Object ...args){


        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            //1.获取连接
            conn = JDBCUtils.getConnection();
            //2.预编译sql语句，返回PreparedStatement的实例
            ps = conn.prepareStatement(sql);
            //填充占位符
            for (int i = 0;i <args.length;i++){
                ps.setObject(i + 1,args[i]);
            }
            //3.返回结果集
            resultSet = ps.executeQuery();
            //4.获取结果集元数据
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            //5.获取列数
            int columnCount = resultSetMetaData.getColumnCount();

            if (resultSet.next()){//对接到某一行了

                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {

                    Object columnValue = resultSet.getObject(i + 1);
                    //columnValue如
//                    resultSet.getInt(1);


                    //获取每个列的列名
                    String columnLabel = resultSetMetaData.getColumnLabel(i + 1);

                    //给customer对象某个属性赋值columnValue:通过反射

                    Field field = t.getClass().getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,columnValue);

                }
                return t;

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,ps,resultSet);
        }

        return null;
    }

}
