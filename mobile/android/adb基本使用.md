# adb基本使用

## 一、adb简介

Android Debug Bridge，Android调试桥接器，简称adb，是用于管理模拟器或真机状态的万能工具，采用了客户端-服务器模型，包括三个部分：

​    1、**客户端部分**，运行在开发用的电脑上，可以在命令行中运行adb命令来调用该客户端，像ADB插件和DDMS这样的Android工具也可以调用adb客户端。

​    2、**服务端部分**，是运行在开发用电脑上的后台进程，用于管理客户端与运行在模拟器或真机的守护进程通信。

​    3、**守护进程部分**，运行于模拟器或手机的后台。

 当启动adb客户端时，客户端首先检测adb服务端进程是否运行，如果没有运行，则启动服务端。当服务端启动时，它会绑定到本地的TCP **5037**端口，并且监听从adb客户端发来的命令——所有的adb客户端都使用5037端口与adb服务端通信。

接下来服务端与所有正在运行的模拟器或手机连接。它通过扫描**5555-5585**之间的奇数号端口来搜索模拟器或手机，一旦发现adb守护进程，就通过此端口进行连接。需要说明的是，每一个模拟器或手机使用一对有序的端口，偶数号端口用于控制台连接，奇数号端口用于adb连接，例如：

​    Emulator 1, console: 5554
​    Emulator 1, adb: 5555
​    Emulator 2, console: 5556
​    Emulator 2, adb: 5557 ...

​    即如果模拟器与adb在5555端口连接，则其与控制台的连接就是5554端口。

​    当服务端与所有的模拟器建立连接之后，就可以使用adb命令来控制或者访问了。因为服务端管理着连接并且可以接收到从多个adb客户端的命令，所以可以从任何一个客户端或脚本来控制任何模拟器或手机设备。

## 二、adb基本命令

- 进入指定设备 `adb -s serialNumber shell`
- 查看版本 `adb version`
- 查看日志 `adb logcat`
- 查看设备 `adb devices`
- 连接状态 `adb get-state`
- 启动ADB服务 `adb start-server`
- 停止ADB服务 `adb kill-server`
- 电脑推送到手机 `adb push local remote`
- 手机拉取到电脑 `adb pull remote local`

## 三、adb shell下的am 与 pm

注:am和pm命令必须先切换到adb shell模式下才能使用

**am**

am全称activity manager，你能使用am去模拟各种系统的行为，例如去启动一个activity，强制停止进程，发送广播进程，修改设备屏幕属性等等。当你在adb shell命令下执行am命令:

```
am <command>
```

- 启动app `am start -n {packageName}/.{activityName}`
- 杀app的进程 `am kill `
- 强制停止一切 `am force-stop `
- 启动服务`am startservice`
- 停止服务 `am stopservice`
- 打开简书 `am start -a android.intent.action.VIEW -d http://www.jianshu.com/`
- 拨打10086 `am start -a android.intent.action.CALL -d tel:10086`

**pm**

