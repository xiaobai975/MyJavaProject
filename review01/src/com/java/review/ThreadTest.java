package com.java.review;

import jdk.nashorn.internal.ir.IfNode;
import org.junit.Test;
import sun.plugin2.message.GetNameSpaceMessage;
import sun.plugin2.os.windows.Windows;

import java.awt.*;

/**
 * @author JUNSHI 405773808@qq.com
 * @description: 多线程复习
 * @create 2020-03-24 13:15
 */
public class ThreadTest {

    @Test
    public void test() {

        Window w1 = new Window();
        Window w2 = new Window();
        Window w3 = new Window();

        w1.setName("窗口一");
        w2.setName("窗口二");
        w3.setName("窗口三");

        w1.run();
        w2.run();
        w3.run();


    }

    @Test
    public void test1() {

        windows windows = new windows();
        Thread thread1 = new Thread(windows);
        Thread thread2 = new Thread(windows);
        Thread thread3 = new Thread(windows);

        thread1.setName("窗口1");
        thread2.setName("窗口2");
        thread3.setName("窗口3");

        thread1.run();
        thread2.run();
        thread3.run();

    }


}

class Window extends Thread {

    private static int ticket = 100;

    @Override
    public void run() {

        while (true) {
            synchronized (Window.class) {

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (ticket > 0) {
                    System.out.println(getName() + "卖票：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }

    }
}


class windows implements Runnable {

    private int ticket = 100;

    @Override
    public void run() {

        while (true) {
            synchronized (this) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + "卖票为：" + ticket);
                    ticket--;
                }


            }

        }

    }
}

class SingleTest{
    //懒汉式单例模式
    private static SingleTest Instance = null;

    public SingleTest getInstance(){

        if (Instance == null){

            synchronized (this){

                if (Instance == null){
                    return new SingleTest();
                }
            }

        }
        return Instance;


    }

}
class Single{

    private Single(){

    }

    private static Single instance = new Single();

    public Single getInstance(){
        return instance;
    }


}