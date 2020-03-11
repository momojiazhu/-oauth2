package com.gaobo.system.config;

import com.alibaba.fastjson.JSONObject;
import com.gaobo.common.entity.Result;
import com.gaobo.system.model.DeptModel;
import com.gaobo.system.model.SearchLogModel;
import com.gaobo.system.service.SearchLogService;
import com.github.pagehelper.PageInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *   切面来控制搜索日志，围绕方法，有的在方法前执行，有的在方法后执行
 */

@Aspect
@Component
public class SearchLogAspect {

    @Autowired
    SearchLogService searchLogService;

    @Pointcut("execution(* com.gaobo.system.control.*.*Select(..))")
    public void executeService() {
    }

    @Around("execution(* com.gaobo.system.control.*.*Select(..))")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url=request.getRequestURL()!=null?request.getRequestURL().toString().trim():null;
        String parameterStr = JSONObject.toJSONString(proceedingJoinPoint.getArgs()[0]);
        Object obj = proceedingJoinPoint.proceed();
        Map<String,Object> resultMap = getResultMap(obj);
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SearchLogModel searchLogModel = new SearchLogModel();
        searchLogModel.setUserName(username);
        searchLogModel.setUrl(url);
        searchLogModel.setParameter(parameterStr);
        searchLogModel.setResult(JSONObject.toJSONString(resultMap));
        searchLogService.insert(searchLogModel);
        return obj;
    }

    public Map<String,Object> getResultMap(Object obj){
        Map<String,Object> map = new HashMap<>();
        if(obj instanceof Result){
            Object success=((Result) obj).getSuccess();
            Object result=((Result) obj).getResult();
            map.put("success",success);
            if(result instanceof List){
                map.put("result_num",((List)result).size());
            }else if(result instanceof PageInfo){
                map.put("result_num",((PageInfo)result).getList().size());
            }else{
                map.put("result_num",0);
            }
        }
        return map;
    }

}
