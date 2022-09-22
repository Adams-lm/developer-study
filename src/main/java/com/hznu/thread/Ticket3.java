package com.hznu.thread;

/**
 * @author LIN
 * @date 2022/8/17 11:27
 */
public class Ticket3 implements Runnable {
    private int tick = 100;
    private boolean isFlag = true;

    @Override
    public void run() {
        while (isFlag) {
            show();
        }
    }

    /**
     *  同步show方法，继承Thread类方法一样，只需同步方法即可，同时需要给方法加static关键字，确保不会创建多个对象
     */
    public synchronized void show() {
        if (tick > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "号窗口买票，票号为：" + tick--);
        } else {
            isFlag = false;
        }
    }
}

class TicketTest3 {
    public static void main(String[] args) {
        Ticket3 ticket = new Ticket3();

        Thread thread1 = new Thread(ticket);
        Thread thread2 = new Thread(ticket);
        Thread thread3 = new Thread(ticket);

        thread1.setName("窗口1");
        thread2.setName("窗口2");
        thread3.setName("窗口3");

        thread1.start();
        thread2.start();
        thread3.start();

    }
}
