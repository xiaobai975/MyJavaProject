package com.jdbc.blob;

import com.jdbc.exer.bean.Customer;
import com.jdbc.exer.util.JDBCUtils;
import org.junit.Test;

import java.io.*;
import java.sql.*;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-04-06 15:06
 */
public class BlobTest {

    @Test
    public void testInsert() throws Exception {

        Connection connection = JDBCUtils.getConnection();

        String sql = "insert into customers(name,email,birth,photo)values(?,?,?,?)";

        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setObject(1, "小红");
        ps.setObject(2, "xiaohong@qq.com");
        ps.setObject(3, "2055-5-16");

        FileInputStream file = new FileInputStream(new File("583016f738afdaee20ba50c27746d124.jpg"));

        ps.setBlob(4, file);

        ps.execute();

        JDBCUtils.closeResource(connection, ps);


    }


    @Test
    public void testBlobQuery()  {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        InputStream binaryStream = null;
        FileOutputStream fps = null;
        try {
            connection = JDBCUtils.getConnection();

            String sql = "select id,name,email,birth,photo from customers where id = ?";

            ps = connection.prepareStatement(sql);

            ps.setObject(1, 27);

            resultSet = ps.executeQuery();
            binaryStream = null;
            fps = null;
            if (resultSet.next()) {

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                Date birth = resultSet.getDate("birth");
                Blob photo = resultSet.getBlob("photo");

                Customer customer = new Customer(id, name, email, birth);

                System.out.println(customer);

                binaryStream = photo.getBinaryStream();
                fps = new FileOutputStream("xiaobai.jpg");
                byte[] bytes = new byte[1024];
                int len;
                while ((len = binaryStream.read(bytes)) != -1) {
                    fps.write(bytes, 0, len);
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (fps != null){

                    fps.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (binaryStream != null) {
                    binaryStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            JDBCUtils.closeResource(connection, ps, resultSet);
        }


    }

}
