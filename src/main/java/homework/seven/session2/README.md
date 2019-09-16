###### 回答出下面这几行代码的表现以及背后的原因，为什么同一个接口会有不同的表现，是怎么实现的。

1. Stack stack1 = new StackImpl();
2. Stack stack2 = new StackWithLogImpl();
3. stack1.push(2);
4. stack2.push(2);

###### 答:

代码表现: 
1. 定义一个 Stack 类型的引用 stack1 并将其指向一个新建的 StackImpl 类型的实例;
1. 定义一个 Stack 类型的引用 stack1 并将其指向一个新建的 StackWithLogImpl 类型的实例;
3. 通过 Stack 类型的引用 stack1 调用 StackImpl 类型实例的 push 方法往 stack1 中添加元素 "2", 控制台无输出;
4. 通过 Stack 类型的引用 stack2 调用 StackWithLogImpl 类型实例的 push 方法往 stack2 中添加元素 "2", 控制台输入 push 方法的 log;

这是 java 多态的一个典型场景, 子类的实例可以赋值给父类的引用, 通过调用在父类中定义的方法, 可以执行子类的方法实现. 
stack1 和 stack2 虽然同为 Stack 类型的引用, 但是其指向的实例类型并不相同, 所以执行的具体方法也不相同.
