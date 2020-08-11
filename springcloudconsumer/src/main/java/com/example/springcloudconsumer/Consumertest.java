package com.example.springcloudconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping(value = "/consumer")
public class Consumertest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IConsumerTest iConsumerTest;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public Map<String,Object> getProviderData(@RequestParam Integer id){

        //通信的两种方式：一：使用restTemplate，二：使用Feign
        //Map<String,Object> data = restTemplate.getForObject("http://springcloudprovider/provider/test?id="+id,Map.class);
        Map<String,Object> data = iConsumerTest.getProviderInfo(id);
        return data;
    }

}
