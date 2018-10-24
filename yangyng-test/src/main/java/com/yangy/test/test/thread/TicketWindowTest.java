package com.yangy.test.test.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yang yang
 * @create 2018/10/20
 */
@Slf4j
public class TicketWindowTest {


    public static void main(String[] args) {
        final TicketWindowThread runable = new TicketWindowThread();

        Thread thread1 = new Thread(runable, "一号");
        Thread thread2 = new Thread(runable, "二号");
        Thread thread3 = new Thread(runable, "三号");
        Thread thread4 = new Thread(runable, "四号");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }


}

class TicketWindowThread implements Runnable {

    private static final int MAX = 500;

    private int index = 1;
    private final static Object READ = new Object();

    @Override
    public void run() {
        synchronized (READ) {
            while (index <= MAX) {
                System.out.println("柜台: " + Thread.currentThread() + " 当前的号码是: " + (index++));
            }
        }
    }
}