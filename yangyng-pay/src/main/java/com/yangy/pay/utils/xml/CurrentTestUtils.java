package com.yangy.pay.utils.xml;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 并发测试工具类
 *
 * @author yang yang
 * @create 2018/10/17
 */
public class CurrentTestUtils {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(3);
        Thread t1 = new Thread(new test("t1"));
        t1.start();
        countDownLatch.countDown();
        Thread t2 = new Thread(new test("t2"));
        t2.start();
        countDownLatch.countDown();
        Thread t3 = new Thread(new test("t3"));
        t3.start();
        countDownLatch.countDown();


        List<Object> list = new ArrayList<>();

        list.add("1");
        list.add("2");
        list.add("3");

//        list.parallelStream().forEach();

        countDownLatch.await();

    }
}

class test implements Runnable {

    public test(String str) {
        System.out.println(str);
    }

    @Override
    public void run() {
        //执行的业务方法
        System.out.println("aaa");
    }
}


