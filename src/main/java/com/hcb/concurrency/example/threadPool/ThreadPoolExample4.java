package com.hcb.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolExample4 {

    public static void main(String[] args) {

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

//        executorService.schedule(new Runnable() {
//            @Override
//            public void run() {
//                log.warn("schedule run");
//            }
//        }, 3, TimeUnit.SECONDS);

        // 延迟1秒开始执行,每3秒执行一次
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.warn("schedule run");
            }
        }, 1, 3, TimeUnit.SECONDS);

//        executorService.shutdown();

        /*Timer timer = new Timer();
        // 从当前时间开始执行,2秒钟执行一次
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.warn("timer run");
            }
        }, new Date(), 2* 1000);*/
    }
}
