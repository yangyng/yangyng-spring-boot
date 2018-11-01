package com.yangy.common.config;

/**
 * 三方相关的路径
 *
 * @author yang yang
 * @create 2018/10/30
 */
public interface ThirdURLConstant {

    /**
     * <p>
     * 获取微信access_token
     * </P>
     */
    String WX_GET_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    /**
     * <p>
     * 通过该接口获取微信
     * openid session_key unionid
     * </P>
     */
    String MINIAPP_JSCODE2SESSION = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";

    /**
     * <p>
     * 获取微信用户信息
     * </P>
     */
    String WX_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";

}