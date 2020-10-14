package com.example.dockerdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("resources.mapper")
@EnableAutoConfiguration
public class DockerdemoApplication {

    public static void main(String[] args) {
        System.setProperty("wlm", "9527");
        System.out.println("wlm");
        SpringApplication.run(DockerdemoApplication.class, args);
    }

}
