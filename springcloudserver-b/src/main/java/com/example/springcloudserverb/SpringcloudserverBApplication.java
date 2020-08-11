package com.example.springcloudserverb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpringcloudserverBApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudserverBApplication.class, args);
    }

}
