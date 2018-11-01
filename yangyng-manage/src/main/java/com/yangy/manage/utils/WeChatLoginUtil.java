package com.yangy.manage.utils;

import com.yangy.common.config.ThirdLoginConfig;
import com.yangy.common.config.ThirdURLConstant;
import com.yangy.common.config.WeChatConfig;
import com.yangy.common.utils.HttpUtil;
import org.apache.http.Consts;

import java.net.URLEncoder;

/**
 * 微信第三方登录工具类
 *
 * @author yang yang
 * @email java_yangy@126.com
 * @create 2018/8/8
 * @since 1.0.0
 */
public class WeChatLoginUtil {

    public static void getCode() throws Exception {
        String encode = URLEncoder.encode("http://tfaudc.natappfree.cc/test/wxLoginCallback", Consts.UTF_8.name());

        String getCodeUrl = WeChatConfig.getGetCodeUrl();

        String url = "https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
        url = url.replace("APPID", "wxcc5884c547bc6752")
                .replace("REDIRECT_URI", encode)
                .replace("SCOPE", "snsapi_login")
                .replace("STATE", "GzB5zUwo543UutSepSUX")
        ;

        System.out.println(url);
    }

    public static void main(String[] args) throws Exception {
        getWxAccessToken("023ML2xf2b2UJB0WR1xf2SCaxf2ML2x-");
    }

//    wx2bd356d559f8a675
//    64ae515d4303589ca3204ca32f7c8073

//    String urlStr = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code"
//            .replace("APPID", "wx0d20c40ca4f02b45")
//            .replace("SECRET", "7f1a302b7fe2c1228b0ec64809a1109b")
//            .replace("CODE", code);


    public static void getWxAccessToken(String code) throws Exception {
        String urlStr = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET"
                .replace("APPID", "wx2bd356d559f8a675")
                .replace("APPSECRET", "64ae515d4303589ca3204ca32f7c8073");

//        String url = ThirdURLConstant.WX_GET_ACCESS_TOKEN
//                .replace("APPID", ThirdLoginConfig.wxAppId)
//                .replace("SECRET", ThirdLoginConfig.wxAppSecret)
//                .replace("CODE", code);

        String result = HttpUtil.get(urlStr, null);
        System.out.println(result);
    }


}