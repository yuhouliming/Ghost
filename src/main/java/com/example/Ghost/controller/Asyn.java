package com.example.Ghost.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

@Component
@Async
public class Asyn {

    public static Random random =new Random();

    public Future<String> doTaskOne() throws Exception {
        System.out.println("one");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("one，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("one完成");
    }

    public Future<String> doTaskTwo() throws Exception {
        System.out.println("two");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("two，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("two");
    }

    @Async
    public Future<String> doTaskThree() throws Exception {
        System.out.println("three");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("three，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("three");
    }
}
