package com.yangy.pay.controller;

import com.alibaba.fastjson.JSON;
import com.yangy.common.model.Result;
import com.yangy.common.okhttp.utils.OkHttpUtil;
import com.yangy.pay.config.TenPayConfig;
import com.yangy.pay.config.TenUrlConfig;
import com.yangy.pay.request.TenPayUnifiedOrderRequest;
import com.yangy.pay.result.TenPayResponse;
import com.yangy.pay.utils.IPUtils;
import com.yangy.pay.utils.xml.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

/**
 * 测试控制器
 *
 * @author yang yang
 * @create 2018/11/15
 */
@RestController
@RequestMapping("test")
@Slf4j
public class TestController {

    @Resource
    private TenPayConfig tenPayConfig;

    @PostMapping("test")
    public Result get() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取请求对象
        HttpServletRequest request = requestAttributes.getRequest();
        String ip = IPUtils.getIpAddr(request);

        TenPayUnifiedOrderRequest orderRequest = new TenPayUnifiedOrderRequest(tenPayConfig);
        orderRequest.setTotalFee(100);
        orderRequest.setIP(ip);
        orderRequest.setTradeType("NATIVE");
        orderRequest.setBody("测试");
        String num = UUID.randomUUID().toString().replace("-", "").substring(0, 31);
        orderRequest.setOutTradeNo(num);
        orderRequest.setProductId("123456");

        orderRequest.checkAndSign();
        String xml = orderRequest.toXml();
        log.info("\n" + xml);

        String xmlParams = OkHttpUtil.postXmlParams(TenUrlConfig.UNIFIED_ORDER, xml);
        log.info("\n" + xmlParams);

        TenPayResponse response = (TenPayResponse) new TenPayResponse().toBean(xmlParams);
        boolean sign = response.checkSign(tenPayConfig);
        return new Result<String>().ok(xml);
    }
}