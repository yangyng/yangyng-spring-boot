package com.yangy.test.test;

import java.util.LinkedList;

/**
 * 自定义队列
 *
 * @author yang yang
 * @create 2018/10/25
 */
public class EventQueue {

    private final int MAX;

    static class Event {
    }

    public static final int MAX_SIZE = 10;

    public final LinkedList<Event> eventQueue = new LinkedList<>();

    public EventQueue() {
        this(MAX_SIZE);
    }

    public EventQueue(int max) {
        this.MAX = max;
    }


    public void offer(Event event) {
        synchronized (eventQueue) {
            if (eventQueue.size() >= MAX) {
                try {
                    console("the queue is full");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            eventQueue.addLast(event);
            eventQueue.notify();
        }
    }


    public Event take() {
        synchronized (eventQueue) {
            if (eventQueue.isEmpty()) {
                try {
                    console("the queue is empty");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Event event = eventQueue.removeFirst();
            console("take msg");
            this.eventQueue.notify();
            return event;
        }
    }

    private void console(String msg) {
        System.out.println(Thread.currentThread().getName() + msg);
    }

}