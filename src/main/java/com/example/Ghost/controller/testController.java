package com.example.Ghost.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
@RequestMapping(value = "/test")
public class testController {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String testControler(){
        return "hello worldaaaa";//嘻嘻嘻
        //hahah
        //第三次提交
        //第四次提交
        //branch代码提交
        //branch代码再次提交
    }
}
