package com.yangy.pay.request;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.yangy.pay.config.TenPayConfig;
import com.yangy.pay.enums.SignType;
import com.yangy.pay.utils.xml.SignUtils;
import com.yangy.pay.utils.xml.XStreamInitializer;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * 微信支付基础请求对象
 *
 * @author yang yang
 * @create 2018/10/16
 */
@Data
@XStreamAlias("xml")
public abstract class BaseTenPayRequest implements Serializable {

    @XStreamAlias("appid")
    private String appId = TenPayConfig.appId;
    @XStreamAlias("mch_id")
    private String mchId = TenPayConfig.mchId;
    @XStreamAlias("noce_str")
    private String nonceStr = getNonceStr();

    @XStreamAlias("sign")
    private String sign;
    @XStreamAlias("body")
    private String body;
    @XStreamAlias("out_trade_no")
    private String outTradeNo;
    @XStreamAlias("total_fee")
    private int totalFee;
    @XStreamAlias("spbill_create_ip")
    private String IP;
    @XStreamAlias("notify_url")
    private String notifyUrl;

    private String signType = SignType.MD5.getStr();
    private String deviceInfo;

    public String getNonceStr() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32)
    }

    public static int yuanToFen(String yuan) {
        return new BigDecimal(yuan).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue();
    }

    public String toXml() {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(this.getClass());
        return xstream.toXML(this);
    }

    public void checkAndSign() {
        if (StringUtils.isBlank(this.mchId)) {
            this.mchId = TenPayConfig.mchId;
        }
        if (StringUtils.isBlank(this.appId)) {
            this.appId = TenPayConfig.appId;
        }
        if (StringUtils.isBlank(this.signType)) {
            this.signType = SignType.MD5.getStr();
        }
        if (StringUtils.isBlank(getNonceStr())) {
            this.setNonceStr(getNonceStr());
        }

        //设置签名字段的值
//        this.setSign(SignUtils.generateSignature(this, this.getSignType(), TenPayConfig.getMchKey(), this.getIgnoredParamsForSign()));
    }


}