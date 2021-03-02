package com.example.dockerdemo.java8.pojo;

import cn.hutool.json.JSONUtil;
import javafx.print.PaperSource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Slf4j
public class Apple {

    private int weight;

    private String color;

    private static boolean isGreenApple(Apple apple){
        return "green".equals(apple.getColor());
    }

    private static boolean isHeavyApple(Apple apple){
        return 150<apple.getWeight();
    }

    private static List<Apple> filterApples(List<Apple> apples, Predicate<Apple> p){
        List<Apple> appleList = new ArrayList<>();
        apples.forEach(entity->{
            if(p.test(entity)){
                appleList.add(entity);
            }
        });
        return appleList;
    }


    /**
     * 参数行为化
     */
    public interface  ApplePredicate<T>{
        boolean test(T t);
    }

//    public static class AppleColorFilter implements ApplePredicate{
//        @Override
//        public boolean test(Apple apple) {
//            return "green".equals(apple.getColor());
//        }
//    }

    private static <E> List<E> filterApple2(List<E> apples, ApplePredicate<E> p){
        List<E> appleList = new ArrayList<>();
        apples.forEach(entity->{
            if(p.test(entity)){
                appleList.add(entity);
            }
        });
        return appleList;
    }


    public static void main(String[] args) {
        List<Apple> appleList = new ArrayList<>();
        Apple apple1 = new Apple(100,"bule");
        Apple apple2 = new Apple(160,"bule");
        Apple apple3 = new Apple(160,"green");
        appleList.add(apple3);
        appleList.add(apple2);
        appleList.add(apple1);
        List<Apple> appleList1 = filterApples(appleList,Apple::isGreenApple);
        List<Apple> appleList2 = filterApples(appleList,Apple::isHeavyApple);

//        log.info(JSONUtil.toJsonStr(appleList1));
//        log.info(JSONUtil.toJsonStr(appleList2));
        List<Apple> appleList3 = appleList.stream().filter(apple ->
                apple.getWeight()>120 &&apple.getColor().equals("bule")).collect(Collectors.toList());
        //log.info(JSONUtil.toJsonStr(appleList3));
        //List<Apple> appleList4 = filterApple2(appleList,new AppleColorFilter());
        //log.info(JSONUtil.toJsonStr(appleList4));
        /**
         * 使用匿名内部类
         */
//        List<Apple> appleList5 =  filterApple2(appleList, new ApplePredicate<Apple>() {
//            @Override
//            public boolean test(Apple apple) {
//                return "green".equals(apple.getColor());
//            }
//        });
        //log.info(JSONUtil.toJsonStr(appleList5));

        List<Apple> appleList6 = filterApple2(appleList,apple -> "green".equals(apple.getColor()));
        //Apple apple = Apple.builder().color("green").weight(12).build();
        log.info(JSONUtil.toJsonStr(appleList6));
    }
}
