package com.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 实现线程的4种方式
 *  callable和Runnable区别 ： 结合FutureTask使用,并带有返回值
 */
public class TestCallable {

    public static void main(String[] args) {
        FutureTask<Integer> task = new FutureTask<Integer>(() -> {
            Thread.sleep(200);
            return 0;
        });
        //结合FutureTask 跑
        new Thread(task).start();
        try {
            Integer call = task.get();
            System.out.println("------------:" + call);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

class CallableThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 0;
    }
}

