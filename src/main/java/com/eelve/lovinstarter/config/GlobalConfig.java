package com.eelve.lovinstarter.config;



import com.eelve.lovinstarter.service.IGlobalConfigService;
import com.eelve.lovinstarter.utils.SpringUtils;

import java.util.Vector;

/**
 * @ClassName GlobalConfig
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2020/5/9 9:30
 * @Version 1.0
 **/
public class GlobalConfig {

    private static IGlobalConfigService bean = SpringUtils.getBean(IGlobalConfigService.class);


    private static GlobalConfig instance = null;
    private Vector  properties = null;
    private GlobalConfig() {
        //Load configuration information from DB or file
        //Set values for properties
        properties = bean.getConfig();
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
    public Vector getProperties() {
        return properties;
    }
    public void updateProperties() {
        //Load updated configuration information by new a GlobalConfig object
        GlobalConfig shadow = new GlobalConfig();
        properties = shadow.getProperties();
    }
}

