package homework.tonemy.session2;
/*
 * @author tonemy
 *  Date: 2019-7-19 
 *  功能：	1、使用int[] 存储
 *  			2、如果栈满了，扩容2倍。
 *  			3、如果栈为空 pop 和 peak 抛异常。
 *  作业：
 *  1、StackImpl 实现 Stack 为实现一个栈，要求存储的结构为 int[] 数组，目的为理解封装这个概念。
 *  	封装理解： StackImpl  包括了栈的操作方法和数据，并进行捆绑，内部实现方法时需要改变相应的数据，
 *  			向外只提供接口的方法。
 *  2、StackWithLogImpl 实现一个栈，继承StackImpl，并在处理前后打出日志，目的为理解继承这个概念。
 *  	继承理解： 描述了两个类的关系，被提供信息的称为子类，提供信息的称为父类。
 *  			好处：子类能自动继承父类的方法；创建子类对象时，无需创建父类对象
 *  			坏处： 子类与父类的耦合性很高，子类依赖父类的实现；子类不能改变父类的接口；
 *  3、回答出下面这几行代码的表现以及背后的原因，为什么同一个接口会有不同的表现，是怎么实现的
 *  		这体现了多态中的一种表现形式---重写（override），重写描述了不同类的关系---实现或继承，
 *  	虽然StackImpl（实现) 和StackWithLogImpl（继承）的同一个接口，但在Main函数中，相对于接口来说，
 *  	引用的是子类对象，这样相同的引用，调用同样的方法，会根据子类对象的不同而表现出不同的行为；
 * 		也就是说，我在StackImpl中实现了接口的功能，但在Main中调用的是StackImpl类，而表现出这个类实现
 *  	的方法的行为；同理，StackWithLogImpl中添加了打印函数，对父类进行重写，但在Main中调用的是
 *  	StackWithLogImpl类，而表现出这个类实现 的方法的行为，从而有了日志的输出。
 *
  */
public class Main {
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		Stack stack1 = new StackImpl();
		Stack stack2 = new StackWithLogImpl();
		stack1.push(6);
		stack2.push(6);
	}
}

