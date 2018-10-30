package com.yangy.test.test.threadpool;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * 基础线程池
 *
 * @author yang yang
 * @create 2018/10/27
 */
public class BasicThreadPool extends Thread implements ThreadPool {

    private final int initSize;

    private final int maxSize;

    private final int coreSize;

    private int activeCount;

    private final ThreadFactory threadFactory;

    private final ThreadQueue threadQueue;

    private volatile boolean isShutdown = false;

    private final Queue<ThreadTask> internalTask = new ArrayDeque();

    private final static DenyPolicy DEFAULT_DENY_POLICY = new DenyPolicy.DiscardDenyPolicy();

    public final long keepAliveTIme;

    private final TimeUnit timeUnit;

    public BasicThreadPool(int initSize, int maxSize, int coreSize, ThreadFactory threadFactory, int queueSize, long keepAliveTIme, TimeUnit timeUnit) {
        this.initSize = initSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.threadFactory = threadFactory;
        this.threadQueue = new LinkedThreadQueue(queueSize, DEFAULT_DENY_POLICY, this);
        this.keepAliveTIme = keepAliveTIme;
        this.timeUnit = timeUnit;
        this.init();
    }

    private void init() {
        start();
        for (int i = 0; i < initSize; i++) {
            newThread();
        }
    }

    private void newThread() {
        InternalTask internalTask = new InternalTask(threadQueue);
        Thread thread = this.threadFactory.createThread(internalTask);
//        ThreadTask threadTask = new ThreadTask(thread, internalTask);
        threadQueue.offer(thread);
        this.activeCount++;
        thread.start();
    }

    @Override
    public void run() {
        while (!isShutdown && !isInterrupted()) {
            try {
                timeUnit.sleep(keepAliveTIme);
            } catch (InterruptedException e) {
                isShutdown = true;
                break;
            }
            synchronized (this) {
                if(isShutdown){
                    break;
                }
            }
        }


    }

    @Override
    public void execute(Runnable runnable) {
        if (this.isShutdown) {
            throw new IllegalStateException("当前线程池已销毁");
        }
        this.threadQueue.offer(runnable);
    }

    @Override
    public void shutdown() {

    }

    @Override
    public int getInitSize() {
        return 0;
    }

    @Override
    public int getMaxSize() {
        return 0;
    }

    @Override
    public int getCoreSize() {
        return 0;
    }

    @Override
    public int getQueueSize() {
        return 0;
    }

    @Override
    public int getActiveCount() {
        return 0;
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    private static class ThreadTask {
        Thread thread;
        InternalTask task;

        public ThreadTask(Thread thread, InternalTask task) {
            this.thread = thread;
            this.task = task;
        }
    }
}