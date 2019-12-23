# Unable to connect to ADB

## 一、重启adb server

### 1.Android Studio切换到Terminal窗口

<img src="https://img-blog.csdnimg.cn/20190517155539148.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FhejUyMDkyOQ==,size_16,color_FFFFFF,t_70" style="zoom:80%;" />

### 2.重启adb server

```
$adb kill-server

$adb start-server
```

![](https://img-blog.csdnimg.cn/2019051716002537.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FhejUyMDkyOQ==,size_16,color_FFFFFF,t_70)

## 二、adb端口被占用

### 1.可能占用原因

​	大多情况是因为电脑本身安装了手机助手之类的（例如，应用宝，豌豆荚，百度手机助手等）那些手机助手通常也会包含AdbWinApi.dll文件，这就造成了启用adb时端口会被占用。

### 2.找到占用进程，并杀死进程

```
$adb nodaemon server
cannot bind 'tcp:5037'
```

#### 2.1.查看5037端口占用的进程号

```
$netstat -ano|findstr "5037" 
 	TCP 127.0.0.1:5037 0.0.0.0:0 LISTENING 8516
    TCP 127.0.0.1:5037 127.0.0.1:59163 TIME_WAIT 0
    TCP 127.0.0.1:5037 127.0.0.1:59164 TIME_WAIT 0 
    TCP 127.0.0.1:5037 127.0.0.1:59167 TIME_WAIT 0
```

#### 2.2.查看进程号的任务名

```
$tasklist |findstr "8516"
sjk_daemon                        8516 Console                    1     3,071 K
```

#### 2.3.查看任务列表中的任务，找到其进程号

```
$tasklist
Image Name                     PID Session Name        Session#    Mem Usage
========================= ======== ================ =========== ============
System Idle Process              0 Services                   0         24 K
System                           4 Services                   0      1,128 K
sjk_daemon                     963 Console                    1      3,071 K
tasklist.exe                  1260 Console                    1      5,856 K
```

#### 2.4.kill掉这个进程

```
$taskkill /f /pid 963
```

#### 2.5.启动adb server

```
$adb start-server
```