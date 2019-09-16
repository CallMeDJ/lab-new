### 第四课 总纲

熟练掌握 JDK 基础类 String、字符串匹配算法、数字转字符串、链表翻转算法

### 学习资料

<https://blog.csdn.net/thousa_ho/article/details/72842029> 字符串匹配算法，自己先思考，再点链接看


<https://blog.csdn.net/lwkrsa/article/details/82015364>   单链表翻转


JDK 源码 String 类

### 主要关注内容

1、String 类的实现。

2、字符串匹配算法原理以及实现。

### 作业

#### 目标：
1、实现 StringInterface 接口，实现一个容器为数组的 ArrayString。

2、实现 StringInterface 接口，实现一个容器为链表的 LinedString。

3、实现字符串匹配算法 indexOf。

4、实现数字转字符串的算法。

5、实现字符串首尾翻转字符串算法。

6、描述为什么在 JDK 中使用数组而不是链表来实现字符串。

字符串是程序中使用最广泛的类型，因此它的性能和内存占用对系统影响特别大。相对链表而言，数组占用的空间更少，且随机访问比链表快。
由于字符串是不可变的，因此不需要支持删除、插入等修改操作，链表在此方面的优势荡然无存。

7、描述为什么 String 是一个不可变的类，以及是怎么实现不可变的。

String 作为 Java 的核心类型，无处不在，它的稳定与安全直接决定系统的稳定与安全性。由于不可变，它天生就是线程安全的。
为了实现不可变，String 声明成 final ，创建方法都会复制一份数据，任何修改方法都返回新的字符串对象。

### 扩展阅读

StringBuilder、StingBuffer 源码。

字符编码以及字符集。<https://blog.csdn.net/nlznlz/article/details/80950596>



