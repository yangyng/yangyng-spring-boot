package com.yangy.common.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 三方登录返回的用户信息对象
 *
 * @author yang yang
 * @create 2018/10/30
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThirdLoginUserVO {

    private String nickname;
    private String gender;
    private String icon;
    private String openId;
    private String unionId;
    private String area;
    private String province;
    private String city;
    private String country;

}