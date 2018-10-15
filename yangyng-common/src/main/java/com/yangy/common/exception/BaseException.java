package com.yangy.common.exception;

import com.yangy.common.enums.ResultCode;

import java.io.Serializable;

/**
 * 自定义异常
 *
 * @author yang yang
 * @email java_yangy@126.com
 * @create 2018/8/1
 * @since 1.0.0
 */
public class BaseException extends RuntimeException implements Serializable {

    private int code;
    private String msg;

    public BaseException() {
        super();
    }

    public BaseException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BaseException(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public BaseException(ResultCode enums) {
        super();
        this.code = enums.getCode();
        this.msg = enums.getMsg();
    }

    public Integer getStatus() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}