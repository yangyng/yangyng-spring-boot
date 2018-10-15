package com.yangy.manage.controller;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yangy.common.enums.ResultCode;
import com.yangy.common.exception.BaseException;
import com.yangy.common.model.Result;
import com.yangy.common.utils.MD5Util;
import com.yangy.manage.entity.SysUser;
import com.yangy.manage.pojo.LoginVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
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
    private IService<SysUser> service;

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


}
