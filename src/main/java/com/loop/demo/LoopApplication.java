package com.loop.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.loop.demo.mapper")
@SpringBootApplication
public class LoopApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoopApplication.class, args);
    }

}
