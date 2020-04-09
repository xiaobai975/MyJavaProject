package com.jdbc.test;

import com.jdbc.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-04-07 21:07
 */
public class ConnectionTest {

    @Test
    public void testConnection() throws SQLException {

        Connection connection = JDBCUtils.getConnection();

        System.out.println(connection);

        JDBCUtils.closeResource(connection);


    }

    @Test
    public void testInsert()  {

        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();

            QueryRunner queryRunner = new QueryRunner();

            String sql = "INSERT INTO team(name)VALUES(?)";
            for (int i = 0;i < 10;i++){
                queryRunner.update(connection, sql, i);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection);
        }


    }
}
//              name    age salary position status   bonus   stock  device
//         3	李彦宏	23	7000.0	程序员	FREE			        戴尔(NEC17寸)
//         4	刘强东	24	7300.0	程序员	FREE			        戴尔(三星 17寸)
//         5	雷军	28	10000.0	设计师	FREE	5000.0		    激光(佳能 2900)
//         6	任志强	22	6800.0	程序员	FREE			        华硕(三星 17寸)
//         7	柳传志	29	10800.0	设计师	FREE	5200.0		    华硕(三星 17寸)
//         8	杨元庆	30	19800.0	架构师	FREE	15000.0  2500	针式(爱普生20K)
//         9	史玉柱	26	9800.0	设计师	FREE	5500.0		    惠普m6(5800.0)
//         10	丁磊	21	6600.0	程序员	FREE			        戴尔(NEC 17寸)
//         11	张朝阳	25	7100.0	程序员	FREE			        华硕(三星 17寸)
//         12	杨致远	27	9600.0	设计师	FREE	4800.0		    惠普m6(5800.0)