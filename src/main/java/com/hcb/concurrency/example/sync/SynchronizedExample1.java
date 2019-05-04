package com.hcb.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


//作用于调用的对象,即同一个对象不能同时执行对象中的同步代码块,同步方法.不同的对象调用该同步代码块和同步方法不受影响.
// 而且像下边这种方法的所有代码都在一个同步代码块之中,其实是和同步方法没有区别的.
// 而且子类继承不了父类synchronized的feature,因为synchronized的实现不是方法的声明.

@Slf4j
public class SynchronizedExample1 {

    // 修饰一个代码块
    public void test1(int j) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 {} - {}", j, i);
            }
        }
    }

    // 修饰一个方法
    public synchronized void test2(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test2 {} - {}", j, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
/*        //模拟同一个对象同时调用同步代码块
        executorService.execute(() -> {
            example1.test1(1);
        });
        executorService.execute(() -> {
            example1.test1(2);
        });*/

     /*   //模拟同一个对象同时调用同步方法
        executorService.execute(() -> {
            example1.test2(1);
        });
        executorService.execute(() -> {
            example1.test2(2);
        });
*/
   /*     //模拟不同对象同时调用同步代码块
        executorService.execute(() -> {
            example1.test1(1);
        });
        executorService.execute(() -> {
            example2.test1(2);
        });*/

        //模拟不同对象同时调用同步方法
        executorService.execute(() -> {
            example1.test2(1);
        });
        executorService.execute(() -> {
            example2.test2(2);
        });
    }
}
