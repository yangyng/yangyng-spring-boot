package com.yangy.manage.service.impl;

import com.yangy.manage.entity.UsUser;
import com.yangy.manage.mapper.UsUserMapper;
import com.yangy.manage.service.IUsUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author yangy
 * @since 2018-10-27
 */
@Service
public class UsUserServiceImpl extends ServiceImpl<UsUserMapper, UsUser> implements IUsUserService {

}
