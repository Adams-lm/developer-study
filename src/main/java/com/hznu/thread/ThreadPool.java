package com.hznu.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author LIN
 * @date 2022/8/25 14:21
 */
class PoolTest1 implements Runnable{
    @Override
    public void run() {
        for(int i = 0;i <= 100;i++){
            if(i % 2 == 0){
                int s1 = 1;
                int i2 = 1;
                int b3 = 1;
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}

class PoolTest2 implements Runnable{
    @Override
    public void run() {
        for(int i = 0;i <= 100;i++){
            if(i % 2 != 0){
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}

public class ThreadPool {
    public static void main(String[] args) {
        //1. 提供指定线程数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;
        //设置线程池的属性
        System.out.println(service.getClass());
        service1.setCorePoolSize(15);
        // 2.执行指定的线程的操作。需要提供实现Runnable接口或Callable接口实现类的对象
        // 适合适用于Runnable
        service.execute(new PoolTest1());
        service.execute(new PoolTest2());
        //service.submit(Callable callable);//适合使用于Callable
        //3.关闭连接池
        service.shutdown();

    }

}

