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
    }
}
