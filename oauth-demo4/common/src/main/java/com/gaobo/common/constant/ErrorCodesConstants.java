package com.gaobo.common.constant;

public enum ErrorCodesConstants {

    E10001("修改操作，id不能为空"),
    E10002("删除的id不存在");

    private String message;

    ErrorCodesConstants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
