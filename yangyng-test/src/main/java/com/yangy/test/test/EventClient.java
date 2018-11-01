package com.yangy.test.test;

import java.util.concurrent.TimeUnit;

/**
 * 客户端
 *
 * @author yang yang
 * @create 2018/10/25
 */
public class EventClient {

    public static void main(String[] args) {

        final EventQueue eventQueue = new EventQueue();

        new Thread(() -> {
            for (; ; ) {
                eventQueue.offer(new EventQueue.Event());
            }
        }, "生产者").start();

        new Thread(() -> {
            for (; ; ) {
                EventQueue.Event take = eventQueue.take();
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "消费者").start();
    }

}