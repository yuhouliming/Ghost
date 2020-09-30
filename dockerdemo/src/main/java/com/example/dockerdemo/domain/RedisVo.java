package com.example.dockerdemo.domain;

import lombok.Data;

@Data
public class RedisVo {

    private String key;

    private String value;

    private int time;
}
