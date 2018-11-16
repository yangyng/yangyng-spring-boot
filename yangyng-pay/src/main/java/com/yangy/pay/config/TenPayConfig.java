package com.yangy.pay.config;

import com.yangy.common.exception.BaseException;
import com.yangy.pay.enums.SignType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * 微信支付相关的配置项
 *
 * @author yang yang
 * @create 2018/10/16
 */
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "tenpay")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenPayConfig {
    private String appId;
    private String mchId;
    private String signType;
    private String notifyUrl;
    private String privateKey;

    @Value("{tenpay.app-id}")
    public void setAppId(String appId) {
        if (StringUtils.isEmpty(appId)) {
            throw new BaseException("请检查配置文件");
        }
        this.appId = appId;
    }

    @Value("{tenpay.mch-id}")
    public void setMchId(String mchId) {
        if (StringUtils.isEmpty(mchId)) {
            throw new BaseException("请检查配置文件");
        }
        this.mchId = mchId;
    }

    public void setSignType(String signType) {
        this.signType = SignType.MD5.getStr();
    }

    @Value("{tenpay.notify-url}")
    public void setNotifyUrl(String notifyUrl) {
        if (StringUtils.isEmpty(notifyUrl)) {
            throw new BaseException("请检查配置文件");
        }
        this.notifyUrl = notifyUrl;
    }

    @Value("{tenpay.private-key}")
    public void setPrivateKey(String privateKey) {
        if (StringUtils.isEmpty(privateKey)) {
            throw new BaseException("请检查配置文件");
        }
        this.privateKey = privateKey;
    }
}