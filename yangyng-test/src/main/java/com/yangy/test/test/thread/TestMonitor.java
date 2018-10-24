package com.yangy.test.test.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author yang yang
 * @create 2018/10/24
 */
public class TestMonitor {

    public static final Object MUTEX = new Object();

    public static void main(String[] args) {

        final TestMonitor testMonitor = new TestMonitor();

        for (int i = 0; i < 10; i++) {
            new Thread(testMonitor::doSomeThing).start();
        }
    }


    public void doSomeThing() {

        synchronized (MUTEX) {
            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}