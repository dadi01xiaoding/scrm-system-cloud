package com.dadi01.scrm.service.demo1.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
        SpringApplication.run(Runner.class, args);
    }
}
