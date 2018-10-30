package com.yangy.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangy.user.entity.UsUser;
import com.yangy.user.mapper.UsUserMapper;
import com.yangy.user.service.IUsUserService;
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
