# JAVA集合类Map

## 一、Collection与Map的区别

- Collection中的集合，元素是孤立存在的（理解为单身），向集合中存储元素采用一个个元素的方式存储。

- Map中的集合，元素是成对存在的(理解为夫妻)。每个元素由键与值两部分组成，通过键可以找对所对应的值。

- Collection中的集合称为单列集合，Map中的集合称为双列集合。

- 需要注意的是，Map中的集合不能包含重复的键，值可以重复；每个键只能对应一个值。

- Map中常用的集合为HashMap集合、LinkedHashMap集合。

## 二、Map常用接口

```
Map
 ├Hashtable
 │
 ├HashMap
 │   └LinkedHashMap
 └TreeMap
```

- Map提供了一种映射关系，其中的元素是以键值对（key-value）的形式存储的，能够实现根据key快速查找value；
- Map中的键值对以Entry类型的对象实例形式存在；
- 键（key值）不可重复，value值可以重复，一个value值可以和很多key值形成对应关系，每个键最多只能映射到一个值。
- Map支持泛型，形式如：Map<K,V>Map中使用put(K key,V value)方法添加

### 表 1： 覆盖的方法

我们将这 Object 的这两个方法覆盖，以正确比较 Map 对象的等价性。 

| equals(Object o) | 比较指定对象与此 Map 的等价性 |
| ---------------- | ----------------------------- |
| hashCode()       | 返回此 Map 的哈希码           |

### 表 2： Map 更新方法

可以更改 Map 内容。 

| clear()                       | 从 Map 中删除所有映射               |
| ----------------------------- | ----------------------------------- |
| remove(Object key)            | 从 Map 中删除键和关联的值           |
| put(Object key, Object value) | 将指定值与指定键相关联              |
| clear()                       | 从 Map 中删除所有映射               |
| putAll(Map t)                 | 将指定 Map 中的所有映射复制到此 map |

### 表 3： 返回视图的 Map 方法

使用这些方法返回的对象，您可以遍历 Map 的元素，还可以删除 Map 中的元素。 

| entrySet() | 返回 Map 中所包含映射的 Set 视图。 Set 中的每个元素都是一个 Map.Entry 对象，可以使用 getKey() 和 getValue() 方法（还有一个 setValue() 方法）访问后者的键元素和值元素 |
| ---------- | ------------------------------------------------------------ |
| keySet()   | 返回 Map 中所包含键的 Set 视图。 删除 Set 中的元素还将删除 Map 中相应的映射（键和值） |
| values()   | 返回 map 中所包含值的 Collection 视图。 删除 Collection 中的元素还将删除 Map 中相应的映射（键和值） |

### 表 4： Map 访问和测试方法

这些方法检索有关 Map 内容的信息但不更改 Map 内容。 

| get(Object key)             | 返回与指定键关联的值                               |
| --------------------------- | -------------------------------------------------- |
| containsKey(Object key)     | 如果 Map 包含指定键的映射，则返回 true             |
| containsValue(Object value) | 如果此 Map 将一个或多个键映射到指定值，则返回 true |
| isEmpty()                   | 如果 Map 不包含键-值映射，则返回 true              |
| size()                      | 返回 Map 中的键-值映射的数目                       |

## 三、Map常用子类

- HashMap<K,V>：存储数据采用的哈希表结构，元素的存取顺序不能保证一致。由于要保证键的唯一、不重复，需要重写键的hashCode()方法、equals()方法。
- LinkedHashMap<K,V>：HashMap下有个子类LinkedHashMap，存储数据采用的哈希表结构+链表结构。通过链表结构可以保证元素的存取顺序一致；通过哈希表结构可以保证的键的唯一、不重复，需要重写键的hashCode()方法、equals()方法。
- 注意：Map接口中的集合都有两个泛型变量<K,V>,在使用时，要为两个泛型变量赋予数据类型。两个泛型变量<K,V>的数据类型可以相同，也可以不同。

