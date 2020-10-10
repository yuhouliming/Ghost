package com.example.dockerdemo.controller;

import com.example.dockerdemo.common.ResponseVo;
import com.example.dockerdemo.domain.User;
import com.example.dockerdemo.service.UserRepository;
import com.example.dockerdemo.service.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/mongoDb")
@RestController
public class MongoDbController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/save")
    public ResponseVo saveUser(@RequestBody User user){
        ResponseVo responseVo = new ResponseVo();
        userRepository.saveUser(user);
        return  responseVo;
    }

    @PostMapping("/query")
    public ResponseVo queryUser(@RequestBody User user){
        ResponseVo responseVo = new ResponseVo();
        User user1 = userRepository.findUserByUserName(user.getUserName());
        responseVo.setData(user1);
        return  responseVo;
    }

    @PostMapping("/update")
    public ResponseVo updateUser(@RequestBody User user){
        ResponseVo responseVo = new ResponseVo();
        userRepository.updateUser(user);
        return  responseVo;
    }

    @PostMapping("/delete")
    public ResponseVo deleteUser(@RequestBody User user){
        ResponseVo responseVo = new ResponseVo();
        userRepository.deleteUserById(user.getId());
        return  responseVo;
    }
}
