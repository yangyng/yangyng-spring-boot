package com.yangy.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 三方登录对象
 *
 * @author yang yang
 * @create 2018/10/31
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThirdLoginModel {

    private String openId;
    private String unionId;
    private String sessionKey;

    private String nickname;
    private String gender;
    private String icon;
}