pm全称package manager，你能使用pm命令去模拟[Android](https://link.jianshu.com?t=http://lib.csdn.net/base/android)行为或者查询设备上的应用等，当你在adb shell命令下执行pm命令：

```
pm <command>
```

- 列出手机所有的包名 `pm list packages`
- 安装/卸载 `pm install/uninstall`

## 四、模拟用户事件

- 文本输入:`adb shell input text `
   例:`手机端输出demo字符串，相应指令：adb shell input "demo".`
- 键盘事件：`input keyevent ，其中KEYCODE见本文结尾的附表`
   例:`点击返回键，相应指令： input keyevent 4.`
- 点击事件：`input tap  `
   例: `点击坐标（500，500），相应指令： input tap 500 500.`
- 滑动事件： `input swipe     `
   例: `从坐标(300，500)滑动到(100，500)，相应指令： input swipe 300 500 100 500.`
   例: `200ms时间从坐标(300，500)滑动到(100，500)，相应指令： input swipe 300 500 100 500 200.`

## 五、logcat日志

- 显示包含的logcat `logcat \| grep `
- 显示包含，并忽略大小写的logcat `logcat \| grep -i `
- 读完所有log后返回，而不会一直等待 `logcat -d`
- 清空log并退出 `logcat -c`
- 打印最近的count `logcat -t `
- 格式化输出Log，其中format有如下可选值： `logcat -v `

## 六、常用节点

```
查看节点值，例如：cat /sys/class/leds/lcd-backlight/brightness
修改节点值，例如：echo 128 > sys/class/leds/lcd-backlight/brightness
```

- LPM: `echo N > /sys/modue/lpm_levels/parameters/sleep_disabled`
- 亮度：`/sys/class/leds/lcd-backlight/brightness`
- CPU: `/sys/devices/system/cpu/cpu0/cpufreq`
- GPU: `/sys/class/ kgsl/kgsl-3d0/gpuclk`
- 限频：`cat /data/pmlist.config`
- 电流： `cat /sys/class/power_supply/battery/current_now`
- 查看Power： `dumpsys power`
- WIFI :`data/misc/wifi/wpa_supplicant.conf`
- 持有wake_lock: `echo a> sys/power/wake_lock`
- 释放wake_lock:`echo a> sys/power/wake_unlock`
- 查看Wakeup_source: `cat sys/kernel/debug/wakeup_sources`
- Display(关闭AD):`mv /data/misc/display/calib.cfg /data/misc/display/calib.cfg.bak 重启`
- 关闭cabc：`echo 0 > /sys/device/virtual/graphics/fb0/cabc_onoff`
- 打开cabc：`echo 3 > /sys/device/virtual/graphics/fb0/cabc_onoff`
- systrace：`sdk/tools/monitor`
- 限频：`echo /sys/devices/system/cpu/cpu0/cpufreq/scaling_max_freq 1497600`
- 当出现read-only 且 remount命令不管用时：`adb shell mount -o rw,remount /`
- 进入9008模式： `adb reboot edl`
- 查看高通gpio：`sys/class/private/tlmm 或者 sys/private/tlmm`
- 查看gpio占用情况：`sys/kernle/debug/gpio`

## 七. 远程ADB

为避免使用数据线，可通过wifi通信，前提是手机与PC处于同一局域网

**启动方法**：

```
adb tcpip 5555  //这一步，必须通过数据线把手机与PC连接后再执行  
adb connect <手机IP>
```

**停止方法**:

```
adb disconnect //断开wifi连接
adb usb //切换到usb模式
```

## 八、常用操作

- 查看当前 `ls`
- 打印当前路径 `pwd`
- 查看当前连接的设备 `adb devices`
- 终止adb服务进程 `adb kill-server`
- 重启adb服务进程 `adb start-server`
- PID是:8607 查看某个进程的日志 `adb logcat -v process |grep 8607`
- 清理缓存 `logcat -c`
- 打印xys标签log `adb logcat -s xys`
- 打印192.168.56.101:5555设备里的xys标签log `adb -s 192.168.56.101:5555 logcat -s xys`
- 打印在ActivityManager标签里包含start的日志 `adb logcat -s ActivityManager | findstr "START"`

> "-s"选项 : 设置输出日志的标签, 只显示该标签的日志;
>  "-f"选项 : 将日志输出到文件, 默认输出到标准输出流中, -f 参数执行不成功;
>  "-r"选项 : 按照每千字节输出日志, 需要 -f 参数, 不过这个命令没有执行成功;
>  "-n"选项 : 设置日志输出的最大数目, 需要 -r 参数, 这个执行 感觉 跟 adb logcat 效果一样;
>  "-v"选项 : 设置日志的输出格式, 注意只能设置一项;
>  "-c"选项 : 清空所有的日志缓存信息;
>  "-d"选项 : 将缓存的日志输出到屏幕上, 并且不会阻塞;
>  "-t"选项 : 输出最近的几行日志, 输出完退出, 不阻塞;
>  "-g"选项 : 查看日志缓冲区信息;
>  "-b"选项 : 加载一个日志缓冲区, 默认是 main, 下面详解;
>  "-B"选项 : 以二进制形式输出日志;

- 重启机器 `adb reboot`
- 获取序列号 `adb get-serialno`
- 重启到bootloader，即刷机模式 `adb reboot bootloader`
- 重启到recovery，即恢复模式 `adb reboot recovery`
- 安装APK：`adb install  //比如：adb install baidu.apk`
- 安装apk到sd卡： `adb install -s  // 比如：adb install -s baidu.apk`
- 卸载APK：`adb uninstall  //比如：adb uninstall com.baidu.search`
- 获取机器MAC地址 `adb shell cat /sys/class/net/wlan0/address`
- 启动应用：`adb shell am start -n  /. 例如:adb shell am start  -n yf.o2o.store/yf.o2o.store.activity.LoginActivity`
- 查看占用内存排序  `adb shell top`
- 查看占用内存前6的app：`adb shell top -m 6`
- 刷新一次内存信息，然后返回：`adb shell top -n 1`
- 查询各进程内存使用情况：`adb shell procrank`
- 杀死一个进程：`adb shell kill [pid]`
- 查看进程列表：`adb shell ps`
- 查看指定进程状态：`adb shell ps -x [PID]`
- 查看后台services信息： `adb shell service list`
- 查看当前内存占用： `adb shell cat /proc/meminfo`
- 查看IO内存分区：`adb shell cat /proc/iomem`
- 将system分区重新挂载为可读写分区：`adb remount`
- 从本地复制文件到设备： `adb push  `
- 从设备复制文件到本地： `adb pull  `
- 列出目录下的文件和文件夹，等同于dos中的dir命令：`adb shell ls`
- 进入文件夹，等同于dos中的cd 命令：`adb shell cd `
- 重命名文件： `adb shell rename path/oldfilename path/newfilename`
- 删除system/avi.apk： `adb shell rm /system/avi.apk`
- 删除文件夹及其下面所有文件：`adb shell rm -r `
- 移动文件：`adb shell mv path/file newpath/file`
- 设置文件权限：`adb shell chmod 777 /system/fonts/DroidSansFallback.ttf`
- 新建文件夹：`adb shell mkdir path/foldelname`
- 查看文件内容：`adb shell cat `
- 查看wifi密码：`adb shell cat /data/misc/wifi/*.conf`
- 清除log缓存：`adb logcat -c`
- 查看bug报告：`adb bugreport`
- 获取设备名称：`adb shell cat /system/build.prop`
- 查看ADB帮助：`adb help`
- 跑monkey：
   `adb shell monkey -v -p your.package.name 500`
   `adb -s 192.168.244.151:5555 shell monkey -v -p com.bolexim 500`

## 九、附表

下表中， 箭头左边为keycode值，箭头右边为keycode的含义，部分用中文标注

```
0 –> “KEYCODE_UNKNOWN”
1 –> “KEYCODE_MENU”
2 –> “KEYCODE_SOFT_RIGHT”
3 –> “KEYCODE_HOME” //Home键
4 –> “KEYCODE_BACK” //返回键
5 –> “KEYCODE_CALL” 
6 –> “KEYCODE_ENDCALL” 
7 –> “KEYCODE_0” //数字键0
8 –> “KEYCODE_1” 
9 –> “KEYCODE_2” 
10 –> “KEYCODE_3”
11 –> “KEYCODE_4” 
12 –> “KEYCODE_5” 
13 –> “KEYCODE_6” 
14 –> “KEYCODE_7” 
15 –> “KEYCODE_8” 
16 –> “KEYCODE_9” 
17 –> “KEYCODE_STAR” 
18 –> “KEYCODE_POUND” 
19 –> “KEYCODE_DPAD_UP” 
20 –> “KEYCODE_DPAD_DOWN” 
21 –> “KEYCODE_DPAD_LEFT”
22 –> “KEYCODE_DPAD_RIGHT” 
23 –> “KEYCODE_DPAD_CENTER” 
24 –> “KEYCODE_VOLUME_UP” //音量键+
25 –> “KEYCODE_VOLUME_DOWN” //音量键-
26 –> “KEYCODE_POWER” //Power键
27 –> “KEYCODE_CAMERA” 
28 –> “KEYCODE_CLEAR”
29 –> “KEYCODE_A” //字母键A
30 –> “KEYCODE_B” 
31 –> “KEYCODE_C” 
32 –> “KEYCODE_D” 
33 –> “KEYCODE_E” 
34 –> “KEYCODE_F” 
35 –> “KEYCODE_G”
36 –> “KEYCODE_H”
37 –> “KEYCODE_I”
38 –> “KEYCODE_J” 
39 –> “KEYCODE_K” 
40 –> “KEYCODE_L” 
41 –> “KEYCODE_M”
42 –> “KEYCODE_N” 
43 –> “KEYCODE_O” 
44 –> “KEYCODE_P” 
45 –> “KEYCODE_Q” 
46 –> “KEYCODE_R”
47 –> “KEYCODE_S”
48 –> “KEYCODE_T” 
49 –> “KEYCODE_U” 
50 –> “KEYCODE_V” 
51 –> “KEYCODE_W” 
52 –> “KEYCODE_X”
53 –> “KEYCODE_Y” 
54 –> “KEYCODE_Z”
55 –> “KEYCODE_COMMA” 
56 –> “KEYCODE_PERIOD”
57 –> “KEYCODE_ALT_LEFT” 
58 –> “KEYCODE_ALT_RIGHT” 
59 –> “KEYCODE_SHIFT_LEFT” 
60 –> “KEYCODE_SHIFT_RIGHT”
61 -> “KEYCODE_TAB” 
62 –> “KEYCODE_SPACE” 
63 –> “KEYCODE_SYM” 
64 –> “KEYCODE_EXPLORER” 
65 –> “KEYCODE_ENVELOPE” 
66 –> “KEYCODE_ENTER” //回车键
67 –> “KEYCODE_DEL” 
68 –> “KEYCODE_GRAVE” 
69 –> “KEYCODE_MINUS” 
70 –> “KEYCODE_EQUALS” 
71 –> “KEYCODE_LEFT_BRACKET” 
72 –> “KEYCODE_RIGHT_BRACKET” 
73 –> “KEYCODE_BACKSLASH” 
74 –> “KEYCODE_SEMICOLON” 
75 –> “KEYCODE_APOSTROPHE”
76 –> “KEYCODE_SLASH” 
77 –> “KEYCODE_AT” 
78 –> “KEYCODE_NUM” 
79 –> “KEYCODE_HEADSETHOOK” 
80 –> “KEYCODE_FOCUS”
81 –> “KEYCODE_PLUS”
82 –> “KEYCODE_MENU”
83 –> “KEYCODE_NOTIFICATION”
84 –> “KEYCODE_SEARCH”
```