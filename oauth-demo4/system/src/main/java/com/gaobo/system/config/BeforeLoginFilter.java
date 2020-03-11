package com.gaobo.system.config;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * 过滤器before
 */
public class BeforeLoginFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("This is a filter before UsernamePasswordAuthencationFilter");
        chain.doFilter(request, response);
    }
}
