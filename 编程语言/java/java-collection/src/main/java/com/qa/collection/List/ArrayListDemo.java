package com.qa.collection.List;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class ArrayListDemo {
    public static void main(String[] args) {
        //ArrayList是实现了基于动态数组的数据结构,线程不安全
        ArrayList c = new ArrayList();
        c.add("a");
        c.add("b");
        c.add("c");
        c.add("d");

        Iterator it = c.iterator();
        //后向遍历
        while (it.hasNext()){
            //向下转型
            String s=(String) it.next();
            System.out.println(s);
        }

        ListIterator li = c.listIterator();

        //前向遍历
        while (li.hasNext()){
            String s = (String)li.next();
            System.out.println(s);
        }
        //后向遍历
        while (li.hasPrevious()){
            String s = (String)li.previous();
            System.out.println(s);
        }

        //get方法遍历
        for(int i=0;i<c.size();i++){
            String s = (String)c.get(i);
            System.out.println(s);
        }

    }
}
