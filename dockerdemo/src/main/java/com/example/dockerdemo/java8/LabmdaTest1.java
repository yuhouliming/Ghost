package com.example.dockerdemo.java8;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Slf4j
public class LabmdaTest1 {
    public static void main(String[] args) {
//        new Thread(()->{
//            System.out.println("hello");
//        }).start();

        Consumer<String> stringConsumer = s -> System.out.println(s);
        stringConsumer.accept("Java3y");
        // Supplier 无入参，有返回值
        Supplier<String> supplier = () -> "Java4y";
        String s = supplier.get();
        System.out.println(s);

        File[] hiddleFiles = new File("/").listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isHidden();
            }
        });

        File[] hiddleFile = new File("/").listFiles(File::isHidden);//参数方法化

        File[] hiddleFil = new File("/").listFiles((File file)->file.isHidden());//参数方法化


        Callable<Integer> callable = ()->99; //类型推断，会调用callable的方法

    }
}
