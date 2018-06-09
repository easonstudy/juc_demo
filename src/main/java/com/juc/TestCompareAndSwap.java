package com.juc;

/**
 * 模拟CAS算法
 */
public class TestCompareAndSwap {
    public static void main(String[] args) {
        CompareAndSwap cas = new CompareAndSwap();
        for (int i = 0; i < 30; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int expectedValue = cas.get();
                    boolean b = cas.compareAndSet(expectedValue, (int)(Math.random()*100));
                    System.out.println(b);
                }
            }).start();
        }
    }
}

class CompareAndSwap {
    private int value;

    /**
     * 获取内存值
     *
     * @return
     */
    public synchronized int get() {
        return value;
    }

    /**
     * 比较值
     */
    public synchronized int compareAndSwap(int expectedValue, int next) {
        int oldValue = value;

        if (oldValue == expectedValue) {
            this.value = next;
        }
        return oldValue;
    }

    public synchronized boolean compareAndSet(int expectedValue, int next) {
        return expectedValue == compareAndSwap(expectedValue, next);
    }

}