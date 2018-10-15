package com.yangy.manage.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

/**
 * <p>
 * 登录VO
 * </p>
 *
 * @author yang yang
 * @create 2018/10/15
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginVO {

    //用户名
    private String username;
    //真实姓名
    private String realName;
    //账户密码
    private String password;
    //用户密码 盐
    private String salt;
    //验证码
    private String verifyCode;
    //用户手机号
    private String phone;
    //用户邮箱地址
    private String email;
    //用户生日字符串
    private String birthStr;
    //用户生日 13 位时间戳
    private Long birth;
    //用户性别 0 女 1 男
    private Integer gender;
    //用户性别字符串 女 男
    private String genderStr;
    //用户所有菜单列表
    private List<Menu> menuList;
    //微信openId
    private String openId;
    //微信unionId
    private String unionId;
}