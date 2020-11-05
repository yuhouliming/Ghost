package com.example.dockerdemo.controller;

import cn.hutool.json.JSONUtil;
import com.example.dockerdemo.domain.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping("/login")
    public String login(HttpSession httpSession){
        String sessionId = httpSession.getId();
        httpSession.setAttribute("User", "wlm");
        return JSONUtil.toJsonStr(sessionId);

    }
    @GetMapping("/index")
    public String index(HttpSession httpSession){
        return JSONUtil.toJsonStr(httpSession.getAttribute("User"));

    }
}
