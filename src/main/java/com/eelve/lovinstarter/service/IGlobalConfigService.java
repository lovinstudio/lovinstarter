package com.eelve.lovinstarter.service;

import com.eelve.lovinstarter.model.SystemDict;


import java.util.Vector;

/**
 * @author zhao.zhilue
 * @Description:
 * @date 2020/5/911:29
 */
public interface IGlobalConfigService {

    Vector<SystemDict> getConfig();
}
