package com.juc;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 发令枪
 */
public class TestCountDownLatch {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);
        RunSports rs = new RunSports(latch);
        new Thread(rs, "A").start();
        new Thread(rs, "B").start();
        new Thread(rs, "C").start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----------");

    }

}

class RunSports implements Runnable {

    private CountDownLatch latch;

    public RunSports(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+":"+ i);
        }
        latch.countDown();

    }
}