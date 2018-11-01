package com.yangy.common.model;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页对象
 *
 * @author yang yang
 * @create 2018/11/1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MpPage<AnyType> extends Page<AnyType> {
    private AnyType anyType;

    public static QueryWrapper<AnyType > getQuery(){

    }
}