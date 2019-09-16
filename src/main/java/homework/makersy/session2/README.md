### 第二课 总纲

熟练掌握 Java 封装继承多态思想，时间一周

### 学习资料

<https://www.runoob.com/java/java-tutorial.html>

<https://www.cnblogs.com/UncleWang001/articles/9717318.html>

PS:栈是一个有序集合，其中添加和删除元素都是发生在同一端，通常称作发生操作的这一端为顶部，对应的端为底部


### 主要关注内容
数据结构中栈的原理
Java 面向对象

### 作业

#### 目标：实现一个栈，和实现一个带日志输出的栈


```java
public interface Stack {

	/**
	 * 入栈
	 * @return
	 */
	int push();

	/**
	 * 出栈
	 * @return
	 */
	int pop();

	/**
	 * 查看栈顶元素
	 */

	int peak();

	/**
	 * 栈的大小
	 */

	int size();

	/**
	 * 栈是否为空
	 * @return
	 */
	int isEmpty();

}

```
1、StackImpl 实现 Stack 为实现一个栈，要求存储的结构为 int[] 数组，目的为理解封装这个概念。

2、StackWithLogImpl 实现一个栈，继承StackImpl，并在处理前后打出日志，目的为理解继承这个概念。

3、回答出下面这几行代码的表现以及背后的原因，为什么同一个接口会有不同的表现，是怎么实现的。
```java
Stack stack1 = new StackImpl();
Stack stack2 = new StackWithLogImpl();
stack1.push(2);
stack2.push(2);
```

答：
代码结果为：
```java
Before homework.makersy.session2.StackWithLogImpl@15975490 push
After homework.makersy.session2.StackWithLogImpl@15975490 push
```
同一个Stack接口有着不同的表现，这正是因为Java的多态性，由于有多个类实现了Stack接口，这些不同的类所生成的对象内部的属性和方法都是本类所定义的，因此同一个接口可以有着不同的实现。这里的实现方法就是 StackImpl 类实现了 Stack 接口，实现了其中定义的所有方法；然后 StackWithLogImpl 类继承 StackImpl 类，重写了父类的方法，重写方式是：功能的实现直接采用 super 调用父类的方法，然后在调用前和调用后分别打印日志。

