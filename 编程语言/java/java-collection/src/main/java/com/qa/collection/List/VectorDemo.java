package com.qa.collection.List;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import java.util.function.Consumer;

public class VectorDemo {
    public static void main(String[] args) {
        //底层数据结构是数组,线程安全
        Vector<Integer> t = getVector();
        //通过快速随机访问遍历vector
        vectorByNormalFor(t);
        //通过增强for循环遍历vector
        vectorByStrengThenFor(t);
        //通过快迭代器遍历vector
        vectorByIterator(t);
        //通过ForEach遍历vector
        vectorByForEach(t);
        //通过Enumeration遍历vector
        vectorByEnumeration(t);
    }
    /**
     * 构建一个Vector集合,包含元素50000个
     * @return
     */
    private static Vector<Integer> getVector() {
        Vector vector = new Vector();
        for (int i = 0; i < 50000; i++){
            vector.add(i);
        }
        return vector;
    }

    /**
     * 通过快速随机访问遍历vector
     */
    private static void vectorByNormalFor(Vector<Integer> vector) {
        // 记录开始时间
        long start = System.currentTimeMillis();
        int size = vector.size();
        for (int i = 0; i < size; i++) {
            vector.get(i);
        }
        // 记录用时
        long interval = System.currentTimeMillis() - start;
        System.out.println("vectorByNormalFor：" + interval + " ms");
    }

    /**
     * 通过增强for循环遍历vector
     * @param vector
     */
    public static void vectorByStrengThenFor(Vector<Integer> vector){
        // 记录开始时间
        long start = System.currentTimeMillis();
        for (Integer i : vector) {

        }
        // 记录用时
        long interval = System.currentTimeMillis() - start;
        System.out.println("vectorByStrengThenFor：" + interval + " ms");
    }

    /**
     * 通过快迭代器遍历vector
     */
    private static void vectorByIterator(Vector<Integer> vector) {
        // 记录开始时间
        long start = System.currentTimeMillis();
        for(Iterator iter = vector.iterator(); iter.hasNext();) {
            iter.next();
        }
        // 记录用时
        long interval = System.currentTimeMillis() - start;
        System.out.println("vectorByIterator：" + interval + " ms");
    }
    /**
     * 通过ForEach遍历vector
     */
    private  static void vectorByForEach(Vector<Integer> vector){
        // 记录开始时间
        long start = System.currentTimeMillis();
       vector.forEach(new Consumer<Integer>() {
           @Override
           public void accept(Integer integer) {

           }
       });
        // 记录用时
        long interval = System.currentTimeMillis() - start;
        System.out.println("vectorByForEach：" + interval + " ms");
    }
    /**
     * 通过Enumeration遍历vector
     * Enumeration（枚举）接口的作用和Iterator类似，只提供了遍历Vector和HashTable类型集合元素的功能，不支持元素的移除操作。
     */
    private static void vectorByEnumeration(Vector<Integer> vector){
        // 记录开始时间
        long start = System.currentTimeMillis();
        Enumeration<Integer> enume = vector.elements();
        while (enume.hasMoreElements()){
            int i = enume.nextElement().intValue();
        }
        // 记录用时
        long interval = System.currentTimeMillis() - start;
        System.out.println("vectorByEnumeration：" + interval + " ms");
    }
}
