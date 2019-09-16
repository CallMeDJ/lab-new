### 第三课 总纲

熟练掌握各种 Java Lambda 技巧

### 学习资料

<https://blog.csdn.net/qq_29848473/article/details/79554472>

<https://www.cnblogs.com/anakin/p/7742779.html>


### 主要关注内容

1、Lambda 对于原来匿名函数的支持，比如 Thread 。

2、Lambda 对于集合的遍历的支持。

3、JDK8 中的 Function、Consumer、Predicate 的使用

### 作业

#### 目标：

实现对于西瓜的流水线处理，最终产出合格的西瓜。


1、把两种西瓜使用 stream 遍历，然后 Function 转换为同一种西瓜。

2、使用 Predicate 将西瓜中质量小于0和质量大于50的瓜挑出来，丢掉。

3、使用 Consumer 创建出5个检查人员，每个检查人员都会检查每个西瓜，使用 System.out.println("X 号检察员检查第 N 个西瓜，质量为 Y 完毕")。该过程使用多线程完成。
也就是说我们会创建出 50 个线程，待所有检查人员检查完成后（使用 CountDownlatch 来确认所有线程都执行完成了），观察所有的检验报告。

4、把现在还剩下的西瓜质量按顺序打印出来，格式 [1，3，4，5，6]。
   
5、使用 reduce 计算一下，最终这批西瓜总计有多重，并打印出来
   
详细需要实现的内容请参照 WatermelonPipeline.java




