package com.gaobo.system.config;

import com.gaobo.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 全局异常的统一处理
 */

@RestControllerAdvice
@Slf4j
public class MyControllerAdvice {

    @ExceptionHandler(InvalidGrantException.class)
    public Result badCredentialsException(Exception e){
        log.error(e.getMessage(), e);
        System.out.println("bad");
        return Result.error(e.getMessage());
    }

    /**
     * validator 前端给后端传递参数异常的统一处理
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String msgs = this.handle(e.getBindingResult().getFieldErrors());
        Result error = Result.error(400, msgs);
        log.warn("URL:{} ,参数校验异常:{}", request.getRequestURI(),msgs);
        return error;
    }

    private String handle(List<FieldError> fieldErrors) {
        StringBuilder sb = new StringBuilder();
        for (FieldError obj : fieldErrors) {
            sb.append(obj.getField());
            sb.append("=[");
            sb.append(obj.getDefaultMessage());
            sb.append("]  ");
        }
        return sb.toString();
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        log.error(e.getMessage(), e);
        System.out.println("excp");
        return Result.error(e.getMessage());
    }

}
