package com.example.project;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.example.project.mapper")
@EnableDubbo
@EnableScheduling
public class UserBackedApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserBackedApplication.class, args);
    }

}
