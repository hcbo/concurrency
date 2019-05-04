package com.hcb.concurrency.example.threadLocal;

public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id) {
        // ThreadLocal会维持一个map ,键为线程号,值为我们要封闭的对象
        requestHolder.set(id);
    }

    public static Long getId() {
        // 通过线程号,能拿到对象
        return requestHolder.get();
    }

    public static void remove() {
        // 手动移除
        requestHolder.remove();
    }
}
