package com.yangy.pay.enums;

public enum SignType {

    MD5("MD5"),
    HMAC("HMAC-SHA256"),

    ;

    private String str;

    SignType(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
