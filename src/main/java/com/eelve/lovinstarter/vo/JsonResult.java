package com.eelve.lovinstarter.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.http.HttpStatus;

import java.io.Serializable;

/**
 * 定义Json响应数据
 *
 * @param <T>
 */
@ApiModel
@Data
public class JsonResult<T> implements Serializable {
    @ApiModelProperty(notes = "响应码 0: 成功，其他: 异常")
    private Integer code;
    @ApiModelProperty(notes = "异常描述信息")
    private String msg;
    @ApiModelProperty(notes = "返回的数据")
    private T data;

    /**
     * 成功
     *
     * @return
     */
    public static JsonResult ok() {
        JsonResult result = new JsonResult();
        result.setCode(0);
        return result;
    }

    /**
     * 失败
     *
     * @param msg
     * @return
     */
    public static JsonResult error(String msg) {
        JsonResult result = new JsonResult();
        result.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        result.setMsg(msg);
        return result;
    }

    /**
     * 失败
     *
     * @param code
     * @param msg
     * @return
     */
    public static JsonResult error(Integer code, String msg) {
        JsonResult result = new JsonResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 失败
     *
     * @return
     */
    public static JsonResult error() {
        JsonResult result = new JsonResult();
        result.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        result.setMsg("未知异常，请联系管理员");
        return result;
    }

    /**
     * 添加返回的数据
     *
     * @param data
     * @return
     */
    public JsonResult<T> put(T data) {
        this.data = data;
        return this;
    }

    /**
     * 是否正常
     *
     * @return
     */
    @JsonIgnore
    public boolean isOk() {
        return this.code.intValue() == 0;
    }
    @JsonIgnore
    public boolean isError() {
        return this.code.intValue() != 0;
    }
}
