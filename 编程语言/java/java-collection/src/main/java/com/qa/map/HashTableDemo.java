package com.qa.map;

import java.util.*;

/*
 * @desc 遍历Hashtable的测试程序。
 *   (01) 通过entrySet()去遍历key、value，参考实现函数：
 *        iteratorHashtableByEntryset()
 *   (02) 通过keySet()去遍历key，参考实现函数：
 *        iteratorHashtableByKeyset()
 *   (03) 通过values()去遍历value，参考实现函数：
 *        iteratorHashtableJustValues()
 *   (04) 通过Enumeration去遍历key，参考实现函数：
 *        enumHashtableKey()
 *   (05) 通过Enumeration去遍历value，参考实现函数：
 *        enumHashtableValue()
 */
public class HashTableDemo {
    public static void main(String[] args) {
        //HashTable是无序的,线程安全的,Hashtable效率较低
        int val = 0;
        String key = null;
        Integer value = null;
        Random r = new Random();
        Hashtable table = new Hashtable();

        for (int i=0; i<12; i++) {
            // 随机获取一个[0,100)之间的数字
            val = r.nextInt(100);

            key = String.valueOf(val);
            value = r.nextInt(5);
            // 添加到Hashtable中
            table.put(key, value);
            System.out.println(" key:"+key+" value:"+value);
        }
        // 通过entrySet()遍历Hashtable的key-value
        iteratorHashtableByEntryset(table) ;

        // 通过keySet()遍历Hashtable的key-value
        iteratorHashtableByKeyset(table) ;

        // 单单遍历Hashtable的value
        iteratorHashtableJustValues(table);

        // 遍历Hashtable的Enumeration的key
        enumHashtableKey(table);

        // 遍历Hashtable的Enumeration的value
        //enumHashtableValue(table);
    }
    /*
     * 通过Enumeration遍历Hashtable的key
     * 效率高!
     */
    private static void enumHashtableKey(Hashtable table) {
        if (table == null)
            return ;

        System.out.println("\nenumeration Hashtable");
        Enumeration enu = table.keys();
        while(enu.hasMoreElements()) {
            System.out.println(enu.nextElement());
        }
    }


    /*
     * 通过Enumeration遍历Hashtable的value
     * 效率高!
     */
    private static void enumHashtableValue(Hashtable table) {
        if (table == null)
            return ;

        System.out.println("\nenumeration Hashtable");
        Enumeration enu = table.elements();
        while(enu.hasMoreElements()) {
            System.out.println(enu.nextElement());
        }
    }

    /*
     * 通过entry set遍历Hashtable
     * 效率高!
     */
    private static void iteratorHashtableByEntryset(Hashtable table) {
        if (table == null)
            return ;

        System.out.println("\niterator Hashtable By entryset");
        String key = null;
        Integer integ = null;
        Iterator iter = table.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry entry = (Map.Entry)iter.next();

            key = (String)entry.getKey();
            integ = (Integer)entry.getValue();
            System.out.println(key+" -- "+integ.intValue());
        }
    }

    /*
     * 通过keyset来遍历Hashtable
     * 效率低!
     */
    private static void iteratorHashtableByKeyset(Hashtable table) {
        if (table == null)
            return ;

        System.out.println("\niterator Hashtable By keyset");
        String key = null;
        Integer integ = null;
        Iterator iter = table.keySet().iterator();
        while (iter.hasNext()) {
            key = (String)iter.next();
            integ = (Integer)table.get(key);
            System.out.println(key+" -- "+integ.intValue());
        }
    }
    /*
     * 遍历Hashtable的values
     */
    private static void iteratorHashtableJustValues(Hashtable table) {
        if (table == null)
            return ;

        Collection c = table.values();
        Iterator iter= c.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }

}
