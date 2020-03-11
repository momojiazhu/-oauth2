package com.gaobo.oss.utils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 这个是封装了在service里拿到request的操作
 */
@Component
public class ServletUtils implements ServletContextAware {
	private static ServletContext servletContext;

	public ServletUtils() {
	}

	@Override
	public void setServletContext(ServletContext ctx) {
		servletContext = ctx;
	}

	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	}

}
