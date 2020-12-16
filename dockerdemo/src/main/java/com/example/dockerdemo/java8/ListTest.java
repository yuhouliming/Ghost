package com.example.dockerdemo.java8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("wu");
        strings.add("lei");
        strings.add("ming");
        for(String s:strings){
            System.out.println(s);
//            if("lei".equals(s)){
//                strings.remove(s);
//            }
        }
//        Iterator<String> iterable = strings.iterator();
//        while (iterable.hasNext()){
//            String s= iterable.next();
//            if("wu".equals(s)){
//                iterable.remove();
//            }
//        }
    }

}
