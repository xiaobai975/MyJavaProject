package com.jdbc.dao;

import com.jdbc.bean.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-04-06 23:34
 */
public class CustomerDAOImpl extends BaseDAO implements CustomerDAO {


    @Override
    public void insert(Connection conn, Customer cust) {

        String sql = "insert into customers(name,email,birth)values(?,?,?)";

        update(conn, sql, cust.getName(), cust.getEmail(), cust.getBirth());

    }

    @Override
    public void deleteById(Connection conn, int id) {


        String sql = "delete from customers where id = ?";
        update(conn, sql, id);

    }

    @Override
    public void update(Connection conn, Customer cust) {

        String sql = "update customers set name = ?,email = ?,birth = ? where id = ?";
        update(conn, sql, cust.getName(), cust.getEmail(), cust.getBirth(), cust.getId());

    }

    @Override
    public Customer getCustomerById(Connection conn, int id) {

        String sql = "select name,email,birth from customers where id = ?";

        Customer oneQuery = getOneQuery(conn, Customer.class, sql, id);


        return oneQuery;
    }

    @Override
    public List<Customer> getAll(Connection conn) {

        String sql = "select id,name,email,birth from customers";
        List<Customer> allQuery = getAllQuery(conn, Customer.class, sql);
        return allQuery;
    }

    @Override
    public Long getCount(Connection conn) {

        String sql = "select count(*) from customers";

        return getValue(conn,sql);

    }

    @Override
    public Date getMaxBirth(Connection conn) {

        String sql = "select max(birth) from customers";


        return getValue(conn, sql);

    }
}
