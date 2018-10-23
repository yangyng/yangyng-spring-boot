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
        for (int i = 0; i < 15; i++) {
            SysUser build = SysUser.builder()
                    .userId(1L)
                    .locked(count)
                    .phone("17777777777")
                    .build();
            String bean = "http://localhost:81";
//            String url = "/user/test/current";
            String url = "/pay/pay/tencent/notify";

            String str = "<xml>\n" +
                    "  <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                    "  <attach><![CDATA[支付测试]]></attach>\n" +
                    "  <bank_type><![CDATA[CFT]]></bank_type>\n" +
                    "  <fee_type><![CDATA[CNY]]></fee_type>\n" +
                    "  <is_subscribe><![CDATA[Y]]></is_subscribe>\n" +
                    "  <mch_id><![CDATA[10000100]]></mch_id>\n" +
                    "  <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>\n" +
                    "  <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>\n" +
                    "  <out_trade_no><![CDATA[1409811653]]></out_trade_no>\n" +
                    "  <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                    "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                    "  <sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign>\n" +
                    "  <sub_mch_id><![CDATA[10000100]]></sub_mch_id>\n" +
                    "  <time_end><![CDATA[20140903131540]]></time_end>\n" +
                    "  <total_fee>1</total_fee>\n" +
                    "<coupon_fee_0><![CDATA[10]]></coupon_fee_0>\n" +
                    "<coupon_count><![CDATA[1]]></coupon_count>\n" +
                    "<coupon_type><![CDATA[CASH]]></coupon_type>\n" +
                    "<coupon_id><![CDATA[10000]]></coupon_id> \n" +
                    "  <trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                    "  <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>\n" +
                    "</xml>";

            Map<String, Object> stringMapHashMap = new HashMap<>();
            String post = null;
            try {
                post = HttpUtil.post("application/json", bean, url, str, 1000, 1000);
                System.out.println(post);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}