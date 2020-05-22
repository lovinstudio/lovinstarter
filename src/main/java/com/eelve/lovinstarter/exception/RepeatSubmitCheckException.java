package com.eelve.lovinstarter.exception;

/**
 * @ClassName RepeatSubmitCheckException
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2020/5/22 9:46
 * @Version 1.0
 **/
public class RepeatSubmitCheckException extends RuntimeException {

    private Integer code;

    public RepeatSubmitCheckException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
