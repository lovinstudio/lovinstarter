package com.eelve.lovinstarter.dao;

import com.eelve.lovinstarter.model.SystemDict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Vector;

/**
 * @ClassName GlobalConfigDao
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2020/5/9 11:22
 * @Version 1.0
 **/
@Mapper
public interface GlobalConfigDao {

    @Select("select * from system_dict")
    Vector<SystemDict> getConfig();

    @Update("update system_dict set dict_value = #{dict_value} where id = #{id}")
    int updateSystemDict(int id,String dict_value);
}
