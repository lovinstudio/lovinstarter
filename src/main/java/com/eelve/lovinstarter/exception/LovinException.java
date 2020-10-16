package com.eelve.lovinstarter.exception;

/**
 * @ClassName LovinException
 * @Description TDO 自定义异常类
 * @Author zhao.zhilue
 * @Date 2020/10/16 14:35
 * @Version 1.0
 **/
public class LovinException  extends  RuntimeException{
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

    public LovinException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public LovinException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public LovinException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public LovinException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
