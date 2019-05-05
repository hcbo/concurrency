package com.hcb.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CountDownLatchExample2 {

    private final static int threadCount = 200;

    public static void main(String[] args) throws Exception {

        ExecutorService exec = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    Thread.sleep(100);
                    test(threadNum);
                } catch (Exception e) {
                    log.error("exception", e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        // 指定等待时间
        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        log.info("finish");
        // 这个方法会等所有的已有线程执行完,才会销毁线程.
        exec.shutdown();
    }

    private static void test(int threadNum) throws Exception {
//        Thread.sleep(100);
        log.info("{}", threadNum);
    }
}
