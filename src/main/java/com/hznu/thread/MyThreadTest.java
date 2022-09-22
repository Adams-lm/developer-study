package com.hznu.thread;

/**
 * @author LIN
 * @date 2022/8/16 16:34
 */
//1.继承Thread类
class MyThread extends Thread {
    public MyThread() {
    }

    //2.重run方法
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }
}

class ThreadTest {
    public static void main(String[] args) {
//3.新建Thread对象
        MyThread myThread = new MyThread();
        //4.调用start方法
        myThread.start();
    }
}

