package com.example.dockerdemo.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class LabmdaTest1 {
    public static void main(String[] args) {
//        new Thread(()->{
//            System.out.println("hello");
//        }).start();

        Consumer<String>  stringConsumer = s-> System.out.println(s);
        stringConsumer.accept("Java3y");
        // Supplier 无入参，有返回值
        Supplier<String> supplier = () -> "Java4y";
        String s = supplier.get();
        System.out.println(s);

    }

}
