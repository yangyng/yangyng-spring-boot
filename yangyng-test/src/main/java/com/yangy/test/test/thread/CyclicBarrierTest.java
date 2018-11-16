package com.yangy.test.test.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 实现代码
 *
 * @author yang yang
 * @create 2018/11/12
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(10);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new TestRunnableThread(barrier));
            thread.start();
        }

    }

}

class TestRunnableThread implements Runnable {

    private CyclicBarrier cyclicBarrier;

    public TestRunnableThread(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }


    @Override
    public void run() {

        String name = Thread.currentThread().getName();
        System.out.println("线程名称： " + name);
        System.out.println("等待线程数量： " + cyclicBarrier.getNumberWaiting());

        try {
            cyclicBarrier.await();
            System.out.println("线程: "+name+"的代码开始执行了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}