package com.yangy.test.test.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 线程的runnable实现方式
 * </P>
 */
@Slf4j
public class RunnableImpl implements Runnable {

    private String name;

    public RunnableImpl() {
    }

    public RunnableImpl(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " is running !");
    }
}