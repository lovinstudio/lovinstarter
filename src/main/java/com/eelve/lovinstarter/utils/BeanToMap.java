package com.eelve.lovinstarter.utils;

import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName BeanToMap
 * @Description TDO 实体类转Map对象
 * @Author zhao.zhilue
 * @Date 2020/4/22 19:40
 * @Version 1.0
 **/
@Slf4j
public class BeanToMap {
    private BeanToMap() {}

    /**
     *将实体对象 对象转成Map
     */
    public static Map transBeanToMap(Object obj) {

        Map map = new HashMap<>();
        if (obj == null) {
            return map;
        }
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    map.put(key, value);
                }
            }
        } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
            log.error("transBeanToMap Error ", e);
        }
        return map;
    }
}
