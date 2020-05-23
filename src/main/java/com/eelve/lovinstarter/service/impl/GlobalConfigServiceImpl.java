package com.eelve.lovinstarter.service.impl;

import com.eelve.lovinstarter.dao.GlobalConfigDao;
import com.eelve.lovinstarter.model.SystemDict;
import com.eelve.lovinstarter.service.IGlobalConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Vector;

/**
 * @ClassName GlobalConfigServiceImpl
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2020/5/9 11:30
 * @Version 1.0
 **/
@Service
public class GlobalConfigServiceImpl implements IGlobalConfigService {

    @Autowired
    GlobalConfigDao globalConfigDao;

    @Override
    public Vector<SystemDict> getConfig() {
        return globalConfigDao.getConfig();
    }

    @Override
    public int updateSystemDict(int id, String dict_value) {
        return globalConfigDao.updateSystemDict(id,dict_value);
    }
}
