package com.yangy.manage.controller;


import com.yangy.common.enums.ResultCode;
import com.yangy.common.exception.BaseException;
import com.yangy.common.model.Result;
import com.yangy.common.utils.MD5Util;
import com.yangy.manage.entity.SysUser;
import com.yangy.manage.pojo.LoginVO;
import com.yangy.manage.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author yangy
 * @since 2018-10-15
 */
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Resource
    private ISysUserService service;

    @Resource
    private RedisTemplate redisTemplate;

    @PostMapping("login")
    public Result login(LoginVO loginVO) {
        /*
         * 必须参数
         * 用户手机号 邮箱
         * 验证码
         *
         *
         * */

        //参数判空
        if (null == loginVO
                || (StringUtils.isEmpty(loginVO.getPhone()) && StringUtils.isEmpty(loginVO.getVerifyCode()))
                || (StringUtils.isEmpty(loginVO.getUsername()) && StringUtils.isEmpty(loginVO.getPassword()))
                || (StringUtils.isEmpty(loginVO.getEmail()) && StringUtils.isEmpty(loginVO.getPassword()))) {
            throw new BaseException(ResultCode.PARAM_ERROR);
        }

        SysUser select = SysUser.builder().phone(loginVO.getPhone()).build();
        String md5Str = MD5Util.md5(loginVO.getPassword() + loginVO.getSalt());

        return null;
    }

    @PostMapping("test/current")
    public Result testCurrent(@RequestBody SysUser user) {
        Boolean notAbsent = redisTemplate.opsForValue().setIfAbsent("key", "value");
        if(!notAbsent){
            System.out.println("该请求不予处理");
        }else {
            System.out.println("处理该请求");
            service.doSomeThing(user);
        }
        return null;
    }
}
