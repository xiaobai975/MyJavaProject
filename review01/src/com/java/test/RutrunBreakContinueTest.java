package com.java.test;

import org.junit.Test;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-04-06 13:13
 */
public class RutrunBreakContinueTest {


    @Test
    public void testContinue() {

        for (int i = 0; i < 3; i++) {

            if (i == 1){
               break;
            }

            System.out.println("i的结果：" + i);
        }

    }

    @Test
    public void test() {


        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                System.out.println("j的值：" + j);
                return;
            }

            System.out.println("return后的输出语句");
        }
    }
}






