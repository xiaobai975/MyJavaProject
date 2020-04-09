package com.java.review2.review2;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-03-26 17:17
 */
public class PropertiesTest {

    @Test
    public void test() throws IOException {

        Properties p = new Properties();

        ClassLoader classLoader = PropertiesTest.class.getClassLoader();
//        InputStream is = classLoader.getResourceAsStream("jdbc.properties");
        InputStream is = classLoader.getResourceAsStream("jdbc.properties");

        p.load(is);

        String name = p.getProperty("user");

        String passward = p.getProperty("passward");

        System.out.println("user = " + name + ",passward = " + passward );


    }



}
