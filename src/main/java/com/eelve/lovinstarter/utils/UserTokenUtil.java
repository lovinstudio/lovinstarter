package com.eelve.lovinstarter.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName UserTokenUtil
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2020/5/22 9:48
 * @Version 1.0
 **/
public class UserTokenUtil {
    /**
     * 获取请求的token
     * @param httpRequest
     * @return
     */
    public static String getRequestToken(HttpServletRequest httpRequest) {
        //从header中获取token
        String token = httpRequest.getHeader("token");

        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = httpRequest.getParameter("token");
        }

        return token;
    }
}
