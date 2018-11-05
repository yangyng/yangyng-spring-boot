package com.yangy.manage.bean;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yangy.common.exception.BaseException;
import com.yangy.common.utils.MD5Util;
import com.yangy.manage.entity.SysUser;
import com.yangy.manage.pojo.LoginVO;
import com.yangy.manage.service.ISysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * @author yang yang
 * @create 2018/11/5
 */
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private ISysUserService sysUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        LoginVO loginVO = (LoginVO) principals.getPrimaryPrincipal();
        info.addRoles(null);
        info.addStringPermissions(null);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String queryStr = (String) token.getPrincipal();
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        SysUser select = SysUser.builder()
                .username(queryStr)
                .build();
        queryWrapper.setEntity(select);
        SysUser user = sysUserService.getOne(queryWrapper);

        if (null == user) {
            //用户信息不存在
            return null;
        }

        if (0 != user.getLocked()) {
            //用户冻结状态
            throw new BaseException(401, "当前账户暂不可用");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                user,
                MD5Util.md5(user.getPwd()),
                ByteSource.Util.bytes(user.getUsername()),
                getName()
        );
        return info;
    }
}