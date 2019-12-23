package com.qa.collection.Set;

import java.util.HashSet;
import java.util.Iterator;

public class HashSetDemo {
    public static void main(String[] args) {
        //底层数据是哈希表
        //无序的
        HashSet<String> hashSet = new HashSet<String>();

        System.out.println("HashSet初始容量大小："+hashSet.size());

        //元素添加：
        hashSet.add("a");
        hashSet.add("b");
        hashSet.add("c");
        hashSet.add("d");
        hashSet.add("e");
        hashSet.add("f");
        hashSet.add("g");
        hashSet.add("h");
        System.out.println("HashSet容量大小："+hashSet.size());

        //迭代器遍历：
        Iterator<String> iterator = hashSet.iterator();
        while (iterator.hasNext()){
            String str = iterator.next();
            System.out.println(str);
        }
        //增强for循环
        for(String str:hashSet){
            if("d".equals(str)){
                System.out.println("你就是我想要的元素:"+str);
            }
            System.out.println(str);
        }

        //元素删除：
//        hashSet.remove("d");
//        System.out.println("HashSet元素大小：" + hashSet.size());
//        hashSet.clear();
//        System.out.println("HashSet元素大小：" + hashSet.size());

        //集合判断：
        boolean isEmpty = hashSet.isEmpty();
        System.out.println("HashSet是否为空：" + isEmpty);
        boolean isContains = hashSet.contains("a");
        System.out.println("HashSet是否为空：" + isContains);


        HashSet<Dog> dset = new HashSet<Dog>();
        dset.add(new Dog(2));
        dset.add(new Dog(1));
        dset.add(new Dog(3));
        dset.add(new Dog(5));
        dset.add(new Dog(4));
        Iterator<Dog> iterator2 = dset.iterator();
        while (iterator2.hasNext()) {
            System.out.print(iterator2.next() + " ");
        }
    }

}
