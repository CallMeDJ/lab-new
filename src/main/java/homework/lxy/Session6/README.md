### 第六课 总纲

熟练掌握 TCP 原理

### 学习资料

<https://blog.csdn.net/qq_31869107/article/details/81327494>   TCP握手与挥手详解

<https://blog.csdn.net/yao5hed/article/details/81046945> TCP 滑动窗口

<https://blog.csdn.net/m0_37962600/article/details/79993310> TCP 中的拥塞控制算法

<https://blog.csdn.net/occupy8/article/details/48174307> TCP 中的 Nagel 算法

<https://www.cnblogs.com/steven520213/p/8005258.html> TCP 与 UDP 的区别与联系

### 主要关注内容
TCP 基础链接握手原理。
TCP 滑动窗口以及拥塞控制算法。
TCP 中 Nagel 算法。


### 作业

#### 目标：

请不要太过关注 TCP 协议各个字节各个头部的含义，比如第2个字节到第8个字节代表什么，这里请暂时不要关注这些细节。

主要注重 TCP 协议的流程，算法。


1、画出属于自己的握手原理图，描述为什么什么是 TIME_WAIT，有什么策略。

2、用伪代码写出 Nagel 算法，描述清楚流程即可。

3、描述滑动窗口、拥塞控制 的基础机制，以及这样设计能带来什么好处。

4、描述清楚 TCP 与 UDP 的区别与联系，设计一个 TCP 与 UDP 混合使用的技术方案。

5、实现简单的 TCP 三次握手四次挥手示例代码  见  TCPClient TCPServer  TCPStatus。

