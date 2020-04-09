package com.java.review1;

import org.junit.Test;
import sun.invoke.empty.Empty;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-03-26 8:51
 */
public class ReflectionTest {

    @Test
    public void test() throws Exception {
        //获取实例
        Class c1 = Person.class;

        Person p = new Person();
        Class c2 = p.getClass();

        Class c3 = Class.forName("com.java.review1.Person");

        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class c4 = classLoader.loadClass("com.java.review1.Person");

        Person p1 = (Person)c1.newInstance();
        Person p2 = (Person) c2.newInstance();
        c3.newInstance();
        c4.newInstance();

        Field uid = c1.getDeclaredField("UID");
        uid.setAccessible(true);
        uid.set(c1,123);
        System.out.println(uid.get(c1));

        Method show = c1.getDeclaredMethod("show",String.class);
        show.setAccessible(true);
            Object tom = show.invoke(p1, "cn");
        System.out.println(tom);

        Method showDesc = c1.getDeclaredMethod("showDesc");
        showDesc.setAccessible(true);
        Object cn = showDesc.invoke(null);
        System.out.println(cn);

        Class[] interfaces = c1.getInterfaces();
        for (Class i : interfaces){
            System.out.println(i);
        }

        Class[] interfaces1 = c1.getSuperclass().getInterfaces();
        for (Class c : interfaces1){
            System.out.println(c);
        }

    }

    @Test
    public void test1(){
        String s1 = "hello";
        String s2 = "world";

        String s3 = "hello" + "world";
        String s4 = "helloworld";

        String s5 = s1 + s2;
        String s6 = (s1 + s2).intern();
        String s7 = "hello" + s2;

        System.out.println(s3 == s4);//true
        System.out.println(s3 == s5);//false
        System.out.println(s3 == s6);//true
        System.out.println(s3 == s7);//false
        System.out.println(s5 == s6);//false
        System.out.println(s5 == s7);//false
        System.out.println(s6 == s7);//false
}

    @Test
    public void test2(){

        long l = System.currentTimeMillis();
        System.out.println(l);

        Date date = new Date();
        System.out.println(date.toString());

        Date date1 = new Date(1585192294192l);
        System.out.println(date1.toString());
        long time = date.getTime();
        System.out.println(time);
    }

    @Test
    public void test3() throws ParseException {

        Date date = new Date();

        SimpleDateFormat format = new SimpleDateFormat();
        String format1 = format.format(date);
        System.out.println(format1);

        String str = "20-3-26 下午12:44";
        Date parse = format.parse(str);
        System.out.println(parse);

        System.out.println("********************");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format2 = sdf.format(date);
        System.out.println(format2);

        String str1 = "2020-03-26 01:04:57";
        Date parse1 = sdf.parse(str1);
        System.out.println(parse1);

    }

    @Test
    public void test4(){

        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);

        LocalDateTime time = LocalDateTime.of(2001,03,03,12,12,21);
        System.out.println(time);
    }
    @Test
    public void test5(){

        LocalDateTime time = LocalDateTime.now();
        System.out.println(time);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String format = formatter.format(time);
        System.out.println(format);

        TemporalAccessor parse = formatter.parse("2001-03-03 04:41:25");
        System.out.println(parse.toString());


    }

    @Test
    public void test6(){

        Integer integer = new Integer(123);
        System.out.println(integer);

        Double aDouble = new Double(1212.12);
        System.out.println(aDouble);

        Character a = new Character('a');
        System.out.println(a);

        String s = String.valueOf(integer);
        System.out.println(s);

        String str = "133";
        int i = Integer.parseInt(str);

    }

}
