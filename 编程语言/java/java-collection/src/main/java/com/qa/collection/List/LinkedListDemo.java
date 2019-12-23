package com.qa.collection.List;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListDemo {
    public static void main(String[] args) {
        //LinkedList基于链表的数据结构,线程不安全
        LinkedList<Integer> list = getLinkedList();
        //通过快速随机访问遍历LinkedList
        listByNormalFor(list);
        //通过增强for循环遍历LinkedList
        listByStrengThenFor(list);
        //通过快迭代器遍历LinkedList
        listByIterator(list);
    }
    /**
     * 构建一个LinkedList集合,包含元素50000个
     * @return
     */
    private static LinkedList<Integer> getLinkedList() {
        //双链表
        LinkedList list = new LinkedList();
        for (int i = 0; i < 50000; i++){
            list.add(i);
        }
        return list;
    }
    /**
     * 通过快速随机访问遍历LinkedList
     */
    private static void listByNormalFor(LinkedList<Integer> list) {
        // 记录开始时间
        long start = System.currentTimeMillis();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            list.get(i);
        }
        // 记录用时
        long interval = System.currentTimeMillis() - start;
        System.out.println("listByNormalFor：" + interval + " ms");
    }

    /**
     * 通过增强for循环遍历LinkedList
     * @param list
     */
    public static void listByStrengThenFor(LinkedList<Integer> list){
        // 记录开始时间
        long start = System.currentTimeMillis();
        for (Integer i : list) {

        }
        //在java8中可以写成如下
        //list.forEach(x -> System.out.print(x));
        // 记录用时
        long interval = System.currentTimeMillis() - start;
        System.out.println("listByStrengThenFor：" + interval + " ms");
    }

    /**
     * 通过快迭代器遍历LinkedList
     */
    private static void listByIterator(LinkedList<Integer> list) {
        // 记录开始时间
        long start = System.currentTimeMillis();
        for(Iterator iter = list.iterator(); iter.hasNext();) {
            iter.next();
        }
        // 记录用时
        long interval = System.currentTimeMillis() - start;
        System.out.println("listByIterator：" + interval + " ms");
    }
}
