package com.eelve.lovinstarter.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhao.zhilue
 * @Description:
 * @date 2019/7/2215:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilTest {

    @Autowired
    RedisUtil redisUtil;

    @Test
    public void getExpire() {
        System.out.println(redisUtil.getExpire("version"));
    }
}
