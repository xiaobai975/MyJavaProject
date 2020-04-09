package com.jdbc3.preparedstatement.crud;

import com.jdbc3.bean.Customer;
import com.jdbc3.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-04-05 15:49
 */
public class CustomerForQuery {

    @Test
    public void testQuery(){

        String sql = "select id,name,birth,email from customers where id = ?";
        Customer customer = customerForQuery(sql, 13);
        System.out.println(customer);

        sql = "select name,email from customers where name = ?";
        Customer customer1 = customerForQuery(sql,"周杰伦");
        System.out.println(customer1);

    }

    public Customer customerForQuery(String sql,Object ...args) {

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

                Customer customer = new Customer();
                for (int i = 0; i < columnCount; i++) {

                    Object columnValue = resultSet.getObject(i + 1);
                    //columnValue如
//                    resultSet.getInt(1);



                    //获取每个列的列名
                    String catalogName = resultSetMetaData.getColumnName(i + 1);

                    //给customer对象某个属性赋值columnValue:通过反射

                    Field field = Customer.class.getDeclaredField(catalogName);
                    field.setAccessible(true);
                    field.set(customer,columnValue);

                }
                return customer;

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,ps,resultSet);
        }


        return null;
    }

    @Test
    public void testSelect(){

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();

            String sql = "select id,name,email,birth from customers where id = ?";
            ps = connection.prepareStatement(sql);

            ps.setObject(1,1);
            resultSet = ps.executeQuery();

            if (resultSet.next()){

                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date birth = resultSet.getDate(4);

    //            Object[] arr = new Object[]{id,name,email,birth};

                Customer customer = new Customer(id, name, email, birth);
                System.out.println(customer);


            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,ps,resultSet);
        }

    }

}
