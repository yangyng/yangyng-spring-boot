package com.yangy.common.model;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangy.common.enums.ResultCode;
import com.yangy.common.exception.BaseException;
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
public class MpPage<T> extends Page<T> {
    private T anyType;

    public QueryWrapper<T> getQuery() {
        T model = getAnyType();
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(model);
        return queryWrapper;
    }

    public T getAnyType() {
        if (null == anyType) {
            throw new BaseException(ResultCode.PARAM_ERROR);
        }
        return anyType;
    }
}