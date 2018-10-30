package com.yangy.test.test.threadpool;

/**
 * 线程池接口
 *
 * @author yang yang
 * @create 2018/10/27
 */
public interface ThreadPool {

    //提交任务
    void execute(Runnable runnable);

    //关闭销毁线程池
    void shutdown();

    //线程池的初始线程数量
    int getInitSize();

    //线程池的最大线程数
    int getMaxSize();

    //线程池的核心线程数
    int getCoreSize();

    //队列中任务数量
    int getQueueSize();

    //激活线程数
    int getActiveCount();

    //当区阿尼线程池是否已被销毁
    boolean isShutdown();

}