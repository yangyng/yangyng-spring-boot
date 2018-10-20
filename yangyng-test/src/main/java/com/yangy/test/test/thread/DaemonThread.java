package com.yangy.test.test.thread;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 守护线程
 * </p>
 *
 * @author yang yang
 * @create 2018/10/20
 */
public class DaemonThread {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() ->
        {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.setDaemon(true);

        thread.start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("main thread end");
    }

}