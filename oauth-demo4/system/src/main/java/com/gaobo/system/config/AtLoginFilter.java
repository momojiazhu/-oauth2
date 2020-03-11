package com.gaobo.system.config;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * 过滤器at
 */
public class AtLoginFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("This is a filter at UsernamePasswordAuthencationFilter");
        chain.doFilter(request, response);
    }
}
