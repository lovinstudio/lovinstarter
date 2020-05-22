package com.eelve.lovinstarter.config;



import com.eelve.lovinstarter.constant.ConfigConstants;
import com.eelve.lovinstarter.model.SystemDict;
import com.eelve.lovinstarter.service.IGlobalConfigService;
import com.eelve.lovinstarter.utils.SpringUtils;

import java.lang.reflect.Field;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName GlobalConfig
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2020/5/9 9:30
 * @Version 1.0
 **/
public class GlobalConfig {

    private static final IGlobalConfigService bean = SpringUtils.getBean(IGlobalConfigService.class);


    private static GlobalConfig instance = null;
    private ConcurrentHashMap<String ,Object> properties = null;
    private GlobalConfig() {
        //Load configuration information from DB or file
        //Set values for properties
        properties = convert();
    }
    private static synchronized void syncInit() {
        if (instance == null) {
            instance = new GlobalConfig();
        }
    }
    public static GlobalConfig getInstance() {
        if (instance == null) {
            syncInit();
        }
        return instance;
    }
    public ConcurrentHashMap<String ,Object> getProperties() {
        return properties;
    }

    /**
     * 刷新配置
     */
    public void updateProperties() {
        //Load updated configuration information by new a GlobalConfig object
        GlobalConfig shadow = new GlobalConfig();
        properties = shadow.getProperties();
    }

    /**
     * 参数转换方法
     */
    private ConcurrentHashMap<String ,Object> convert() {
        ConcurrentHashMap<String ,Object> convert = new ConcurrentHashMap<>();
        Vector<SystemDict> systemDictVector = bean.getConfig();
        Class clazz = null;
        try {
            clazz = Class.forName("com.eelve.lovinstarter.constant.ConfigConstants");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        assert clazz != null;
        Field[] fields = clazz.getDeclaredFields();
        for (SystemDict systemDict : systemDictVector) {
            for (Field field : fields) {
                if (systemDict.getDict_key().toUpperCase().equals(field.getName())) {
                    convert.put(field.getName(), systemDict.getDict_value());
                }
            }
        }
        return convert;
    }
}

