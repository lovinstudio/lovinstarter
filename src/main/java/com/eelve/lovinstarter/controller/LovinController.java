package com.eelve.lovinstarter.controller;

import com.eelve.lovinstarter.vo.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName RemindController
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2020/04/08 21:36
 * @Version 1.0
 **/
@Controller
@RequestMapping("/lovin")
@Api(value = "Lovin基础的接口",tags = {"Lovin基础的接口的controller"})
@Log
public class LovinController {

    @RequestMapping("/all")
    @ResponseBody
    @ApiOperation(value = "测试接口",notes = "测试接口",httpMethod="GET")
    public JsonResult allInfos(HttpServletRequest request){
        log.info(JsonResult.ok().toString());
        return JsonResult.ok();
    }
}
