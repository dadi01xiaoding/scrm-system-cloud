package com.dadi01.scrm.service.demo1.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author fang
 * @Date 2020-01-13 01:40
 **/
@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
@RestController
public class Runner {
    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
    }
    @Value("${server.port}")
    private String name;
    @RequestMapping("/get")
    public String get() {
        return name;
    }
}
