package com.eelve.lovinstarter.model;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName AppLog
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2020/4/21 15:04
 * @Version 1.0
 **/
@Data
public class LovinLog {

    /**ID**/
    private Long id;

    /**日志类型**/
    private Integer type;

    /**用户名**/
    private String username;

    /**用户操作**/
    private String operation;

    /**请求方法**/
    private String method;

    /**请求参数**/
    private String params;

    /**执行时长(毫秒)**/
    private Long time;

    /**IP地址**/
    private String ip;

    /**请求URI**/
    private String requestUri;

    /**创建时间**/
    private Date createTime;

    /**用户ID**/
    private String userId;

    /**用户ID**/
    private String result;
}
