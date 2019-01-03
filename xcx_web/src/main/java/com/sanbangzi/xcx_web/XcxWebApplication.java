package com.sanbangzi.xcx_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@ComponentScan(basePackages = {"com.sanbangzi"})
@MapperScan(basePackages = "com.sanbangzi.*.dao")
@SpringBootApplication
public class XcxWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(XcxWebApplication.class, args);
    }

}

