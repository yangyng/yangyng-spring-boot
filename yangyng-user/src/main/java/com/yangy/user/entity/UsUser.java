package com.yangy.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author yangy
 * @since 2018-10-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UsUser对象", description="系统用户表")
public class UsUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户主键")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "性别(0 女 1男)")
    private Integer gender;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "用户密码")
    private String pwd;

    @ApiModelProperty(value = "盐")
    private String salt;

    @ApiModelProperty(value = "微信open_id")
    private String wechat;

    @ApiModelProperty(value = "新浪第三方id")
    private String sina;

    @ApiModelProperty(value = "qq第三方id")
    private String qq;

    @ApiModelProperty(value = "用户手机号")
    private String phone;

    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "创建时间")
    private Long ctime;

    @ApiModelProperty(value = "启用状态(0 启用 1 锁定)")
    private Integer locked;


}
