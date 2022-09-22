package com.hznu.thread;

/**
 * @author LIN
 * @date 2022/8/17 14:29
 */
public class NumThread implements Runnable {
    private int number = 1;
    private Object object = new Object();

    @Override
    public void run() {
        while (true) {
            synchronized (object) {
                object.notify();//调用notify()方法唤醒线程
                if (number <= 100) {
                    //线程休眠
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + number++);

                    try {
                        object.wait();//打印输出一次后调用wait()方法将线程阻塞
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            }
        }
    }
}
