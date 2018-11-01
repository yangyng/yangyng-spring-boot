package com.yangy.common.utils.login.wechat;

import com.alibaba.fastjson.JSONObject;
import com.yangy.common.config.ThirdLoginConfig;
import com.yangy.common.config.ThirdURLConstant;
import com.yangy.common.model.ThirdLoginModel;
import com.yangy.common.utils.AesCbcUtil;
import com.yangy.common.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * 微信第三方登录工具类
 *
 * @author yang yang
 * @email java_yangy@126.com
 * @create 2018/8/8
 * @since 1.0.0
 */
@Slf4j
public class WeChatLoginUtil {

    /**
     * <p>
     * 根据code获取用户 openId及 unionId sessionKey
     * </p>
     *
     * @param code
     * @return
     */
    public static ThirdLoginModel miniAppLogin(String code) {
        String url = ThirdURLConstant.MINIAPP_JSCODE2SESSION
                .replace("APPID", ThirdLoginConfig.wxAppId)
                .replace("SECRET", ThirdLoginConfig.wxAppSecret)
                .replace("JSCODE", code);
        String resultStr = null;

        try {
            resultStr = HttpUtil.get(url, null);
        } catch (Exception e) {
            log.error("请求失败");
            return null;
        }
        JSONObject resultJSON = JSONObject.parseObject(resultStr);
        String sessionKey = String.valueOf(resultJSON.get("session_key"));
        String openId = String.valueOf(resultJSON.get("openid"));
        return ThirdLoginModel.builder().sessionKey(sessionKey).openId(openId).build();
    }

    /**
     * <p>
     * 小程序获取用户信息 包含加密数据
     * </P>
     *
     * @param data
     * @param code
     * @param iv
     * @return
     * @throws Exception
     */
    public static ThirdLoginModel miniAppUserInfo(String data, String code, String iv) throws Exception {
        ThirdLoginModel loginModel = miniAppLogin(code);
        if (null == loginModel || StringUtils.isEmpty(loginModel.getSessionKey())) {
            log.error("获取token失败");
            return null;
        }
        String decodeData = AesCbcUtil.decrypt(data, loginModel.getSessionKey(), iv, "UTF-8");
        ThirdLoginModel model = JSONObject.parseObject(decodeData, ThirdLoginModel.class);
        return model;
    }
}