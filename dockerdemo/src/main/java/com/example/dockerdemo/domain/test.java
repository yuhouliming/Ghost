package com.example.dockerdemo.domain;

import lombok.Data;

@Data
public class test {
    private  int num=1;
    public int getNum(){
        return num;
    }
    public int setNum(){
        return num = num+2;
    }
}
