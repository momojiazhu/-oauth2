package com.gaobo.oss.control;

import com.gaobo.common.constant.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/oss")
    public String test(){
        System.out.println("123456");
        return "hello.oss"+ Test.GAOBO.getCode();
    }

}
