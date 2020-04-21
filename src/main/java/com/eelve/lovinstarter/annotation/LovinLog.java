package com.eelve.lovinstarter.annotation;

import com.eelve.lovinstarter.constant.LovinLogType;

import java.lang.annotation.*;

/**
 * @author zhao.zhilue
 * @Description: 日志记录
 * @date 2020/4/2119:47
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LovinLog {
    String value() default "";

    /**
     * 日志类型
     * <p>
     * 参考 {@link LovinLogType}
     */
    int type() default LovinLogType.API;

    /**
     * 需要排除的字段名称、该字段不会持久化到数据库参数中
     *
     * @return
     */
    String[] excludeFields() default {};
}
