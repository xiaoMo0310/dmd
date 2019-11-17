package com.dmd.admin.aspect;

import com.dmd.admin.annotation.OperationLog;
import com.dmd.admin.model.domain.UmsOperationLog;
import com.dmd.admin.service.UmsOperationLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
public class OperationLogAspect {
    @Autowired
    private UmsOperationLogService operationLogService;

    @Pointcut("@annotation(com.dmd.admin.annotation.OperationLog)")
    public void OperationLogAspect() {
    }

    @Before("OperationLogAspect()")
    public void doBefore(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OperationLog appLog = method.getAnnotation(OperationLog.class);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        UmsOperationLog operationLog=new UmsOperationLog();
        operationLog.setIp(getIpAddress(request));
        operationLog.setUrl(request.getRequestURI());
        operationLog.setLoginUser(authentication.getName());
        operationLog.setOperation(appLog.content());
        operationLogService.addOperationLog(operationLog);
    }

    @After("OperationLogAspect()")
    public void doAfter(JoinPoint joinPoint) {

    }
    public String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}