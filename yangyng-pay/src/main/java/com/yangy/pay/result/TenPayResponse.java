package com.yangy.pay.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.yangy.pay.config.TenPayConfig;
import com.yangy.pay.request.BaseTenPayBean;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * 返回结果
 *
 * @author yang yang
 * @create 2018/11/16
 */
@Data
@Slf4j
@EqualsAndHashCode(callSuper = true)
@XStreamAlias("xml")
public class TenPayResponse extends BaseTenPayBean {

    @XStreamAlias("code_url")
    private String codeUrl;
    @XStreamAlias("result_code")
    private String resultCode;
    @XStreamAlias("return_code")
    private String returnCode;
    @XStreamAlias("return_msg")
    private String returnMsg;
    @XStreamAlias("prepay_id")
    private String prepayId;
    @XStreamAlias("trade_type")
    private String tradeType;

    public TenPayResponse(TenPayConfig tenPayConfig) {
        super(tenPayConfig);
    }

    public TenPayResponse() {
    }

    @Override
    public void checkAndSign() {
        super.checkAndSign();
    }
}