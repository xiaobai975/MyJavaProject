package com.jdbc.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author JUNSHI 405773808@qq.com
 * @description: 定义一个用来被继承的对数据库进行基本操作的DAO
 * @create 2020-04-07 21:08
 */
public abstract class BaseDAO<T> {

    //实例化dbutils的运算器
    private QueryRunner queryRunner = new QueryRunner();
    //定义一个变量来接收泛型的类型
    private Class<T> type;

    //获取T的Class对象，获取泛型的类型，泛型是在被子类继承时才确定！
    public BaseDAO() {

        //获取子类的类型
        Class clazz = this.getClass();
        //获取父类的类型
        // getGenericSuperclass(),BaseDAO的子类继承的父类中的泛型
        //ParameterizedType表示的是带泛型的类型
        ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
        // 获取具体的泛型类型 getActualTypeArguments获取具体的泛型的类型
        // 这个方法会返回一个Type的数组
        Type[] types = parameterizedType.getActualTypeArguments();
        // 获取具体的泛型的类型·
        this.type = (Class<T>) types[0];

    }

    /**
     * 通用的增删改操作
     *
     * @param conn
     * @param sql
     * @param args
     * @return 返回增删改的条目数
     */
    public int update(Connection conn, String sql, Object... args) {
        int updateCount = 0;//记录修改的条数
        try {
            updateCount = queryRunner.update(conn, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updateCount;
    }

    /**
     * 获得一个对象
     *
     * @param conn
     * @param sql
     * @param args
     * @return 返回一个查询的对象
     */
    public T getBean(Connection conn, String sql, Object... args) {

        T query = null;
        try {
            query = queryRunner.query(conn, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return query;
    }

    /**
     * 获得所有查询对象
     *
     * @param conn
     * @param sql
     * @param args
     * @return 返回所有对象
     */
    public List<T> getBeanList(Connection conn, String sql, Object... args) {

        List<T> list = null;
        try {
            list = queryRunner.query(conn, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 获取一个但一值得方法，专门用来执行像 select count(*)...这样的sql语句
     *
     * @param conn
     * @param sql
     * @param args
     * @return
     */
    public <E> E getValue(Connection conn, String sql, Object... args) {

        Object value = null;
        try {
            value = queryRunner.query(conn, sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (E) value;

    }


}
