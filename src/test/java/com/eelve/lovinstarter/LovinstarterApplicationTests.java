package com.eelve.lovinstarter;

import com.eelve.lovinstarter.utils.SnowflakeUtil;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class LovinstarterApplicationTests {


    @Test
    public void contextLoads() {
        log.info("----------------------");
        log.info(SnowflakeUtil.nextId()+"");
    }

}
