package com.gaobo.common.entity;

import com.gaobo.common.constant.CommonConstants;
import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean success;

    private String message;

    private Integer code;

    private Object result;

    public Result() {

    }

    public static Result ok() {
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(CommonConstants.SC_OK_200);
        r.setMessage("成功");
        return r;
    }

    public static Result ok(String msg) {
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(CommonConstants.SC_OK_200);
        r.setMessage(msg);
        return r;
    }

    public static Result ok(Object data) {
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(CommonConstants.SC_OK_200);
        r.setResult(data);
        return r;
    }

    public static Result error(String msg) {
        return error(CommonConstants.SC_INTERNAL_SERVER_ERROR_500, msg);
    }

    public static Result error(int code, String msg) {
        Result r = new Result();
        r.setCode(code);
        r.setMessage(msg);
        r.setSuccess(false);
        return r;
    }
}
