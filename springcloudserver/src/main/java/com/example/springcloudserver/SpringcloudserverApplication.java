package com.example.springcloudserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpringcloudserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudserverApplication.class, args);
    }

}
