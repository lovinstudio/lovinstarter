package com.eelve.lovinstarter;

import com.eelve.lovinstarter.utils.SnowflakeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LovinstarterApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(SnowflakeUtil.nextId());
    }

}
