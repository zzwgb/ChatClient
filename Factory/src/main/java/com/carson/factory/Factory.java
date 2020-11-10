package com.carson.factory;

import com.carson.common.app.Application;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Author: Create by Carson on 2020/11/10
 */
public class Factory {

    // 单例模式
    private static final Factory instance;
    // 全局的线程池
    private final Executor executor;

    static {
        instance = new Factory();
    }


    private Factory() {
        // 新建一个4个线程的线程池
        executor = Executors.newFixedThreadPool(4);
    }

    /**
     * 返回全局的Application
     *
     * @return Application
     */
    public static Application app() {
        return Application.getInstance();
    }

    /**
     * 异步运行的方法
     *
     * @param runnable Runnable
     */
    public static void runOnAsync(Runnable runnable) {
        // 拿到单例，拿到线程池，然后异步执行
        instance.executor.execute(runnable);
    }
}
