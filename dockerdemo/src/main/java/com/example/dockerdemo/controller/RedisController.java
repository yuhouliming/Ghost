package com.example.dockerdemo.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.example.dockerdemo.common.ResponseVo;
import com.example.dockerdemo.domain.RedisVo;
import com.example.dockerdemo.utils.RedisUtil;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/redis")
public class RedisController {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;


    @Autowired
    private RedissonClient redissonClient;

    /**
     * 设置rediskey
     * @param redisVo
     * @return
     */
    @RequestMapping(value = "/setValue",method = RequestMethod.POST)
    public String setRedisValue(@RequestBody RedisVo redisVo){
//        RMap<String, String> certMap = redissonClient.getMap(StrUtil.format("{}{}", "wlm", "nihao"));
//        certMap.put(redisVo.getKey(),redisVo.getValue());

        RedisUtil.set(redisTemplate, redisVo.getKey(), redisVo.getValue(),redisVo.getTime());
        return "success";
    }

    /**
     * 获取rediskey
     * @param redisVo
     * @return
     */
    @RequestMapping(value = "/getValue",method = RequestMethod.POST)
    public ResponseVo getRedisValue(@RequestBody RedisVo redisVo){
        ResponseVo responseVo = new ResponseVo();
        String value = (String)RedisUtil.get(redisTemplate, redisVo.getKey());
        RMap<String, String> certMap = redissonClient.getMap(StrUtil.format("{}{}", "wlm", "nihao"));
        responseVo.setData(value+certMap.get("9527"));
        return responseVo;
    }

    /**
     * 删除rediskey
     * @param redisVo
     * @return
     */
    @RequestMapping(value = "/delValue",method = RequestMethod.POST)
    public ResponseVo delRedisValue(@RequestBody RedisVo redisVo){
        ResponseVo responseVo = new ResponseVo();
        RedisUtil.del(redisTemplate, redisVo.getKey());
        return responseVo;
    }

}
