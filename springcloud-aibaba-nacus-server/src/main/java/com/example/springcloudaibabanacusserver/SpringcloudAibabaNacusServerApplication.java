package com.example.springcloudaibabanacusserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringcloudAibabaNacusServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudAibabaNacusServerApplication.class, args);
    }

}
