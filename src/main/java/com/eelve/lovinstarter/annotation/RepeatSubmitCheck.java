package com.eelve.lovinstarter.annotation;

import java.lang.annotation.*;

/**
 * @author zhao.zhilue
 * @Description:
 * @date 2020/5/22 9:43
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmitCheck {
    //默认2秒内不能重复提交
    long value() default 2L;
}
