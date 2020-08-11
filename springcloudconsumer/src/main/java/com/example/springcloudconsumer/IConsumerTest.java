package com.example.springcloudconsumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Component
//服务端
@FeignClient("springcloudprovider")
public interface IConsumerTest {

    @RequestMapping(value = "/provider/test",method = RequestMethod.GET)
    Map<String,Object> getProviderInfo(@RequestParam("id") Integer id);

}
