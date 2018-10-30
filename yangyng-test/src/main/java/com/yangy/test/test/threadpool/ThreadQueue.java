package com.yangy.test.test.threadpool;

public interface ThreadQueue {

    //提交任务
    void offer(Runnable runnable) ;

    //消费任务
    Runnable take() throws InterruptedException;

    void  remove() ;

    //任务队列中任务的数量
    int size();

}
