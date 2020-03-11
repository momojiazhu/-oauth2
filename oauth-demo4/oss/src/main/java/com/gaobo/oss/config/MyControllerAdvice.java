package com.gaobo.oss.config;

import com.gaobo.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class MyControllerAdvice {

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        log.error(e.getMessage(), e);
        System.out.println("excp");
        return Result.error(e.getMessage());
    }

}
