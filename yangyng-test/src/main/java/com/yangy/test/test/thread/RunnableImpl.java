package com.yangy.test.test.thread;

import lombok.extern.slf4j.Slf4j;

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