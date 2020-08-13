package com.example.springcloudaibabanacosconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/config")
@RefreshScope
public class Configtest {

    @Value("${spring.cloud.config.test}")
    private String springCloudConfigValue;
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String getProviderData(){
        String value = springCloudConfigValue;
        return value;
    }

    //演示服务降级
    @RequestMapping(value = "/hystrixTest",method = RequestMethod.GET)
    public Map<String,Object> hystrixTest(@RequestParam Integer id){
        //通信的两种方式：一：使用restTemplate，二：使用Feign
        //Map<String,Object> data = restTemplate.getForObject("http://springcloudprovider/provider/test?id="+id,Map.class);
        Map<String,Object> data = new HashMap<>();
        data.put("id", id);
        return data;
    }


}
