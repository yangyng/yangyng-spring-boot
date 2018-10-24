package com.yangy.test.test.thread;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 线程中断
 * </P>
 *
 * @author yang yang
 * @create 2018/10/20
 */
public class ThreadInterrupt {

    public static void main(String[] args) throws InterruptedException {
//        test1();
//        test2();
        test3();
    }

    private static void test1() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("能不能让好好睡一觉！！！");
            }
        });

        thread.start();
        TimeUnit.SECONDS.sleep(2);
        thread.interrupt();
    }

    private static void test2() throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.interrupted());
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        thread.interrupt();
    }

    private static void test3() throws InterruptedException {

        System.out.println("main thread is interrupt ?" + Thread.interrupted());

        Thread.currentThread().interrupt();

        System.out.println("main thread is interrupt ?" + Thread.currentThread().isInterrupted());

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            System.out.println("看样子是不能好好睡觉了!");
        }
    }

}