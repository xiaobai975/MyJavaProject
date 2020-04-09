package com.java.review;

import sun.awt.windows.ThemeReader;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-03-24 15:37
 */
public class LockTest {

    public static void main(String[] args) {

        window1 w = new window1();
        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口1：");
        t2.setName("窗口2：");
        t3.setName("窗口3：");

        t1.start();
        t2.start();
        t3.start();

    }

}
class window1 implements Runnable{

    private int ticket = 100;
    private ReentrantLock lock = new ReentrantLock();//获取锁的实例化

    @Override
    public void run() {
        while (true){
            try {


                lock.lock();

                Thread.sleep(100);

                if (ticket > 0){
                    System.out.println(Thread.currentThread().getName() + "卖票：" + ticket);
                    ticket--;
                }else {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally{
                lock.unlock();
            }





        }


    }
}
