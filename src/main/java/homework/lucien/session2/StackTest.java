package homework.lucien.session2;

public class StackTest {

	public static void main(String[] args) {
		// 多态的原理
		/**
		 * 代码表现:
		 * 1、 Stack 类型的引用 stack1 指向 StackImpl 类型的实例; 而引用 stack2指向StackWithLogImpl 类型的实例
		 * 并将其指向一个新建的 StackWithLogImpl 类型的实例; 通过 Stack 类型的引用 stack1 调用 StackImpl 类型实例的
		 * push 方法往 stack1 中添加元素 "3", 控制台无输出; 通过 Stack 类型的引用 stack2 调用 StackWithLogImpl
		 * 类型实例的 push 方法往 stack2 中添加元素 "3", 控制台输入 push 方法的 log; 这是 java 多态的一个典型场景.
		 * 2、stack1 和 stack2 虽然同为 Stack类型的引用, 但是其指向的实例类型并不相同, 所以执行的具体方法也不相同.
		 */
		Stack stack1 = new StackImpl();
		Stack stack2 = new StackWithLogImpl();
		stack1.push(3);
		stack2.push(2);
		stack2.push(3);
		stack2.pop();
	}
}
