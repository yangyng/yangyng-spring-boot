package com.yangy.common.enums;

public enum ResultCode {

    /**
     * 成功码
     */
    SUCCESS(200, "SUCCESS"),

    /**
     * 授权失败
     */
    AUTH_FAIL(401, "AUTH FAIL"),

    /**
     * 网络异常
     */
    NET_BUSY(500, "NET BUSY"),

    /**
     * 参数异常
     */
    PARAM_ERROR(500, "PARAM UN CORRECT"),

    /**
     * 保存失败
     */
    SAVE_FAIL(500, "SAVE FAIL"),

    /**
     * 修改失败
     */
    UPDATE_FAIL(500, "UPDATE FAIL"),

    /**
     * 删除失败
     */
    DEL_FAIL(500, "DELETE FAIL"),

    /**
     * 错误的SQL
     */
    WRONG_SQL(500, "WRONG_SQL")





    ;

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
