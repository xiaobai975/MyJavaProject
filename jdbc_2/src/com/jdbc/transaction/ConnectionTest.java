package com.jdbc.transaction;

import com.jdbc.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-04-06 19:33
 */
public class ConnectionTest {

    @Test
    public void test() throws Exception {

        Connection connection = JDBCUtils.getConnection();

        System.out.println(connection);

    }

}
