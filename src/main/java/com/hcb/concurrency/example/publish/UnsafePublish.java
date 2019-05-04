package com.hcb.concurrency.example.publish;

import com.hcb.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 对象发布的演示
 */
@Slf4j
@NotThreadSafe
public class UnsafePublish {

    private String[] states = {"a", "b", "c"};

    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        // 通过new的方式发布了 类的实例
        UnsafePublish unsafePublish = new UnsafePublish();

        log.info("{}", Arrays.toString(unsafePublish.getStates()));

        //通过这个实例提供公有方法得到了私有域的引用,通过引用可以在任何线程修改私有域,所以在任何线程中使用这个域,
        // 其内容是不确定的,所以是线程不安全的.
        unsafePublish.getStates()[0] = "d";
        log.info("{}", Arrays.toString(unsafePublish.getStates()));
    }
}
