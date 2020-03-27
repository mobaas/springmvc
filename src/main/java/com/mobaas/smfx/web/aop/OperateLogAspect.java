/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.smfx.web.aop;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.mobaas.smfx.Constants;
import com.mobaas.smfx.domain.AdminLog;
import com.mobaas.smfx.service.AdminService;

/**
 * 操作日志切面类
 * @author billy (billy_zh@126.com)
 *
 */
@Aspect
@Component
public class OperateLogAspect {

	/**
	 * 操作日志切入点
	 */
	@Pointcut(Constants.LOG_POINTCUT)
	private void log() {}
	
    private long startTimeMillis = 0; // 开始时间  
    private int duration = 0; // 

    @Autowired
    private AdminService adminService;
    
    @Autowired
    private HttpServletRequest request;
    
    /** 
     * 方法调用前触发
     * @param joinPoint 
     */ 
    @Before("log()")
    public void before(JoinPoint joinPoint){  
        //System.out.println("被拦截方法调用之后调用此方法，输出此语句");  
        startTimeMillis = System.currentTimeMillis(); //记录方法开始执行的时间  
    }  
    
    /**
     * 方法调用后触发
     * @param joinPoint
     */
    @After("log()")
    public void after(JoinPoint joinPoint){  
        duration = (int)(System.currentTimeMillis() - startTimeMillis);  
    }
    
    /** 
     * 方法调用后触发
     * @param joinPoint 
     */
    @AfterReturning(pointcut="log()", returning="result")
    public void afterReturning(JoinPoint joinPoint, String result) { 

        Class<?> targetClass = joinPoint.getTarget().getClass();  
        String methodName = joinPoint.getSignature().getName();  
        Object[] arguments = joinPoint.getArgs(); 

        Class<?>[] argClass = new Class<?>[arguments.length];
        for (int i=0; i<arguments.length; i++) {
        	argClass[i] = arguments[i].getClass();
        }

        Method targetMethod = null;
		try {
			targetMethod = targetClass.getMethod(methodName, argClass);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
        if (targetMethod != null) {
        	OperateLog opLog = targetMethod.getAnnotation(OperateLog.class);
        	if (opLog != null) {
        		AdminLog log = new AdminLog();
        		log.setCategory( opLog.Category().toString() );
        		log.setOpName( opLog.Name() );
        		log.setDuration(duration);
        		log.setResult(result);
        		log.setLogTime(new Date());
    			log.setLogIp(request.getRemoteAddr());
    			
    			log.setLoginName( SecurityContextHolder.getContext().getAuthentication().getName() );
    			
        		adminService.insertAdminLog(log);
        	}
        }
    }
  
    /**
     * 出现错误后触发
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(pointcut="log()", throwing="ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
    	// TODO
    }
    
    /** 
     * 环绕触发
     * @param joinPoint
     */  
    //@Around("log()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {  

        return null;  
    }  

}
