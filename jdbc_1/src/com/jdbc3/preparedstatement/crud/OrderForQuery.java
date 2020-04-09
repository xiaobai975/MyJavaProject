package com.jdbc3.preparedstatement.crud;

import com.jdbc3.bean.Order;
import com.jdbc3.util.JDBCUtils;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-04-05 20:39
 */
public class OrderForQuery {

    @Test
    public void testOrderQuery() {

        String sql = "select order_id orderId,order_date orderDate,order_name orderName from `order` where order_id = ? ";

        Order order = orderQuery(sql, "3");
        System.out.println(order);

    }

    public Order orderQuery(String sql,Object ...args){

        try {
            Connection connection = JDBCUtils.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1,args[i]);
            }

            ResultSet resultSet = ps.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();

            int columnCount = metaData.getColumnCount();

            if (resultSet.next()){
                Order order = new Order();
                //循环列数将每列的属性赋值
                for (int i = 0; i < columnCount; i++) {

                    //查询出来的属性对应每列属性的值
                    //具体列的列值
                    Object columnValue = resultSet.getObject(i + 1);

                    //得到每列的列名
//                    String columnName = metaData.getColumnName(i + 1);
                    //得到每列的别名
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    //通过反射附上值

                    Field declaredField = Order.class.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    //给order对象赋值当前列名的值
                    declaredField.set(order,columnValue);

                }
                return order;


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            return null;


    }

    @Test
    public void testQuery(){

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();

            String sql = "select order_id,order_name,order_date from `order` where order_id = ?";
            ps = connection.prepareStatement(sql);

            ps.setObject(1,"3");

            resultSet = ps.executeQuery();

            if(resultSet.next()){

                int orderId = resultSet.getInt(1);
                String orderName = resultSet.getString(2);
                Date orderDate = resultSet.getDate(3);

                Order order = new Order(orderId, orderName, orderDate);
                System.out.println(order);


            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,ps,resultSet);
        }


    }



}
