package com.eelve.lovinstarter.aspect;

import com.eelve.lovinstarter.exception.LovinException;
import com.eelve.lovinstarter.vo.JsonResult;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName LovinExceptionHandler
 * @Description TDO 全局异常处理
 * @Author zhao.zhilue
 * @Date 2020/10/16 14:37
 * @Version 1.0
 **/

@RestControllerAdvice
@Log
public class LovinExceptionHandler {
    /**
     * 处理自定义异常
     */
    @ExceptionHandler(LovinException.class)
    public JsonResult<Void> handleRRException(LovinException e) {
        JsonResult<Void> r = new JsonResult<Void>();
        r.setCode(e.getCode());
        r.setMsg(e.getMessage());
        return r;
    }

    @ExceptionHandler(RuntimeException.class)
    public JsonResult<Void> handleRuntimeException(Exception e) {
        log.info(e.getMessage());
        return JsonResult.error();
    }

    @ExceptionHandler(Exception.class)
    public JsonResult<Void> handleException(Exception e) {
        log.info(e.getMessage());
        return JsonResult.error();
    }
}
