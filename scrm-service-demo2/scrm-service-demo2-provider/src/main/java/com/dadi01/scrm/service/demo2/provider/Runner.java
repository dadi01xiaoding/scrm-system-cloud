package com.dadi01.scrm.service.demo2.provider;

import com.dadi01.scrm.foundation.model.dto.ResultDTO;
import com.dadi01.scrm.service.demo2.api.IGateway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * @Description TODO
 * @Author fang
 * @Date 2020-01-13 01:40
 **/
@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class Runner {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Runner.class, args);
        IGateway gateway = applicationContext.getBean(IGateway.class);
        ResultDTO userName = gateway.getUserName();
        System.out.println((String) userName.getData());
    }
}
