package com.example.dockerdemo.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class tests {
    public static void main(String[] args) {
        String transfer = "在分析过程中，作者将IRC语篇转写为文本格式，首先分析了其话题链以及邻近对的特征，得出IRC语篇意义上是连贯的，形式上是相对混杂的。";
        String biaozhu ="在分析过程中，作者将IRC语篇转写为文本格式，首先分析了其话题链以及邻近对的特征";
        List<String> classificationName = new ArrayList<>();
        classificationName.add("分析");
        classificationName.add("作者");
        classificationName.add("连贯");
        float KeyWordAccr = calcKeyWordAccr(transfer,biaozhu,classificationName);
        System.out.println(KeyWordAccr);
    }

    public static float calcKeyWordAccr(String transfer,String biaozhu,List<String> classificationName){
        float keyWordAccr = 0;
        List<String> keyWordTransfer = new ArrayList<>();
        List<String> keyWordBiaozhu = new ArrayList<>();
        for(String name:classificationName){
            if(transfer.contains(name)){
                keyWordTransfer.add(name);
            }
        }
        for(String name:classificationName){
            if(biaozhu.contains(name)){
                keyWordBiaozhu.add(name);
            }
        }
        float x = 0;
        for(String nameTransfer:keyWordTransfer){
            for(String nameBiaozhu:keyWordBiaozhu){
                if(nameTransfer.equals(nameBiaozhu)){
                    x++;
                }
            }
        }
        if(keyWordTransfer.size()>keyWordBiaozhu.size()&&keyWordBiaozhu.size()==x){
            keyWordAccr = (x/keyWordTransfer.size())*100;
        }else{
            keyWordAccr = (x/keyWordBiaozhu.size())*100;
        }
        return new BigDecimal(keyWordAccr).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }
}
