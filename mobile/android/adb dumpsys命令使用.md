# adb dumpsys命令使用

adb shell dumpsys，默认打印出当前系统所有service信息，在后面可加上具体的服务名
 需要列出当前运行的服务，可运行：

```
adb shell dumpsys | findstr DUMP
```

下面简单列下我用到的一些命令...（在编写脚本时需要获取一些状态值供判断用）

##  获取设备分辨率：

```
adb shell dumpsys display | findstr DisplayDeviceInfo
```

DisplayDeviceInfo{"内置屏幕": 1080 x 1920, 55.0 fps, density 480, 464.949 x 468.923 dpi...

## 获取设备电池信息：

```
adb shell dumpsys battery
```

Current Battery Service state:  
AC powered: false  
USB powered: true  
Wireless powered: false  
status: 2 #电池状态  
health: 2  
present: true  
level: 34 #电量  
scale: 100  
voltage: 3848  
current now: -427943  
temperature: 280 #电池温度  
technology: Li-ion

## 获取cpu信息：

```
adb shell dumpsys cpuinfo
```

## 获取内存信息：

```
adb shell dumpsys meminfo
要获取具体应用的内存信息，可加上包名
adb shell dumpsys meminfo PACKAGE_NAME
```

## 获取Activity信息：

```
adb shell dumpsys activity
加上-h可以获取帮助信息
获取当前界面的UI信息，可以用：
adb shell dumpsys activity top
要获取当前界面的Activity：
adb shell dumpsys activity top | findstr ACTIVITY
```

## 获取package信息：

```
adb shell dumpsys package
加上-h可以获取帮助信息
获取某个包的信息：
adb shell dumpsys package PACKAGE_NAME
```

## 获取通知信息：

```
adb shell dumpsys notification
```

```
NotificationRecord(0x44217920: pkg=com.sohu.newsclient useron=0x7f0201b5 / com.tencent.news:drawable/icon   
pri=0 score=0   
contentIntent=PendingIntent{4294d748: PendingIntentRecord{44088e90 com.tencent.news startActivity}}   
deleteIntent=null   
tickerText=null   
contentView=android.widget.RemoteViews@441fc810   
defaults=0x00000001 
flags=0x00000010   
sound=null   
vibrate=null   
led=0x00000000 onMs=0 offMs=0   
extras={     
    android.title=农业部:中国超级稻亩产超1吨     
    android.subText=null     
    android.showChronometer=false     
    android.icon=2130837941     
    android.text=农业部今日通报称,经专家测产,袁隆平领衔培育的中国“超级稻”亩产过千公斤,创造1026.7公斤新纪录。详情>>     
    android.progress=0     
    android.progressMax=0     
    android.showWhen=true     
    android.infoText=null     
    android.progressIndeterminate=false     
    android.scoreModified=false   
}
```

## 获取wifi信息：

```
adb shell dumpsys wifi
可以获取到当前连接的wifi名、搜索到的wifi列表、wifi强度等
```

## 获取电源管理信息：

```
adb shell dumpsys power
```

```
可以获取到是否处于锁屏状态：mWakefulness=Asleep或者mScreenOn=false
亮度值：mScreenBrightness=255
屏幕休眠时间：Screen off timeout: 60000 ms
屏幕分辨率：mDisplayWidth=1440，mDisplayHeight=2560等
```

## 获取电话信息：

```
adb shell dumpsys telephony.registry
可以获取到电话状态，例如
mCallState值为0，表示待机状态、1表示来电未接听状态、2表示电话占线状态
mCallForwarding=false #是否启用呼叫转移
mDataConnectionState=2 #0：无数据连接 1：正在创建数据连接 2：已连接
mDataConnectionPossible=true  #是否有数据连接mDataConnectionApn=   #APN名称
等
```

## 查看设备信息：

### 1.型号

```
adb shell getprop ro.product.model
```

### 2.屏幕分辨率

```
adb shell wm size
```

### 3.屏幕密度

```
adb shell wm density
```

### 4.显示屏参数

```
adb shell dumpsys window displays
```

### 5.android_id

```
adb shell settings get secure android_id
```

### 6.IMEI

在 Android 4.4 及以下版本可通过如下命令获取 IMEI：

```
adb shell dumpsys iphonesubinfo
```

 Android 5.0 及以上版本里这个命令输出为空，得通过其它方式获取了（需要 root 权限）：

```
adb shell
su
service call iphonessubinfo 1
```

输出示例：

```
Result: Parcel(
  0x00000000: 00000000 0000000f 00360038 00390030 '........8.6.0.9.'
  0x00000010: 00350035 00320030 00370037 00350038 '5.5.0.2.7.7.8.5.'
  0x00000020: 00340030 00000031                   '0.4.1...        ')
```

把里面的有效内容提取出来就是 IMEI 了，比如这里的是 `860955027785041`。

### 7.Android 系统版本

```
adb shell getprop ro.build.version.release
```

### 8.IP 地址

```
adb shell ifconfig | grep Mask
```

```
adb shell ifconfig wlan0
```

```
adb shell netcfg
```

### 9.Mac 地址

```
adb shell cat /sys/class/net/wlan0/address
```

### 10.CPU 信息

```
adb shell cat /proc/cpuinfo
```

### 11.内存信息

```
adb shell cat /proc/meminfo
```

### 12.更多硬件与系统属性

设备的更多硬件与系统属性可以通过如下命令查看：

```
adb shell cat /system/build.prop
```

这会输出很多信息，包括前面几个小节提到的「型号」和「Android 系统版本」等。

输出里还包括一些其它有用的信息，它们也可通过 `adb shell getprop <属性名>` 命令单独查看，列举一部分属性如下：

| 属性名                          | 含义                          |
| ------------------------------- | ----------------------------- |
| ro.build.version.sdk            | SDK 版本                      |
| ro.build.version.release        | Android 系统版本              |
| ro.build.version.security_patch | Android 安全补丁程序级别      |
| ro.product.model                | 型号                          |
| ro.product.brand                | 品牌                          |
| ro.product.name                 | 设备名                        |
| ro.product.board                | 处理器型号                    |
| ro.product.cpu.abilist          | CPU 支持的 abi 列表[*节注一*] |
| persist.sys.isUsbOtgEnabled     | 是否支持 OTG                  |
| dalvik.vm.heapsize              | 每个应用程序的内存上限        |
| ro.sf.lcd_density               | 屏幕密度                      |

*节注一：*

一些小厂定制的 ROM 可能修改过 CPU 支持的 abi 列表的属性名，如果用 `ro.product.cpu.abilist` 属性名查找不到，可以这样试试：

```
adb shell cat /system/build.prop | grep ro.product.cpu.abi
```

示例输出：

```
ro.product.cpu.abi=armeabi-v7a
ro.product.cpu.abi2=armeabi
```

