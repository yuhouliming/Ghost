package com.example.dockerdemo.java8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest1 {
    public static void main(String[] args) {

        //测试一
        int[] i = {1,2,3};
        Arrays.stream(i).sum();
        OptionalInt max  = IntStream.of(i).max();
        int sum  = IntStream.of(i).sum();
        System.out.println(max.getAsInt());
        System.out.println(sum);


        List<String> title = Arrays.asList("Wmyskxz", "Is", "Learning", "Java8", "In", "Action");
        List<String> title2 = new ArrayList<>();
        title2 =  title.stream().filter(word -> word.length()>3).collect(Collectors.toList());
//        title.forEach(a ->{
//            System.out.println(a);
//        });
        //s.forEach(System.out::println);
        title2.forEach(s -> {
            System.out.println(s);
        });
        title2 =  title.stream().filter(word -> word.length()>3).collect(Collectors.toList());
    }
}
