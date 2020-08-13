package com.example.springcloudaibabanacusserver;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/provider")
public class PrividerTest {
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public Map<String,Object> getUser(@RequestParam Integer id){
        Map<String,Object> data = new HashMap<>();
        data.put("id",id);
        data.put("userName","admin-A");
        data.put("from","provider-A");
        return data;
    }
}
