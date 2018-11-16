package com.yangy.test.test.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 计数信号量测试
 *
 * @author yang yang
 * @create 2018/11/12
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(10);

        for (int i = 0; i < 100; i++) {
            TestRunnableThread1 runnable = new TestRunnableThread1(semaphore);
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
}

class TestRunnableThread1 implements Runnable {

    private Semaphore semaphore;

    public TestRunnableThread1(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {

        if (semaphore.availablePermits() > 0) {
            try {
                System.out.println("acquire是否公平: " + semaphore.isFair());
                System.out.println("acquire队列长度: " + semaphore.getQueueLength());
                System.out.println("acquire是否存在队列线程: " + semaphore.hasQueuedThreads());
                semaphore.acquire();
                TimeUnit.SECONDS.sleep(10);
                System.out.println(Thread.currentThread().getName() + "就决定是你了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
                System.out.println("release是否公平: " + semaphore.isFair());
                System.out.println("release队列长度: " + semaphore.getQueueLength());
                System.out.println("release是否存在队列线程: " + semaphore.hasQueuedThreads());
            }
        }
    }
}

