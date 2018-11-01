package com.yangy.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * 三方登录相关的配置项
 *
 * @author yang yang
 * @create 2018/10/30
 */
@Component
@ComponentScan
public class ThirdLoginConfig {

    public static String wxAppId;
    public static String wxAppSecret;
    public static String wxMchId;
}