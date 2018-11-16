package com.yangy.pay.request;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.yangy.common.exception.BaseException;
import com.yangy.pay.config.TenPayConfig;
import com.yangy.pay.enums.SignType;
import com.yangy.pay.utils.xml.SignUtils;
import com.yangy.pay.utils.xml.XStreamInitializer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public abstract class BaseTenPayBean implements Serializable {

    @XStreamAlias("appid")
    protected String appId;
    @XStreamAlias("mch_id")
    protected String mchId;
    @XStreamAlias("nonce_str")
    protected String nonceStr = getNonceStr();
    @XStreamAlias("sign")
    protected String sign;
    @XStreamAlias("sign_type")
    protected String signType;

    @XStreamOmitField
    protected static TenPayConfig tenPayConfig;
    @XStreamOmitField
    private String key;

    public BaseTenPayBean(TenPayConfig tenPayConfig) {
        this.tenPayConfig = tenPayConfig;
        this.key = tenPayConfig.getPrivateKey();
        this.appId = tenPayConfig.getAppId();
        this.mchId = tenPayConfig.getMchId();
        this.signType = tenPayConfig.getSignType();
    }

    public BaseTenPayBean() {
    }

    public String getNonceStr() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }

    /**
     * 元转分
     *
     * @param yuan
     * @return
     */
    public static int yuanToFen(String yuan) {
        return new BigDecimal(yuan).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue();
    }

    /**
     * <p>
     * 获取XML
     * </P>
     *
     * @return
     */
    public String toXml() {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(this.getClass());
        return xstream.toXML(this);
    }

    public Object toBean(String xml) {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(this.getClass());
        return xstream.fromXML(xml);
    }

    /**
     * <p>
     * 参数校验
     * </P>
     */
    public void checkAndSign() {
        //TODO
        if (StringUtils.isBlank(this.mchId)) {
            this.mchId = tenPayConfig.getMchId();
        }
        if (StringUtils.isBlank(this.appId)) {
            this.appId = tenPayConfig.getAppId();
        }
        if (StringUtils.isBlank(this.signType)) {
            this.signType = tenPayConfig.getSignType();
        }
        if (StringUtils.isBlank(getNonceStr())) {
            this.setNonceStr(getNonceStr());
        }
        String str = null;
        try {
            str = SignUtils.generateSignature(SignUtils.xmlBean2Map(this), this.key, SignType.MD5.getStr());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.sign = str;
        log.info(str);
    }

    /**
     * <p>
     * 参数校验
     * </P>
     */
    public boolean checkSign(TenPayConfig tenPayConfig) {
        //设置签名字段的值
        if (StringUtils.isEmpty(this.sign)) {
            throw new BaseException("无效的签名");
        }
        String str = null;
        try {
            str = SignUtils.generateSignature(SignUtils.xmlBean2Map(this), tenPayConfig.getPrivateKey(), SignType.MD5.getStr());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.sign.equals(str);
    }
}