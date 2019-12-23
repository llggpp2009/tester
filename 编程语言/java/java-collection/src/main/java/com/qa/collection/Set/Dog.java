package com.qa.collection.Set;

//如果你想1个类的对象支持比较(排序), 就必须实现Comparable接口.
public class Dog implements Comparable<Dog>{
    int size;
    public Dog(int s) {
        size = s;
    }

    public String toString() {
        return size + "";
    }

    @Override
    public int compareTo(Dog o) {
        return size - o.size;
    }

}
