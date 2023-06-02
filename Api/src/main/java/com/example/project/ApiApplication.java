package com.example.project;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.example.project.mapper")
@SpringBootApplication
@EnableDubbo
public class ApiApplication {

    public static void main(String[] args) {
        System.setProperty("user.home","myhome");
        SpringApplication.run(ApiApplication.class, args);
    }

}
