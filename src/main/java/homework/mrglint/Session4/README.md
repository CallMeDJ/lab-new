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
    1. 通过数组可以使用O(1)的时间复杂度访问到指定的字符
7、描述为什么 String 是一个不可变的类，以及是怎么实现不可变的。
    1. 为什么是一个不可变类？因为方法中都是重新构造一个String对象
    2. 怎么实现不可变的？在String类中维护着一个char类型的数组，而且方法内部的操作不会直接修改这个数组，
    而是通过拷贝数组内容到另一个数组的方式来实现
### 扩展阅读

StringBuilder、StingBuffer 源码。

字符编码以及字符集。<https://blog.csdn.net/nlznlz/article/details/80950596>



