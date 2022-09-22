package com.hznu.thread;

/**
 * @author LIN
 * @date 2022/8/16 16:44
 */
public class ThreadDemo {
    public static void main(String[] args) {

        //创建Thread类的匿名子类的方式
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                    }
                }
            }
        }.start();

        new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                if (i % 2 != 0) {
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                }
            }
        }).start();


    }
}

