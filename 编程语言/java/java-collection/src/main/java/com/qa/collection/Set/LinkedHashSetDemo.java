package com.qa.collection.Set;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class LinkedHashSetDemo {
    public static void main(String[] args) {
        //按插入的顺序进行输出：
        //有序的
        LinkedHashSet<Dog> dset = new LinkedHashSet<Dog>();
        dset.add(new Dog(2));
        dset.add(new Dog(1));
        dset.add(new Dog(3));
        dset.add(new Dog(5));
        dset.add(new Dog(4));

        Iterator<Dog> iterator = dset.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }
}
