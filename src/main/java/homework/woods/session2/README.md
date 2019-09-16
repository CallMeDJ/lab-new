### 作业3描述
继承和多态是java的特性，类可以实现多个接口，实现类需要实现接口中的所有方法。
父类的引用可以指向子类的实例。
```java
Stack stack1 = new StackImpl();
Stack stack2 = new StackWithLogImpl();

stack1.push(1);
WoodsPrinter.println("---------------------------------------");
stack2.push(2);
```
案例中，父类引用stack1, stack2 指向子类的实例，编译时会检查父类中是否有push()方法， 
如果没有则报错；执行时，执行的是子类实例的方法。所以上例中，stack1.push(1)执行的是
StackImpl的push方法，stack2.push(2)执行的是StackWithLogImpl的push方法。