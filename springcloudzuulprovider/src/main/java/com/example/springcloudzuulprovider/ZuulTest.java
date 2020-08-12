package com.example.springcloudzuulprovider;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/zuul")
public class ZuulTest {
        @RequestMapping(value = "/test", method = RequestMethod.GET)
        public String getUser() {
            return "zuul";
        }

}
