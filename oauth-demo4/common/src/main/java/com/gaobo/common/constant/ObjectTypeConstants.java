package com.gaobo.common.constant;

public enum ObjectTypeConstants {

    OPT_DEPARTMENT("科室");

    private String message;

    ObjectTypeConstants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
