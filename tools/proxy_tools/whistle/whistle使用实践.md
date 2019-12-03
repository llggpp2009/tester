

# whistle使用实践--以windows为例

## 一、安装启动

### 1.安装Node

 访问https://nodejs.org/en/ ，按提示安装即可。

### 2.安装whistle

`$ npm install -g whistle`

### 3.启动whistle

####  a.查看帮助信息 

`$ w2 help `  

#### b.启动whistle，登录时指定用户名和密码

`$ w2 start -n username -w password`  

![启动whistle](https://github.com/llggpp2009/tester/blob/master/markdown_picture/whistle/whistle_start.png?raw=true)

#### c.停止whistle

`$ w2 stop` 停止whistle

### 4.配置代理--以移动端android设备为例

**此处以vivo X9手机为例**：设置-》WLAN-》手动代理

<img src="https://github.com/llggpp2009/tester/blob/master/markdown_picture/whistle/settings.png?raw=true" alt="设置" style="zoom:20%;" /><img src="https://github.com/llggpp2009/tester/blob/master/markdown_picture/whistle/WLAN.png?raw=true" alt="WLAN" style="zoom:20%;" /><img src="https://github.com/llggpp2009/tester/blob/master/markdown_picture/whistle/configureProxy_manual.png?raw=true" alt="手动代理" style="zoom:20%;"/>

## 二、使用--以移动端android设备为例

使用的前提条件：1. 启动whistle  （详见启动whistle章节）

​					           2.配置完代理 （详见配置代理章节）

### 1.利用chrome浏览器，访问web：

​	a.http://127.0.0.1:8899/
​    b.http://192.168.248.1:8899/
​    c.http://192.168.239.1:8899/
​    d.http://10.13.94.137:8899/

### 2.登录web

  <img src="https://github.com/llggpp2009/tester/blob/master/markdown_picture/whistle/whistle_login.png?raw=true" style="zoom: 80%;" />



### 3.配置https

#### a.下载证书

左侧菜单栏，选择Network。上面菜单栏，点击HTTPS。

<figure class="half">
	<img src="https://github.com/llggpp2009/tester/blob/master/markdown_picture/whistle/HTTPS_configuration.png?raw=true" style="zoom:50%;" />
	<img src="https://github.com/llggpp2009/tester/blob/master/markdown_picture/whistle/https_enable.png?raw=true" style="zoom:50%;" />
</figure>

第一步：手机端通过扫二维码安装;PC端通过点击上面的Download RootCA下载whistle的私有证书。

第二步：开启Https拦截功能，只有勾上上面的两个checkbox及装好根证书，whistle才能看到HTTPS、Websocket的请求。

#### b.手机安装证书

如下有两种方式：

1.直接用手机上的工具（例如360浏览器）扫描上图中的二维码进行安装。

2.手机浏览器网址中输入rootca.pro，下载证书。

#### c.验证证书是否安装成功

**此处以vivo X9手机为例**：设置-》更多设置-》安全-》受信任的凭据-》用户

<img src="https://github.com/llggpp2009/tester/blob/master/markdown_picture/whistle/settings2.png?raw=true" style="zoom: 20%;"/><img src="https://github.com/llggpp2009/tester/blob/master/markdown_picture/whistle/security.png?raw=true" style="zoom: 20%;"/><img src="https://github.com/llggpp2009/tester/blob/master/markdown_picture/whistle/credential_storage.png?raw=true" alt="凭据存储" style="zoom:20%;" />

<img src="https://github.com/llggpp2009/tester/blob/master/markdown_picture/whistle/trusted_credentials%20.png?raw=true" align='left' style="zoom:20%;" />

在用户下面查看到whistle的证书，即确认证书已安装成功。

### 4.功能使用

#### a.顶部菜单栏的功能介绍

![](https://github.com/llggpp2009/tester/blob/master/markdown_picture/whistle/whistle_top_menu.png?raw=true)

**Import**:导入请求的全部内容的文件

**Export**:将请求导出并存为文件

**Clear**：清除当前会话内容

**Replay**:请求重放功能

**Compose**：用来重发请求、构造请求，可以自定义请求的url、请求方法、请求头、请求内容。

**Record**:录制开始与暂停

**Filter**:设置过滤条件

**Files**:可以存放最大20m的文本或二进制内容（Values只能存放比较小的文本内容）

**Weinre**:即web inspector remote（远程web检查器）的缩写，是移动端调试工具。它可以在PC端直接调试运行在移动设备上的远程页面，在PC端可以即时修改目标网页的HTML/CSS/Javascript，调试过程可实时显示移动设备上页面的预览效果，并同步显示设备页面的错误和警告信息，可以查看网络资源的信息，不支持断点调试。

相关介绍：https://www.jianshu.com/p/68185534eff9

**HTTPS**:用来下载根证书，开启HTTPS拦截功能。

**Help**:相关帮助信息

#### b.左侧菜单栏的功能介绍

<img src="https://github.com/llggpp2009/tester/blob/master/markdown_picture/whistle/whistle_left_menu.png?raw=true" align='left' />  **Network**:查看请求响应的详细信息

  **Rules**:规则配置

  **Values**:配置`key-value`的数据，在Rules里面配置可以通过`{key}`获取，如：`www.ifeng.com file://{key}`

  **Plugins**:显示所有已安装的插件列表，开启关闭插件功能，打开插件的管理页面等。

   目前插件列表： https://github.com/whistle-plugins





#### C.目前测试中经常用到的功能

##### 1.抓包及重放

##### 2.设置hosts

##### 3.修改请求与响应

##### 4.移动端调试weinre

## 三、总结

目前是第三方代理工具中的佼佼者。



