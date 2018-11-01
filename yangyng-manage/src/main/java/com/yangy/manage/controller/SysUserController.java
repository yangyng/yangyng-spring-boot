package com.yangy.manage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangy.common.enums.ResultCode;
import com.yangy.common.exception.BaseException;
import com.yangy.common.model.MpPage;
import com.yangy.common.model.Result;
import com.yangy.common.utils.MD5Util;
import com.yangy.manage.entity.SysUser;
import com.yangy.manage.pojo.LoginVO;
import com.yangy.manage.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author yangy
 * @since 2018-10-15
 */
@RestController
@CrossOrigin
@RequestMapping("/sysuser")
public class SysUserController {

    @Resource
    private ISysUserService service;

    /**
     * <p>
     * 添加系统用户
     * </P>
     *
     * @param sysUser
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody SysUser sysUser) {
        boolean save = service.save(sysUser);
        return new Result<Boolean>().ok(save);
    }

    /**
     * <p>
     * 批量添加用户
     * </P>
     *
     * @param collection
     * @return
     */
    @PostMapping("save/batch")
    public Result saveBatch(@RequestBody Collection<SysUser> collection) {
        boolean save = service.saveBatch(collection);
        return new Result<Boolean>().ok(save);
    }

    /**
     * <p>
     * 修改用户信息
     * </P>
     *
     * @param sysUser
     * @return
     */
    @PostMapping("update")
    public Result update(@RequestBody SysUser sysUser) {
        if (null == sysUser || null == sysUser.getUserId()) {
            throw new BaseException(ResultCode.PARAM_ERROR);
        }
        boolean updateById = service.updateById(sysUser);
        return new Result<Boolean>().ok(updateById);
    }

    /**
     * <p>
     * 批量修改用户信息
     * </P>
     *
     * @param collection
     * @return
     */
    @PostMapping("updateBatch")
    public Result updateBatch(@RequestBody Collection<SysUser> collection) {
        boolean batch = service.updateBatchById(collection);
        return new Result<Boolean>().ok(batch);
    }

    /**
     * <p>
     * 根据条件查询单个用户信息
     * </P>
     *
     * @param queryParam
     * @return
     */
    @PostMapping("get/one")
    public Result getOne(@RequestBody QueryWrapper<SysUser> queryParam) {
        SysUser byId = service.getOne(queryParam);
        return new Result<SysUser>().ok(byId);
    }

    /**
     * <p>
     * 根据条件查询用户信息集合
     * </P>
     *
     * @param queryParam
     * @return
     */
    @PostMapping("list")
    public Result list(@RequestBody QueryWrapper<SysUser> queryParam) {
        List<SysUser> list = service.list(queryParam);
        return new Result<List<SysUser>>().ok(list);
    }

    @PostMapping("page")
    public Result page(@RequestBody MpPage<SysUser> sysUserMpPage) {
        IPage<SysUser> page = new Page();
        IPage<SysUser> userIPage = service.page(page, queryParam);
        return new Result<IPage<SysUser>>().ok(userIPage);
    }

//    @PostMapping("get/one")
//    public Result page(@RequestBody QueryWrapper<SysUser> queryParam) {
//        SysUser byId = service.page(queryParam);
//
//        return new Result<SysUser>().ok(byId);
//    }

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
        if (!notAbsent) {
            System.out.println("该请求不予处理");
        } else {
            System.out.println("处理该请求");
            service.doSomeThing(user);
        }
        return null;
    }
}
