package com.yangy.pay.config;

public interface TenUrlConfig {

    String UNIFIED_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    String ORDER_QUERY = "https://api.mch.weixin.qq.com/pay/orderquery";
    String CLOSE_ORDER = "https://api.mch.weixin.qq.com/pay/closeorder";
    String REFUND = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    String REFUND_QUERY = "https://api.mch.weixin.qq.com/pay/refundquery";
    String DOWNLOAD_BILL = "https://api.mch.weixin.qq.com/pay/downloadbill";
    String DOWNLOAD_FUND_FLOW = "https://api.mch.weixin.qq.com/pay/downloadfundflow";
    String REPORT = "https://api.mch.weixin.qq.com/payitil/report";



}
