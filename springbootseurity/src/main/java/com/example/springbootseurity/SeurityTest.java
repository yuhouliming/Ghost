package com.example.springbootseurity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * spring安全测试
 */
@Controller
@RequestMapping(value = "/seurity" )
public class SeurityTest {
    @RequestMapping(value = "/test" ,method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }
}
