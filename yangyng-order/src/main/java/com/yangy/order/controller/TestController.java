package com.yangy.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 *
 * @author yang yang
 * @create 2018/11/6
 */
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("test/{info}")
    public String test(@PathVariable("info") String info) {
        return "hahah" + info;
    }


}