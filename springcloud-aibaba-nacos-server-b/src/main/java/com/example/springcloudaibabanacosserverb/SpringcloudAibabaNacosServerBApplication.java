package com.example.springcloudaibabanacosserverb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringcloudAibabaNacosServerBApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudAibabaNacosServerBApplication.class, args);
    }

}
