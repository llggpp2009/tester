# monkey详解

## 一、monkey简介： 

Monkey  就是SDK中附带的一个工具。Monkey是Android中的一个命令行工具，可以运行在模拟器里或实际设备中。它向系统发送伪随机的用户事件流(如按键输入、触摸屏输入、手势输入等)，实现对正在开发的应用程序进行压力测试。Monkey测试是一种为了测试软件的稳定性、健壮性的快速有效的方法。

该工具用于进行压力测试。然后开发人员结合monkey 打印的日志和系统打印的日志，分析测试中的问题

**Monkey 测试的特点**：

Monkey 测试,所有的事件都是随机产生的，不带任何人的主观性。

1、测试的对象仅为应用程序包，有一定的局限性。

2、Monky测试使用的事件数据流是随机的，不能进行自定义。

3、可对MonkeyTest的对象，事件数量，类型，频率等进行设置。

## 二、Monkey的基本用法

基本语法如下：

`$ adb shell monkey [options]`

如果不指定options，Monkey将以无反馈模式启动，并把事件任意发送到安装在目标环境中的全部包。下面是一个更为典型的命令行示例，它启动指定的应用程序，并向其发送500个伪随机事件：

`$ adb shell monkey -p your.package.name -v 500`
使用android自动化测试工具monkeyrunner启动应用时，需要填写被测程序的包名和启动的Activity，以下有两种查看应用包名package和入口activity名称的方法：

方法一：使用aapt   //aapt是sdk自带的一个工具，在sdk\builds-tools\目录下

1.以ES文件浏览器为例，命令行中切换到aapt.exe目录执行：aapt dump badging E:\apk\es3.apk

