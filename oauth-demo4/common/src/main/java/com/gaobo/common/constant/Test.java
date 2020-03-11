package com.gaobo.common.constant;

public enum Test {

    GAOBO(1000);

    public Integer code;

    Test(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
