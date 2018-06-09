package com.juc;

import org.junit.Test;

/**
 * volatile : 保证内存可见性
 * synchronized 轻量级的同步锁
 * <p>
 * 注意:
 * volatile 不具备 “互斥性”   ： 所有线程都能访问, 没加锁
 * 不可以保证数据“原子性”
 */
public class TestVolatile {

    @Test
    public void test() {
        ThreadDemo threadDemo = new ThreadDemo();
        new Thread(threadDemo).start();

        while (true) {
            if (threadDemo.isIsbool()) {
                System.out.println("-----------");
                break;
            }
        }
    }
}

class ThreadDemo implements Runnable {

    volatile boolean isbool = false;

    public void run() {
        try {
            Thread.sleep(200);
            isbool = true;
            System.out.println("isbool1:" + isbool);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*try {
            Thread.sleep(200);
            isbool = false;
            System.out.println("isbool2:" + isbool);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public boolean isIsbool() {
        return isbool;
    }

    public void setIsbool(boolean isbool) {
        this.isbool = isbool;
    }
}