![img](https://images2015.cnblogs.com/blog/1122011/201706/1122011-20170606094818434-288685894.png)

2.在android sdk目录搜索可以找到aapt.exe，如果没有可以下载apktool。

## 三、Monkey测试的一个实例

通过这个实例，我们能理解Monkey测试的步骤以及如何知道哪些应用程序能够用Monkey进行测试。

Windows下（注：2—4步是为了查看我们可以测试哪些应用程序包，可省略）：

1、通过eclipse启动一个Android的emulator

2、在命令行中输入：adb devices查看设备连接情况

C:\Documents and Settings\Administrator>adb devices

List of devices attached

emulator-5554  device

3、在有设备连接的前提下，在命令行中输入：adb shell 进入shell界面

C:\Documents and Settings\Administrator>adb shell

\#

4、查看data/data文件夹下的应用程序包。注：我们能测试的应用程序包都在这个目录下面

C:\Documents and Settings\Administrator>adb shell

\# ls data/data

ls data/data

5、以com.android.calculator2作为对象进行MonkeyTest

\#monkey -p com.android.calculator2 -v 500

其中-p表示对象包 –v 表示反馈信息级别

运行过程中，Emulator中的应用程序在不断地切换画面。

按照选定的不同级别的反馈信息，在Monkey中还可以看到其执行过程报告和生成的事件。 

## 四、使用monkey help 命令查看命令参数

```
C:\Users\chenfenping>adb shell monkey -help
usage: monkey [-p ALLOWED_PACKAGE [-p ALLOWED_PACKAGE] ...]
    [-c MAIN_CATEGORY [-c MAIN_CATEGORY] ...]
    [--ignore-crashes] [--ignore-timeouts]
    [--ignore-security-exceptions]
    [--monitor-native-crashes] [--ignore-native-crashes]
    [--kill-process-after-error] [--hprof]
    [--pct-touch PERCENT] [--pct-motion PERCENT]
    [--pct-trackball PERCENT] [--pct-syskeys PERCENT]
    [--pct-nav PERCENT] [--pct-majornav PERCENT]
    [--pct-appswitch PERCENT] [--pct-flip PERCENT]
    [--pct-anyevent PERCENT] [--pct-pinchzoom PERCENT]
    [--pkg-blacklist-file PACKAGE_BLACKLIST_FILE]
    [--pkg-whitelist-file PACKAGE_WHITELIST_FILE]
    [--wait-dbg] [--dbg-no-events]
    [--setup scriptfile] [-f scriptfile [-f scriptfile] ...]
    [--port port]
    [-s SEED] [-v [-v] ...]
    [--throttle MILLISEC] [--randomize-throttle]
    [--profile-wait MILLISEC]
    [--device-sleep-time MILLISEC]
    [--randomize-script]
    [--script-log]
    [--bugreport]
    [--periodic-bugreport]
    COUNT
```

### 1 参数： -p

用于约束限制，用此参数指定一个或多个包（Package，即App）。指定包之后，monkey将只允许系统启动指定的APP,如果不指定包，将允许系统启动设备中的所有APP.

\* 指定一个包： `adb shell monkey -p cn.emoney.acg 10`

\* 指定多个包：`adb shell monkey -p cn.emoney.acg –p cn.emoney.wea -p cn.emoney.acg 100`

\* 不指定包：`adb shell monkey 100`

### 2 参数:   -v

用于指定反馈信息级别（信息级别就是日志的详细程度），总共分3个级别，分别对应的参数如下表所示：

日志级别 Level0

示例 `adb shell monkey -p cn.emoney.acg –v 100`

说明缺省值，仅提供启动提示、测试完成和最终结果等少量信息

日志级别 Level 1

示例 `adb shell monkey -p cn.emoney.acg –v -v 100`

说明提供较为详细的日志，包括每个发送到Activity的事件信息

日志级别 Level 2

示例 `adb shell monkey -p cn.emoney.acg –v -v –v 100`

说明最详细的日志，包括了测试中选中/未选中的Activity信息

### 3 参数： -s

用于指定伪随机数生成器的seed值，如果seed相同，则两次Monkey测试所产生的事件序列也相同的。

Monkey 测试1：`adb shell monkey -p cn.emoney.acg -s 10 100`

Monkey 测试2：`adb shell monkey -p cn.emoney.acg –s 10 100`

两次测试的效果是相同的，因为模拟的用户操作序列（每次操作按照一定的先后顺序所组成的一系列操作，即一个序列）是一样的。

### 4 参数： --throttle<毫秒>

用于指定用户操作（即事件）间的时延，单位是毫秒；

`adb shell monkey -p cn.emoney.acg --throttle 5000 100`

### 5 参数： --ignore-crashes

用于指定当应用程序崩溃时（Force& Close错误），Monkey是否停止运行。如果使用此参数，即使应用程序崩溃，Monkey依然会发送事件，直到事件计数完成。

`adb shellmonkey -p cn.emoney.acg --ignore-crashes 1000`

测试过程中即使程序崩溃，Monkey依然会继续发送事件直到事件数目达到1000为止

`adb shellmonkey -p cn.emoney.acg 1000`

测试过程中，如果acg程序崩溃，Monkey将会停止运行

### 6 参数： --ignore-timeouts

用于指定当应用程序发生ANR（Application No Responding）错误时，Monkey是否停止运行。如果使用此参数，即使应用程序发生ANR错误，Monkey依然会发送事件，直到事件计数完成。

`adb shellmonkey -p cn.emoney.acg --ignore-timeouts 1000`

### 7 参数： --ignore-security-exceptions

用于指定当应用程序发生许可错误时（如证书许可，网络许可等），Monkey是否停止运行。如果使用此参数，即使应用程序发生许可错误，Monkey依然会发送事件，直到事件计数完成。

`adb shellmonkey -p cn.emoney.acg --ignore-security-exception 1000`

###  8 参数： --kill-process-after-error

用于指定当应用程序发生错误时，是否停止其运行。如果指定此参数，当应用程序发生错误时，应用程序停止运行并保持在当前状态

（注意：应用程序仅是静止在发生错误时的状态，系统并不会结束该应用程序的进程）。

`adb shellmonkey -p cn.emoney.acg --kill-process-after-error 1000`

###  9 参数： --monitor-native-crashes

用于指定是否监视并报告应用程序发生崩溃的本地代码。

`adb shellmonkey -p cn.emoney.acg --monitor-native-crashes 1000`

### 10 参数： --pct-｛+事件类别｝｛+事件类别百分比｝

用于指定每种类别事件的数目百分比（在Monkey事件序列中，该类事件数目占总事件数目的百分比）
示例:

#### --pct-**touch**｛+百分比｝

调整触摸事件的百分比(**触摸事件**是一个down-up事件，它发生在屏幕上的某单一位置)

```
adb shell monkey -p cn.emoney.acg --pct-touch 10 1000
```

#### --pct-**motion** ｛+百分比｝

调整动作事件的百分比(**动作事件**由屏幕上某处的一个down事件、一系列的伪随件机事和一个up事件组成)

```
adb shell monkey -p cn.emoney.acg --pct-motion 20 1000
```

#### --pct-**trackball** ｛+百分比｝

调整轨迹事件的百分比(**轨迹事件**由一个或几个随机的移动组成，有时还伴随有点击)

```
adb shell monkey -p cn.emoney.acg --pct-trackball 30 1000
```

#### --pct-**nav** ｛+百分比｝

调整“基本”导航事件的百分比(**导航事件**由来自方向输入设备的up/down/left/right组成)

```
adb shell monkey -p cn.emoney.acg --pct-nav 40 1000
```

#### --pct-**majornav** ｛+百分比｝

调整“主要”导航事件的百分比(这些导航事件通常引发图形界面中的动作，如：5-way键盘的中间按键、回退按键、菜单按键)

```
adb shell monkey -p cn.emoney.acg --pct-majornav 50 1000
```

## 五、输出monkeylog

跑monkey的时候或者想抓程序log导出时，有时会提示：`cannot create D:monkeytest.txt: read-only file system`

为什么有时候可以有时候不可以？

后来发现跟使用使用习惯不一样，一会是先进入adb shell 再用命令，一会是直接命令进入。

进入adb shell后再用命令就会失败~

正确方法：退出shell或者执行命令时先不要进shell

```
C:\Documents and Settings\Administrator>adb shell monkey -p 包名  -v 300  >e:\text.txt
```

进入adb shell后就相当于进入linux的root下面，没有权限在里面创建文件~

## 六、Monkey测试结果分析

### 一. 初步分析方法：

Monkey测试出现错误后，一般的查错步骤为以下几步：

1、找到是monkey里面的哪个地方出错

2、查看Monkey里面出错前的一些事件动作，并手动执行该动作

3、若以上步骤还不能找出，可以使用之前执行的monkey命令再执行一遍，**注意seed值要一样**--**复现**

一般的测试结果分析：

1、 **ANR问题**：在日志中搜索“ANR”

2、**崩溃问题**：在日志中搜索“Exception” Force Close

### 二. 详细分析monkey日志：

将执行Monkey生成的log，从手机中导出并打开查看该log；在log的最开始都会显示Monkey执行的seed值、执行次数和测试的包名。

首先我们需要查看Monkey测试中是否出现了ANR或者异常，具体方法如上述。

然后我们要分析log中的具体信息，方法如下：

查看log中第一个Switch，主要是查看Monkey执行的是那一个Activity，譬如下面的log中，执行的是`com.tencent.smtt.SplashActivity`，在下一个swtich之间的，如果出现了崩溃或其他异常，可以在该Activity中查找问题的所在。

```
:Switch:#Intent;action=android.intent.action.MAIN;category=android.intent.category.LAUNCHER;launchFlags=0x10000000;component=com.tencent.smtt/.SplashActivity;end

 // Allowing start of Intent {act=android.intent.action.MAIN  cat=[android.intent.category.LAUNCHER]cmp=com.tencent.smtt/.SplashActivity } in package com.tencent.smtt
```

在下面的log中，Sending Pointer ACTION_DOWN和Sending Pointer ACTION_UP代表当前执行了一个单击的操作;

Sleeping for 500 milliseconds这句log是执行Monkey测试时，throttle设定的间隔时间，每出现一次，就代表一个事件。

```
SendKey(ACTION_DOWN) //KEYCODE_DPAD_DOWN 代表当前执行了一个点击下导航键的操作；

Sending Pointer ACTION_MOVE 代表当前执行了一个滑动界面的操作。
:Sending Pointer ACTION_DOWN x=47.0 y=438.0
:Sending Pointer ACTION_MOVE x=-2.0 y=-4.0
```

**ANR**
如果Monkey测试顺利执行完成，在log的最后，会打印出当前执行事件的次数和所花费的时间；// Monkey finished代表执行完成。Monkey执行中断，在log的最后也能查看到当前已执行的次数。Monkey执行完成的log具体如下：

```
Events injected: 6000

:Dropped: keys=0 pointers=9 trackballs=0 flips=0

\## Network stats: elapsed time=808384ms (0ms mobile, 808384ms wifi, 0msnot connected)

// Monkey finished
```

范例：

Monkey测试结果：

```
# monkey -p wfh.LessonTable -v -v -v 200

:Monkey: seed=0 count=200
:AllowPackage: wfh.LessonTable
:IncludeCategory: android.intent.category.LAUNCHER
:IncludeCategory: android.intent.category.MONKEY
// Selecting main activities from category android.intent.category.LAUNCHER
//  - NOT USING main activity com.android.browser.BrowserActivity (from package com.android.browser)
// Seeded: 0
// Event percentages:
//  0: 15.0%
//  1: 10.0%
//  2: 15.0%
//  3: 25.0%
//  4: 15.0%
//  5: 2.0%
//  6: 2.0%
//  7: 1.0%
//  8: 15.0%

:Switch:  #Intent;action=android.intent.action.MAIN;category=android.intent.category.LAUNCHER;launchFlags=0x10000000;component=wfh.LessonTable/.MainTable;end

  // Allowing start of Intent {  act=android.intent.action.MAIN cat=[android.intent.category.LAUNCHER]  cmp=wfh.LessonTable/.MainTable } in package wfh.LessonTable

Sleeping for 0 milliseconds

:SendKey (ACTION_DOWN): 21  // KEYCODE_DPAD_LEFT
:SendKey (ACTION_UP): 21  // KEYCODE_DPAD_LEFT
Sleeping for 0 milliseconds //------------------------------------用－－throttle来设置一个起效的事件发生后时延时。

:Sending Pointer ACTION_DOWN x=0.0 y=0.0
:Sending Pointer ACTION_UP x=0.0 y=0.0
Sleeping for 0 milliseconds
:Sending Pointer ACTION_MOVE x=0.0 y=0.0
```

当测试到ACTION_MOVE x=0.0 y=0.0这个动作时，发生了FC（Force Close）错误，以下为输出错误信息。同时在LogCat里面也有错误输出，而且LogCat里面的错误信息更为详细，在实际的测试中应该结合两者输出的信息进行调试程序。

```
// CRASH: wfh.LessonTable (pid 1973)
// Short Msg: java.lang.NullPointerException
// Long Msg: java.lang.NullPointerException
// Build Label: android:generic/sdk/generic/:2.1-update1/ECLAIR/35983:eng/test-keys
// Build Changelist: 35983
// Build Time: 1273161972
// ID:
// Tag: AndroidRuntime
// java.lang.NullPointerException:
//  at android.widget.TabHost.dispatchKeyEvent(TabHost.java:279)
//  at android.view.ViewGroup.dispatchKeyEvent(ViewGroup.java:748)

** Monkey aborted due to error.
Events injected: 190
:Dropped: keys=0 pointers=11 trackballs=0 flips=0
\## Network stats: elapsed time=27954ms (27954ms mobile, 0ms wifi, 0ms not connected)
** System appears to have crashed at event 190 of 200 using seed 0
\#
```

开始monkey测试时android的LogCat输出的信息：

```
11-01 08:52:53.712: DEBUG/AndroidRuntime(2077):  >>>>>>>>>>>>>> AndroidRuntime  START <<<<<<<<<<<<<<
11-01 08:52:53.742: DEBUG/AndroidRuntime(2077): CheckJNI is ON
11-01 08:52:54.453: DEBUG/AndroidRuntime(2077): ---
```

以下为LogCat输出的错误信息，在以下的信息中首先从自己的包中找错误，如果没有自己的包的话就再找发生错误的包的第一个发生了异常。由错误提示可以看出很大的可能是因为TabHost引发的异常。经过查看代码发现是由于TabHost的编写不规范，TabHost与其中一个view放在了一起，在monkey测试做滚球上下滚动时当滚到TabHost时就发生了异常了。所以把TabHost与Activity分开写就不会出现些问题了。

```
11-01 08:53:27.113: ERROR/AndroidRuntime(1973): Uncaught handler: thread main exiting due to uncaught exception
11-01 08:53:27.133: ERROR/AndroidRuntime(1973): java.lang.NullPointerException
11-01 08:53:27.133: ERROR/AndroidRuntime(1973):   at android.widget.TabHost.dispatchKeyEvent(TabHost.java:279)
11-01 08:53:27.133: ERROR/AndroidRuntime(1973):   at android.view.ViewGroup.dispatchKeyEvent(ViewGroup.java:748)
```

## 七、总结：

在monkey测试中常用的命令组合有：

1、monkey -p com.yourpackage -v 500／／简单的输出测试的信息。

2、monkey -p com.yourpackage -v -v  500 //以深度为二级输出测试信息。

4、monkey -p com.yourpackage -s 数字 -v 500／／为随机数的事件序列定一个值，若出现问题下次可以重复同样的系列进行排错。

5、monkey -p com.yourpackage -v --throttle 3000 500//为每一次执行一次有效的事件后休眠3000毫秒。

6、工作中为了保证测试数量的完整进行，我们一般不会在发生错误时立刻退出压力测试。monkey 测试命令如下：

/**
 \* monkey 作用的包：`com.ckt.android.junit`
 \* 产生时间序列的种子值：500
 \* 忽略程序崩溃、 忽略超时、 监视本地程序崩溃、 详细信息级别为2， 产生10000个事件 。
 */

```
adb shell monkey -p com.xy.android.junit -s 500 --ignore-crashes --ignore-timeouts --monitor-native-crashes -v -v 10000 > E:\monkey_log\java_monkey_log.txt
```

7、如果monkey事件无法触发，或出现这种情况** SYS_KEYS has no physical keys but with factor 2.0%.
在命令中加入–pct-syskeys 0，如下：

```
adb shell monkey -p com.android.browser --pct-syskeys 0 -v 500
```

8、强制停止Monkey测试

```
adb shell ps | awk '/com\.android\.commands\.monkey/ { system("adb shell kill " $2) }'  
```

