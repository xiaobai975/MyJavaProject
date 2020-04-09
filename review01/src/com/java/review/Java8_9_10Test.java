package com.java.review;

import org.junit.Test;

import java.util.function.Consumer;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-03-24 10:13
 */
public class Java8_9_10Test {


    @Test
    public void test() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("lambdaTest");
            }
        };
        r1.run();

        System.out.println();

        Runnable r2 = () -> System.out.println("lambdaTest");
        r2.run();

        System.out.println();

        Runnable r3 = System.out::println;
        r3.run();

    }

    @Test
    public void test1() {

        Consumer<String> c = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        c.accept("我们在这学习lambda");

        Consumer<String> c1 = s -> System.out.println(s);
        c1.accept("学习");

    }

    @Test
    public void test2(){



    }
}
