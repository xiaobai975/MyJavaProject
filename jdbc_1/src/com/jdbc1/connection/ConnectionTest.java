package com.jdbc1.connection;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-04-04 20:46
 */
public class ConnectionTest {


    // 方式一：
    @Test
    public void testConnection() throws SQLException {
        // 获取Driver实现类对象
        Driver driver = new com.mysql.jdbc.Driver();

        // url:http://localhost:8080/gmall/keyboard.jpg
        // jdbc:mysql:协议
        // localhost:ip地址
        // 3306：默认mysql的端口号
        // test:test数据库
        String url = "jdbc:mysql://localhost:2001/myemployees";
        // 将用户名和密码封装在Properties中
        Properties info = new Properties();

        info.setProperty("user","root");

        info.setProperty("password","20010303");


        Connection conn = driver.connect(url,info);

        System.out.println(conn);
    }

    //方式二：对方式一的迭代:在如下的程序中不出现第三方的api,使得程序具有更好的可移植性
    @Test
    public void test1() throws Exception {
        // 获取Driver实现类对象
        Class clazz = Class.forName("com.mysql.jdbc.Driver");

        Driver driver = (Driver) clazz.newInstance();

        //2.提供要连接的数据库
        String url = "jdbc:mysql://localhost:2001/myemployees";

        //提供需要连接的用户名和密码
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","20010303");

        //4.获取连接
        Connection connect = driver.connect(url, info);
        System.out.println(connect);


    }

    //方式三：使用DriverManager替代Driver
    @Test
    public void test2() throws Exception {

        // 获取Driver实现类对象
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();


        // 2.提供另外三个连接的基本信息：
        String url = "jdbc:mysql://localhost:2001/myemployees";
        String user = "root";
        String password = "20010303";

        //注册驱动
        DriverManager.registerDriver(driver);

        //获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);


    }

    //方式四：
    @Test
    public void test3() throws Exception {

        // 1.提供三个连接的基本信息：
        String url = "jdbc:mysql://localhost:2001/myemployees";
        String user = "root";
        String password = "20010303";

        // 2.获取Driver实现类对象
        Class.forName("com.mysql.jdbc.Driver");
//        Driver driver = (Driver) clazz.newInstance();
//        //注册驱动
//        DriverManager.registerDriver(driver);

        //3.获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);


    }

    //方式五：将数据库连接需要的四个基本信息放在配置文件中
    @Test
    public void test4() throws Exception {

        //1.获取当前类的加载器，获得文件流，输入配置文件名
        InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");

        //2.创建propertiess实例
        Properties properties = new Properties();

        //3.加载文件
        properties.load(is);

        //获得配置文件信息
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");

        //2.加载驱动
        Class.forName(driverClass);

        //获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);


    }

}
