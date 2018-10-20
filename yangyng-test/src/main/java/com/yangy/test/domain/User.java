package com.yangy.test.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 测试用户
 *
 * @author yang yang
 * @create 2018/10/20
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long userId;
    private Integer gender;
    private String name;
    private String phone;
    private String desp;
}