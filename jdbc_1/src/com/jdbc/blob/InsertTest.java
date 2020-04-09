package com.jdbc.blob;

import com.jdbc.exer.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-04-06 16:21
 */
public class InsertTest {

    @Test
    public void tsesInsrt()  {

        Connection connection = null;
        PreparedStatement ps = null;
        long start = 0;
        long end = 0;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "insert into goods(name)values(?)";
            ps = connection.prepareStatement(sql);

            start = System.currentTimeMillis();

            for (int i = 0; i <= 2000000; i++) {

                ps.setObject(1,"name_" + i);

                ps.execute();

            }

            end = System.currentTimeMillis();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("所花费的时间为: " + (end - start));
            JDBCUtils.closeResource(connection,ps);
        }



    }


    @Test
    public void testInsert1() {

        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtils.getConnection();

            String sql = "insert into goods(name)values(?)";

            ps = connection.prepareStatement(sql);

            connection.setAutoCommit(false);

            long start = System.currentTimeMillis();
            for (int i = 1; i <= 1000000; i++) {

                ps.setObject(1,"name_" + i);

                ps.addBatch();

                if (i % 500 == 0){
                    //执行batch
                    ps.executeBatch();
                    //清空batch
                    ps.clearBatch();
                }

            }
            connection.commit();
            long end = System.currentTimeMillis();

            System.out.println("所花费的时间为：" + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,ps);
        }
    }
    


}
