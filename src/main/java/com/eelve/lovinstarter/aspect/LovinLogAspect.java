package com.eelve.lovinstarter.aspect;

import com.eelve.lovinstarter.model.LovinLog;
import com.eelve.lovinstarter.utils.HttpContextUtils;
import com.eelve.lovinstarter.utils.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LovinLogAspect
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2020/4/21 19:55
 * @Version 1.0
 **/
@Aspect
@Component
@Slf4j
public class LovinLogAspect {
    @Pointcut("@annotation(com.eelve.lovinstarter.annotation.LovinLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        Long time = System.currentTimeMillis() - beginTime;
        //保存日志
        saveSysLog(point,result, time);
        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint,Object result, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LovinLog logEntity = new LovinLog();
        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();


        String queryString = request.getQueryString();
        //用户信息后面将从token中获取
        logEntity.setUsername("seven");
        logEntity.setUserId("7");
        com.eelve.lovinstarter.annotation.LovinLog logAnnotation = method.getAnnotation(com.eelve.lovinstarter.annotation.LovinLog.class);
        //注解上的描述
        logEntity.setOperation(logAnnotation.value());
        logEntity.setType(logAnnotation.type());

        //请求的方法名
        logEntity.setMethod(signature.getName());

        //请求的参数
        Object[] args = joinPoint.getArgs();
        Map<String, Object> paramMap = new HashMap<>();
        ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();
        String[] parameterNames = pnd.getParameterNames(method);
        //获取请求参数集合并进行遍历拼接
        if (args.length > 0) {
            if ("POST".equals(request.getMethod())) {
                for (int i = 0; i < args.length; i++) {
                    if (args[i] instanceof BindingResult
                            || args[i] instanceof HttpServletRequest
                            || args[i] instanceof HttpServletResponse){
                        continue;
                    }
                    paramMap.put(parameterNames[i], args[i]);
                    logEntity.setParams(paramMap.toString());
                }

            } else if ("GET".equals(request.getMethod())) {
                logEntity.setParams(queryString);
            }


            //设置IP地址
            logEntity.setIp(IPUtils.getIpAddr(request));

            //获取请求的地址
            String requestURI = request.getRequestURI();


            logEntity.setRequestUri(requestURI);
            logEntity.setTime(time);
            logEntity.setCreateTime(new Date());
            logEntity.setResult(result.toString());
            //这里还可以将结果入库
            log.info(logEntity.toString());
        }
    }
}
