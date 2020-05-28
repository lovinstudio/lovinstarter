package com.eelve.lovinstarter.utils;

import com.eelve.lovinstarter.component.SnowflakeComponent;

/**
 * @ClassName SnowflakeUtil
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2020/4/3 14:09
 * @Version 1.0
 **/

public class SnowflakeUtil {
    private static SnowflakeComponent bean = SpringUtils.getBean(SnowflakeComponent.class);

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    public static Long nextId(){
        return bean.getInstance().nextId();
    }

}