### 1.HashMap

- HashMap是一个散列表，它存储的内容是键值对（key-value）映射。
- HashMap继承于AbstractMap,实现了Map、Cloneable、java.io.Serializable接口。
- HashMap的实现是不同步的，这意味着它不是线程安全的。它的key,value都可以是null。此外，HashMap中的映射不是有序的。
-  HashMap的实例有两个参数影响其性能：“初始容量”和“加载因子”。初始容量是哈希表在创建时的容量。加载因子是哈希表在其容量自动增加之前可以达到多满的一种尺度。通常默认加载因子是0.75，这是在时间和空间成本上寻求一种折衷。

**遍历方式**

#### 1.1 遍历HashMap的键值对

第一步：根据entrySet()获取HashMap的“键值对”的Set集合。
第二步：通过Iterator迭代器遍历“第一步”得到的集合。

```
//map中的key是String类型，value是Integer类型
Integer integ = null;
Iterator iter = map.entrySet().iterator();
while(iter.hasNext()) {
    Map.Entry entry = (Map.Entry)iter.next();
    //获取key
    key = (String)entry.getKey();
        // 获取value
    integ = (Integer)entry.getValue();
}
```

#### 1.2 遍历HashMap的键

第一步：根据keySet()获取HashMap的“键”的Set集合。
第二步：通过Iterator迭代器遍历“第一步”得到的集合。

```
//假设map是HashMap对象
//map中的key是String类型，value是Integer类型
String key = null;
Integer integ = null;
Iterator iter = map.keySet().iterator();
while (iter.hasNext()) {
        //获取key
    key = (String)iter.next();
        //根据key，获取value
    integ = (Integer)map.get(key);
}
```

#### 1.3 遍历HashMap的值

第一步：根据value()获取HashMap的“值”的集合。
第二步：通过Iterator迭代器遍历“第一步”得到的集合。

```
//假设map是HashMap对象 
//map中的key是String类型，value是Integer类型
Integer value = null;
Collection c = map.values();
Iterator iter= c.iterator();
while (iter.hasNext()) {
    value = (Integer)iter.next();
}
```

### 2.HashTable

- 和HashMap一样，Hashtable 也是一个散列表，它存储的内容是键值对(key-value)映射。
- Hashtable 继承于Dictionary，实现了Map、Cloneable、java.io.Serializable接口。
- Hashtable 的函数都是同步的，这意味着它是线程安全的。它的key、value都不可以为null。此外，Hashtable中的映射不是有序的。
- Hashtable 的实例有两个参数影响其性能：初始容量 和 加载因子。容量 是哈希表中桶 的数量，初始容量 就是哈希表创建时的容量。注意，哈希表的状态为  open：在发生“哈希冲突”的情况下，单个桶会存储多个条目，这些条目必须按顺序搜索。加载因子  是对哈希表在其容量自动增加之前可以达到多满的一个尺度。初始容量和加载因子这两个参数只是对该实现的提示。关于何时以及是否调用 rehash  方法的具体细节则依赖于该实现。
- 通常，默认加载因子是 0.75, 这是在时间和空间成本上寻求一种折衷。加载因子过高虽然减少了空间开销，但同时也增加了查找某个条目的时间（在大多数 Hashtable 操作中，包括 get 和 put 操作，都反映了这一点）。

**遍历方式**

#### 2.1 遍历Hashtable的键值对

第一步：根据entrySet()获取Hashtable的“键值对”的Set集合。
第二步：通过Iterator迭代器遍历“第一步”得到的集合。

```
// 假设table是Hashtable对象
// table中的key是String类型，value是Integer类型
Integer integ = null;
Iterator iter = table.entrySet().iterator();
while(iter.hasNext()) {
    Map.Entry entry = (Map.Entry)iter.next();
    // 获取key
    key = (String)entry.getKey();
        // 获取value
    integ = (Integer)entry.getValue();
}
```

