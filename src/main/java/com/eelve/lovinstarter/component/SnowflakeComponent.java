package com.eelve.lovinstarter.component;

import com.eelve.lovinstarter.model.SnowflakeIdWorker;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @ClassName SnowflakeComponent
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2020/4/3 14:17
 * @Version 1.0
 **/

@Component
@Log
public class SnowflakeComponent {


    @Value("${server.datacenterId}")
    private long datacenterId;

    @Value("${server.workId}")
    private long workId;

    private volatile SnowflakeIdWorker instance;

    @Bean
    public SnowflakeIdWorker getInstance() {
        if (instance == null){
            log.info("when instance, workId = {"+workId+"}, datacenterId = {"+datacenterId+"}");
            instance =  new SnowflakeIdWorker(workId, datacenterId);
        }
        return instance;
    }
}
