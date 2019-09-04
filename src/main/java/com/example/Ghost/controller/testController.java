package com.example.Ghost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@RestController
public class testController {

    @Autowired
    private Asyn task;
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String testControler() throws Exception {
        Future<String> task1 = task.doTaskOne();
        Future<String> task2 = task.doTaskTwo();
        Future<String> task3 = task.doTaskThree();
        String s= task3.get();
        return s;//嘻嘻嘻
    }
}
