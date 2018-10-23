package com.yangy.test.test.thread;

import java.util.concurrent.CountDownLatch;

/**
 * <p>
 * countDownLatch使用方式
 * </p>
 *
 * @author yang yang
 * @create 2018/10/20
 */
public class CountDown {


    public static void main(String[] args) {

        int count = 100;
        CountDownLatch countDownLatch = new CountDownLatch(count);

        for (int i = 0; i < count; i++) {
            RunnableImpl r1 = new RunnableImpl("r" + i);
            new Thread(r1).start();
            countDownLatch.countDown();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}