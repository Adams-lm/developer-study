package com.hznu.thread;

/**
 * @author LIN
 * @date 2022/8/17 11:26
 */
public class Ticket2 extends Thread {
    private static int tick = 100;
    private static Object object = new Object();

    public Ticket2() {
    }

    @Override
    public void run() {

        while (true) {
            synchronized (object) {
                //synchronized (Ticket2.class) {//通过反射调用当前类
                if (tick > 0) {
                    System.out.println(Thread.currentThread().getName() + "号窗口买票，票号为" + tick--);
                } else {
                    break;
                }
            }

        }
    }
}

class TicketTest2 {
    public static void main(String[] args) {
        Ticket2 ticket1 = new Ticket2();
        Ticket2 ticket2 = new Ticket2();
        Ticket2 ticket3 = new Ticket2();

        ticket1.setName("窗口1");
        ticket2.setName("窗口2");
        ticket3.setName("窗口3");

        ticket1.start();
        ticket2.start();
        ticket3.start();


    }
}

