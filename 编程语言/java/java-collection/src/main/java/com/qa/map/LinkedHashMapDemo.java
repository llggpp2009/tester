package com.qa.map;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapDemo {

    public static void main(String[] args) {
        //有序的
        //1.插入顺序
        Map<String, Integer> seqMap = new LinkedHashMap<>();
        seqMap.put("c",100);
        seqMap.put("d",200);
        seqMap.put("a",500);
        seqMap.put("d",300);
        for(Map.Entry<String,Integer> entry:seqMap.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
        System.out.println("----------------插入顺序");

        //2.访问顺序
        Map<String, Integer> accessMap = new LinkedHashMap<>(16,0.75f,true);
        accessMap.put("c",100);
        accessMap.put("d",200);
        accessMap.put("a",500);
        accessMap.get("c");
        accessMap.put("d",300);
        for(Map.Entry<String,Integer> entry:accessMap.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
        System.out.println("----------------访问顺序");
    }
}
