package com.example.dockerdemo.controller;

import com.example.dockerdemo.domain.Result;
import com.example.dockerdemo.domain.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RequestMapping(value = "/user")
@RestController
public class LoginController {
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result login( @RequestParam String username,@RequestParam String password,HttpServletResponse response) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);
//        Cookie cookie = new Cookie("Authorization", token.toString());
//        cookie.setPath("/");
//        response.addCookie(cookie);
        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping("/getUserInfo")
    @RequiresPermissions("user:getUserInfo")
//    @RequiresRoles("admin")
    public Result getUserInfo() {
        System.out.println("hello");
        return new Result(ResultCode.SUCCESS);
    }
}
