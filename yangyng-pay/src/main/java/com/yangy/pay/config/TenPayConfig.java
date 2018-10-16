package com.yangy.pay.config;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 微信支付相关的配置项
 *
 * @author yang yang
 * @create 2018/10/16
 */
@Component
@RefreshScope
public class TenPayConfig {
    public static String appId;
    public static String mchId;
    public static String signType;
    public static String notifyUrl;
}