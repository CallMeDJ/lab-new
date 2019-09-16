### 第七课 总纲

熟练掌握 JVM 原理

### 学习资料

https://blog.csdn.net/zhang_jiayuan/article/details/82083163
https://www.cnblogs.com/rinack/p/9888692.html



### 主要关注内容

JVM内存分布
Class加载机制
JVM运行时
GC算法

### 作业:

实现一个JVM 的 gc 流程，JVM 为虚拟机类，JMemory 为虚拟的虚拟机内的内存定义模型。

/**
 * 这里自己实现 gc 算法，比如什么时候应该进行代的晋升，什么时候进行young gc，什么时候进行full gc，什么时候应该crash。
 * 核心逻辑：
 * 新对象优先放 Eden 区，然后经过一定轮数晋升到 Survivor 区域。Survivor 区域分为两个区域循环利用。
 * 对象 gc 经过一定轮数后，从 Survivor 区晋升到 Old 区。
 * 如果 old 区放不下了，进行full gc。
 * 如果 永久代放不下了，进行full gc。
 * PS : 本作业不要求多线程回收，每次回收就 stop the world 好了，暂时不要太纠结这块的性能
 *
 */

#### 目标：

掌握JVM 的内存分布，以及基础的 GC 算法。