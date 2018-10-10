package com.yangy.manage.domain;

import java.io.Serializable;

/**
 * 用户相关的实体类
 *
 * @author yang yang
 * @email m17610912950@163.com
 * @time 2018 10 10 23:47
 **/
@Data
@Builder
public class User implements Serializable {

    private Long id;
    private String username;
    private String realName;
    private Integer gender;
    private Long birth;
    private String icon;
    private String address;
    private String area;

    private String phone;
    private String email;
    private String wxnum;
}