#### 2.2 通过Iterator遍历Hashtable的键

```
// 假设table是Hashtable对象
// table中的key是String类型，value是Integer类型
String key = null;
Integer integ = null;
Iterator iter = table.keySet().iterator();
while (iter.hasNext()) {
        // 获取key
    key = (String)iter.next();
        // 根据key，获取value
    integ = (Integer)table.get(key);
}
```

#### 2.3 通过Iterator遍历Hashtable的值

```
// 假设table是Hashtable对象
// table中的key是String类型，value是Integer类型
Integer value = null;
Collection c = table.values();
Iterator iter= c.iterator();
while (iter.hasNext()) {
    value = (Integer)iter.next();
}
```

#### 2.4 通过Enumeration遍历Hashtable的键

第一步：根据keys()获取Hashtable的集合。
第二步：通过Enumeration遍历“第一步”得到的集合。

```
Enumeration enu = table.keys();
while(enu.hasMoreElements()) {
    System.out.println(enu.nextElement());
} 
```

#### 2.5 通过Enumeration遍历Hashtable的值

第一步：根据elements()获取Hashtable的集合。
第二步：通过Enumeration遍历“第一步”得到的集合。

```
Enumeration enu = table.elements();
while(enu.hasMoreElements()) {
    System.out.println(enu.nextElement());
}
```

### 3.TreeMap

- TreeMap是一个有序的key-value集合，它是通过红黑树实现的。
- TreeMap继承于AbstractMap,所以它是一个Map，即key-value集合。
- TreeMap实现了NavigableMap接口，意味着它支持一系列的导航方法。比如返回有序的key集合。
- TreeMap实现了Clonable接口，意味着它能被克隆。
- TreeMap实现了java.io.Serializable接口，意味着它支持序列化。
- TreeMap基于红黑树实现，该映射根据其键的自然顺序进行排序，或者根据创建映射时提供的Comparator进行排序，具体取决于使用的构造方法
- TreeMap是非同步的，它的iterator方法返回的迭代器是fail-fastl的

**遍历方式**

#### 3.1 遍历TreeMap的键值对

```
// 假设map是TreeMap对象
// map中的key是String类型，value是Integer类型
Integer integ = null;
Iterator iter = map.entrySet().iterator();
while(iter.hasNext()) {
    Map.Entry entry = (Map.Entry)iter.next();
    // 获取key
    key = (String)entry.getKey();
        // 获取value
    integ = (Integer)entry.getValue();
}
```

#### 3.2 遍历TreeMap的键 

```
// 假设map是TreeMap对象
// map中的key是String类型，value是Integer类型
String key = null;
Integer integ = null;
Iterator iter = map.keySet().iterator();
while (iter.hasNext()) {
        // 获取key
    key = (String)iter.next();
        // 根据key，获取value
    integ = (Integer)map.get(key);
}
```

#### 3.3 遍历TreeMap的值】

```
// 假设map是TreeMap对象
// map中的key是String类型，value是Integer类型
Integer value = null;
Collection c = map.values();
Iterator iter= c.iterator();
while (iter.hasNext()) {
    value = (Integer)iter.next();
}
```

### 4.LinkedHashMap

**LinkedHashMap = HashMap + 双向链表**

LinkedHashMap是HashMap的子类，但是内部还有一个双向链表维护键值对的顺序，每个键值对既位于哈希表中，也位于双向链表中。LinkedHashMap支持两种顺序插入顺序 、 访问顺序。

#### 4.1 插入顺序

先添加的在前面，后添加的在后面。修改操作不影响顺序

#### 4.2 访问顺序

所谓访问指的是get/put操作，对一个键执行get/put操作后，其对应的键值对会移动到链表末尾，所以最末尾的是最近访问的，最开始的是最久没有被访问的，这就是访问顺序。









### 

