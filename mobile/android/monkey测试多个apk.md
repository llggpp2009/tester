# monkey测试多个apk

## 一、目的

如何跑多个apk的monkey？

**黑名单：执行除了黑名单中以外的apk；**

**白名单：只执行在白名单中的apk。**

##  二、黑名单的设置方法：

a.创建一个名称为blacklist的txt文档，在文件中输入应用程序的包名，如mms应用，则在文件中输入com.android.mms；

  如果有多个应用程序不想被执行，则在文件中添加多个包名，注意包名与包名之间均用回车键。

b.将blacklist.txt的文件导入到手机的/data目录下，然后在执行monkey测试的时候使用--pkg-blacklist-file参数再加上blacklist文件的存储路径，其他的参数设置则和测试单一apk没有区别。

例子：

```
monkey --pkg-blacklist-file /data/blacklist.txt -c <options> -s <seed> <限制语句> --throttle <milliseconds> -v 执行次数>  d:\blacklist_test.txt //执行黑名单以外的应用（注意pkg前面两个‘-’）
```

## 三、白名单的设置方法：

白名单的设置方法和黑名单的设置方法基本一致，只是在创建txt文档的时候，文件的命令应为whitelist。在执行monkey的时候，只执行白名单中的应用。

例子：

```
monkey --pkg-whitelist-file /data/whitelist.txt -c <options> -s <seed> <限制语句> --throttle <milliseconds> -v 执行次数 >  d:\whitelist_test.txt //执行白名单的应用（注意pkg前面两个‘-’）
```

