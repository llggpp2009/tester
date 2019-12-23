package com.qa.collection.Set;

import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetDemo {
    public static void main(String[] args) {
        //底层数据是红黑二叉树
        //有序的
        TreeSet<Integer> tree = new TreeSet<Integer>();
        tree.add(12);
        tree.add(63);
        tree.add(34);
        tree.add(45);

        Iterator<Integer> iterator = tree.iterator();
        System.out.print("Tree set data: ");
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }
}
