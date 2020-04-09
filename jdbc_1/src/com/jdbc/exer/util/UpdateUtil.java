package com.jdbc.exer.util;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-04-06 0:22
 */
public class UpdateUtil {

    @Test
    public void testUpdate(){

        String sql = "insert into customers(name,email,birth) values(?,?,?)" ;

//        UpdateUtil.updateSql(sql,"小白","222@q","2001-03-03");

        sql = "delete from customers where name = ?";
//        UpdateUtil.updateSql(sql,"傻逼");

        sql = "update customers set name = ? where name = ?";
//        UpdateUtil.updateSql(sql,"煞笔","小白");

    }

    public int updateSql(String sql,Object ...args) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //得到连接
            connection = JDBCUtils.getConnection();
            //2.预编译sql语句，返回PreparedStatement的实例
            ps = connection.prepareStatement(sql);
            //3.填充占位符
            for (int i = 0; i < args.length; i++) {

                ps.setObject(i + 1,args[i]);
            }
            //执行

//            ps.execute();

            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtils.closeResource(connection,ps);
        }
        return 0;
    }

}
