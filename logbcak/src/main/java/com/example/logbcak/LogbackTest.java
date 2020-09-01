package com.example.logbcak;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/logback")
public class LogbackTest {
    private final static Logger logger = LoggerFactory
            .getLogger(LogbackTest.class);
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String getLogInfo(){
        for(int i =0;i<10;i++){
            logger.info("日志测试{}",i);
        }
        return "hello";
    }
}
