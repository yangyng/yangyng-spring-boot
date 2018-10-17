package com.yangy.manage.service;

import com.yangy.manage.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author yangy
 * @since 2018-10-15
 */
public interface ISysUserService extends IService<SysUser> {

    void doSomeThing(SysUser sysUser);
}
