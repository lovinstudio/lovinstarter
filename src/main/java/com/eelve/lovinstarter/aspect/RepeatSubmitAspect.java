package com.eelve.lovinstarter.aspect;

import com.eelve.lovinstarter.annotation.RepeatSubmitCheck;
import com.eelve.lovinstarter.exception.RepeatSubmitCheckException;
import com.eelve.lovinstarter.utils.UserTokenUtil;
import com.eelve.lovinstarter.utils.RedisUtil;
import com.eelve.lovinstarter.utils.Md5Utils;
import com.github.pagehelper.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName RepeatSubmitAspect
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2020/5/22 9:47
 * @Version 1.0
 **/
@Aspect
@Component
@Slf4j
public class RepeatSubmitAspect {

    @Autowired
    RedisUtil redisUtil;

    private static Md5Utils md5Utils = new Md5Utils();

    private static final long time = 5;//5秒内不得重复调用

    @Pointcut("@annotation(repeatSubmitCheck)")
    public void requestPointcut(RepeatSubmitCheck repeatSubmitCheck) {
    }

    @Before("requestPointcut(repeatSubmitCheck)")
    public void beforeCheck(JoinPoint joinPoint, RepeatSubmitCheck repeatSubmitCheck) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        HttpServletRequest request = httpServletRequest();

        if(request!=null){
            String token = UserTokenUtil.getRequestToken(request);
            log.info("RepeatSubmitAspect---token={};className={};methodName={}",token,className,methodName);
            if(!StringUtil.isEmpty(token)){
                String sign = md5Utils.encrypt(token+className+methodName);
                long x = redisUtil.incr(sign, 1, time);
                if(x > 1){
                    throw new RepeatSubmitCheckException(100, "您的请求已经成功提交了，请不要重复提交！");
                }
            }else{
                throw new RepeatSubmitCheckException(300, "token异常");
            }


        }else{
            throw new RepeatSubmitCheckException(200, "request can not be null");
        }
    }

    /**
     * 获得request对象
     *
     * @return
     */
    private HttpServletRequest httpServletRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest();
    }
}
