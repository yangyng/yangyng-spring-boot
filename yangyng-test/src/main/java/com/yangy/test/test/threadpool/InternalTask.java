package com.yangy.test.test.threadpool;

/**
 * 线程池内部类的实现
 *
 * @author yang yang
 * @create 2018/10/27
 */
public class InternalTask implements Runnable {

    public final ThreadQueue threadQueue;

    private volatile boolean running = true;

    public InternalTask(ThreadQueue threadQueue) {
        this.threadQueue = threadQueue;
    }

    @Override
    public void run() {
        while (running && !Thread.currentThread().isInterrupted()) {
            try {
                Runnable take = threadQueue.take();
                take.run();
            } catch (InterruptedException e) {
                running = false;
                break;
            }
        }
    }
}