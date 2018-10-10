package com.yangy.manage.enums;

public enum ResultCodeEnum {

    SUCCESS(200, "成功"),
    UN_AUTH(401, "未授权"),
    NET_BUSY(500, "网络繁忙请稍后重试"),

    API_FAIL(500, "API请求失败"),
    SAVE_FAIL(500, "保存失败"),
    DEL_FAIL(500, "删除失败"),
    UPDATE_FAIL(500, "修改失败");
    private Integer code;
    private String msg;

    ResultCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
