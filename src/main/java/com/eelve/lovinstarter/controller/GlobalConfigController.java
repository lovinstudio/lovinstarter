package com.eelve.lovinstarter.controller;

import com.eelve.lovinstarter.annotation.LovinLog;
import com.eelve.lovinstarter.config.GlobalConfig;
import com.eelve.lovinstarter.constant.RemoteHostType;
import com.eelve.lovinstarter.model.SystemDict;
import com.eelve.lovinstarter.service.IGlobalConfigService;
import com.eelve.lovinstarter.vo.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName GlobalConfigController
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2020/5/9 11:32
 * @Version 1.0
 **/
@RestController("/config")
@Api(value = "Lovin应用配置接口",tags = {"Lovin应用配置接口的controller"})
@Log
public class GlobalConfigController {

    @Autowired
    IGlobalConfigService iGlobalConfigService;

    @RequestMapping("/")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = "6") Integer size) {
        List<SystemDict> dicts=iGlobalConfigService.getConfig();
        model.addAttribute("dicts", dicts);
        return "config";
    }

    @LovinLog("获取应用配置接口")
    @GetMapping("/config")
    @ResponseBody
    @ApiOperation(value = "获取应用配置接口",notes = "获取应用配置接口",httpMethod="GET")
    public JsonResult allConfigs(HttpServletRequest request){
        GlobalConfig globalConfig =  GlobalConfig.getInstance();
        log.info(JsonResult.ok().put(globalConfig.getProperties()).toString());
        return  JsonResult.ok().put(globalConfig.getProperties());
    }


    @LovinLog("更新应用配置接口")
    @PostMapping("/refresh")
    @ResponseBody
    @ApiOperation(value = "更新应用配置接口",notes = "更新应用配置接口",httpMethod="POST")
    public JsonResult refreshProperties(HttpServletRequest request,@RequestParam(name = "dictid") String id,@RequestParam(name = "updateValue") String updateValue) {
        if(!request.getRemoteHost().equals(RemoteHostType.LOCALHOST) && !request.getRemoteHost().equals(RemoteHostType.LOCALADDR)){
            log.info(JsonResult.error("非法请求").toString());
            return JsonResult.error("非法请求");
        }
        int total = iGlobalConfigService.updateSystemDict(Integer.parseInt(id), updateValue);
        if(total > 0){
            GlobalConfig globalConfig =  GlobalConfig.getInstance();
            globalConfig.updateProperties();
            log.info(JsonResult.ok().put(globalConfig.getProperties()).toString());
            return  JsonResult.ok().put(globalConfig.getProperties());
        }else{
            log.info(JsonResult.error("请求失败，请稍后重试").toString());
            return JsonResult.error("请求失败，请稍后重试");
        }

    }
}
