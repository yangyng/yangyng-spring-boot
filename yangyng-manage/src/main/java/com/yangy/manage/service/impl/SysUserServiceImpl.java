package com.yangy.manage.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangy.manage.entity.SysUser;
import com.yangy.manage.mapper.SysUserMapper;
import com.yangy.manage.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author yangy
 * @since 2018-10-15
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Resource
    private SysUserMapper userMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    @Transactional
    public void doSomeThing(SysUser sysUser) {
        SysUser user = userMapper.selectById(sysUser.getUserId());
        if (user.getLocked() == 1 && sysUser.getLocked() == 4) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            userMapper.updateById(sysUser);
            redisTemplate.opsForValue().set("key", "value", 0);
            log.info("“SUCCESS");
        } else {
            log.info("ERROR");
        }
    }
}
