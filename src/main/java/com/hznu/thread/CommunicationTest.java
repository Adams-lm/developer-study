package com.hznu.thread;

/**
 * @author LIN
 * @date 2022/8/17 14:27
 */
public class CommunicationTest {
    public static void main(String[] args) {
        NumThread numThread = new NumThread();

        Thread thread1 = new Thread(numThread);
        Thread thread2 = new Thread(numThread);

        thread1.setName("线程1:");
        thread2.setName("线程2:");

        thread1.start();
        thread2.start();
    }
}




