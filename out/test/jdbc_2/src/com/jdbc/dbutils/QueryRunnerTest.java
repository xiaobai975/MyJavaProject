package com.jdbc.dbutils;

import com.jdbc.bean.Customer;
import com.jdbc.connection.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-04-07 14:48
 */
public class QueryRunnerTest {

    @Test
    public void test() throws SQLException {

        QueryRunner qr = new QueryRunner();

        Connection connection3 = JDBCUtils.getConnection3();

        String sql = "insert into customers(name,email,birth)values(?,?,?)";

        int insertCount = qr.update(connection3, sql, "傻逼", "@ww", "3014-10-1");

        System.out.println("一共有：" + insertCount +"条影响");

    }

    @Test
    public void testDelete(){

        Connection connection3 = null;
        try {
            QueryRunner qr = new QueryRunner();

            connection3 = JDBCUtils.getConnection3();

            String sql = "delete from customers where id = ?";

            int update = qr.update(connection3, sql, "29");

            System.out.println(update);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection3,null);
        }


    }

    @Test
    public void testQuery() throws SQLException {

        Connection connection3 = JDBCUtils.getConnection3();

        QueryRunner qr = new QueryRunner();

        String sql = "select id,name,email,birth from customers where id = ?";

        BeanHandler<Customer> rsh = new BeanHandler<>(Customer.class);

        Customer query = qr.query(connection3, sql, rsh, 26);

        System.out.println(query);


    }
    @Test
    public void testQuery1()  {

        Connection connection3 = null;
        try {
            connection3 = JDBCUtils.getConnection3();

            QueryRunner qr = new QueryRunner();

            String sql = "select id,name,email,birth from customers where id > ?";


            BeanListHandler<Customer> rsh = new BeanListHandler<>(Customer.class);

            List<Customer> query = qr.query(connection3, sql, rsh, 26);

            query.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection3,null);
        }

    }

    @Test
    public void testQuery3() throws SQLException {

        Connection connection3 = JDBCUtils.getConnection3();

        QueryRunner qr = new QueryRunner();

        String sql = "select id,name,email,birth from customers where id = ?";

        MapHandler map = new MapHandler();

        Map<String, Object> query = qr.query(connection3, sql, map, 26);

        System.out.println(query);

        JDBCUtils.closeResource(connection3,null);


    }
    @Test
    public void testQuery4() throws SQLException {

        Connection connection3 = JDBCUtils.getConnection3();

        QueryRunner qr = new QueryRunner();

        String sql = "select id,name,email,birth from customers where id > ?";

        MapListHandler handler = new MapListHandler();

        List<Map<String, Object>> query = qr.query(connection3, sql, handler, 26);

        query.forEach(System.out::println);

        JDBCUtils.closeResource(connection3,null);


    }

    @Test
    public void testCount() throws SQLException {

        Connection connection3 = JDBCUtils.getConnection3();

        QueryRunner queryRunner = new QueryRunner();

        String sql = "select count(*) from customers";

        ScalarHandler scalarHandler = new ScalarHandler();

        Long count = (Long) queryRunner.query(connection3, sql, scalarHandler);

        System.out.println(count);

        JDBCUtils.closeResource(connection3,null);


    }
    @Test
    public void testBirth1() throws SQLException {

        Connection connection3 = JDBCUtils.getConnection3();

        QueryRunner queryRunner = new QueryRunner();

        String sql = "select max(birth) from customers";

        ScalarHandler scalarHandler = new ScalarHandler();

        Date date = (Date) queryRunner.query(connection3, sql, scalarHandler);

        System.out.println(date);

        JDBCUtils.closeResource(connection3,null);


    }

}
