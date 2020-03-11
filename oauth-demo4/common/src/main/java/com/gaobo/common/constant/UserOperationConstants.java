package com.gaobo.common.constant;

public enum UserOperationConstants {

    OPT_ADD("添加"),
    OPT_UPDATE("修改"),
    OPT_DELETE("删除");

    private String message;

    UserOperationConstants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
