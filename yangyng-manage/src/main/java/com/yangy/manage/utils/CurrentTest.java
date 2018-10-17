package com.yangy.manage.utils;

import com.alibaba.fastjson.JSON;
import com.yangy.common.utils.HttpUtil;
import com.yangy.manage.entity.SysUser;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author:luyanan
 * @email:luyanan0718@163.com
 * @date 2018/10/17 13:35
 * @introduce
 **/
public class CurrentTest {


    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(4);
        Thread t1 = new Thread(new test(1));
        t1.start();
        countDownLatch.countDown();
        Thread t2 = new Thread(new test(2));
        t2.start();
        countDownLatch.countDown();
        Thread t3 = new Thread(new test(3));
        t3.start();
        countDownLatch.countDown();
        Thread t4 = new Thread(new test(4));
        t4.start();
        countDownLatch.countDown();


        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

class test implements Runnable {

    private  Integer count;

    public test(Integer count) {
        this.count = count;
        System.out.println(count);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            SysUser build = SysUser.builder()
                    .userId(1L)
                    .locked(count)
                    .phone("17777777777")
                    .build();
            String bean = "http://localhost:9330";
            String url = "/user/test/current";

            Map<String, Object> stringMapHashMap = new HashMap<>();
            String post = null;
            try {
                post = HttpUtil.post("application/json", bean, url, JSON.toJSONString(build), 1000, 1000);
                System.out.println(post);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}