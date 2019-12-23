package com.qa.collection.List;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a","b","c","d");

        // 修改元素
        list.set(4,"e");

        // 删除元素(index或object)
        list.remove(1);
        list.remove("b");

        // 查询
        String item = list.get(1);
        int n = list.indexOf("f");
        System.out.print("get：" + item + "  indexOf：" + n);

        // 遍历(原始方法和新特性)
        dataPrintByIt(list);
        dataPrintByForEach(list);
        dataPrintByFE(list);

        // 清空
        // lists.clear();
    }

    // 遍历list-原始方法
    public static void dataPrintByIt(List<String> lists) {
        Iterator<String> it = lists.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
    }
    // 遍历list-forEach方法，Java8新特性
    public static void dataPrintByForEach(List<String> lists){
        lists.forEach((x) -> System.out.print(x + " "));
        System.out.println();
    }

    public static void dataPrintByFE(List<String> lists){
        for(String item :lists){
            System.out.print(item+" ");
        }
        System.out.println();
    }

}
