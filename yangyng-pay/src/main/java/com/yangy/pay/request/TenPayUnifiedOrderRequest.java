package com.yangy.pay.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.yangy.common.exception.BaseException;
import com.yangy.common.utils.DateUtils;
import com.yangy.pay.config.TenPayConfig;
import com.yangy.pay.enums.SignType;
import com.yangy.pay.utils.xml.SignUtils;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * 统一下单请求
 *
 * @author yang yang
 * @create 2018/11/15
 */
@Data
@Slf4j
@Builder
@EqualsAndHashCode(callSuper = true)
@XStreamAlias("xml")
public class TenPayUnifiedOrderRequest extends BaseTenPayBean {

    @XStreamAlias("trade_type")
    private String tradeType;
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
    @XStreamAlias("time_expire")
    private String timeExpire;
    @XStreamAlias("time_start")
    private String timeStart;
    @XStreamAlias("product_id")
    private String productId;

    public TenPayUnifiedOrderRequest(TenPayConfig tenPayConfig) {
        super(tenPayConfig);
        this.notifyUrl = tenPayConfig.getNotifyUrl();
        this.timeStart = DateUtils.getTimStrByFormat("yyyyMMddHHmmss");
        this.timeExpire = DateUtils.getTimStrByFormat(DateUtils.getHourLater(), "yyyyMMddHHmmss");
    }

    @Override
    public void checkAndSign() {
        super.checkAndSign();
        if (StringUtils.isEmpty(getTradeType())) {
            throw new BaseException("");
        }
        if (0 == this.totalFee) {
            throw new BaseException("无效的金额");
        }
    }
}