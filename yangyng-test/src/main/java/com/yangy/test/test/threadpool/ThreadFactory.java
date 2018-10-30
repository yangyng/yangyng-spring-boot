package com.yangy.test.test.threadpool;

/**
 * 创建线程的工厂方法
 *
 * @author yang yang
 * @create 2018/10/27
 */
@FunctionalInterface
public interface ThreadFactory {
    //创建线程
    Thread createThread(Runnable runnable);

}