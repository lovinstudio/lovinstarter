package com.eelve.lovinstarter.controller;

import com.eelve.lovinstarter.annotation.LovinLog;
import com.eelve.lovinstarter.config.GlobalConfig;
import com.eelve.lovinstarter.constant.RemoteHostType;
import com.eelve.lovinstarter.vo.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName GlobalConfigController
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2020/5/9 11:32
 * @Version 1.0
 **/
@Controller
@Api(value = "Lovin应用配置接口",tags = {"Lovin应用配置接口的controller"})
@Log
public class GlobalConfigController {

    @LovinLog("获取应用配置接口")
    @RequestMapping("/config")
    @ResponseBody
    @ApiOperation(value = "获取应用配置接口",notes = "获取应用配置接口",httpMethod="GET")
    public JsonResult allConfigs(HttpServletRequest request){
        GlobalConfig globalConfig =  GlobalConfig.getInstance();
        log.info(JsonResult.ok().put(globalConfig.getProperties()).toString());
        return  JsonResult.ok().put(globalConfig.getProperties());
    }


    @LovinLog("更新应用配置接口")
    @RequestMapping("/refresh")
    @ResponseBody
    @ApiOperation(value = "更新应用配置接口",notes = "更新应用配置接口",httpMethod="GET")
    public JsonResult refreshProperties(HttpServletRequest request) {
        if(!request.getRemoteHost().equals(RemoteHostType.LOCALHOST)){
            return JsonResult.error("非法请求");
        }
        GlobalConfig globalConfig =  GlobalConfig.getInstance();
        globalConfig.updateProperties();
        log.info(JsonResult.ok().put(globalConfig.getProperties()).toString());
        return  JsonResult.ok().put(globalConfig.getProperties());
    }
}
