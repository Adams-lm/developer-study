package com.hznu;


import com.hznu.thread.ThreadUnsafeExample;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author LIN
 * @date 2022/4/17 11:48
 */
public class DemoTest {

    @Test
    public void timeTest() {
        aa();
    }

    public static void aa(Object... params){
        Integer a = 1;
        System.out.println(a == null);
    }

    @Test
    public void mm() throws InterruptedException {
        final int threadSize = 1000;
        ThreadUnsafeExample example = new ThreadUnsafeExample();
        final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadSize; i++) {
            executorService.execute(() -> {
                example.add();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(example.get());

    }

}


