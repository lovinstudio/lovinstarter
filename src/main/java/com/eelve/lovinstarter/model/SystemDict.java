package com.eelve.lovinstarter.model;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName SystemDict
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2020/5/9 11:27
 * @Version 1.0
 **/
@Data
public class SystemDict {
    private Integer id;
    private String dict_name;
    private String dict_key;
    private String dict_value;
    private Integer dict_type;
    private String dict_desc;
    private Integer status;
    private Integer delete_flag;
    private Integer operator;
    private Date create_time;
    private Date update_time;
    private Date delete_time;
}
