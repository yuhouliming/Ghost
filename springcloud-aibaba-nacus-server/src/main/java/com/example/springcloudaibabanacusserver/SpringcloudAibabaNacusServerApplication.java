package com.example.springcloudaibabanacusserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringcloudAibabaNacusServerApplication {

    private final static Logger logger = LoggerFactory
            .getLogger(SpringcloudAibabaNacusServerApplication.class);
    public static void main(String[] args) {
        logger.info("阿里巴巴注册中心要启动啦");
        SpringApplication.run(SpringcloudAibabaNacusServerApplication.class, args);
    }

}
