
6、描述为什么在 JDK 中使用数组而不是链表来实现字符串。
a. 数组支持随机访问，根据下标随机访问的时间复杂度为 O(1)
b. 链表的优势表现在插入删除

7、描述为什么 String 是一个不可变的类，以及是怎么实现不可变的。
a. String 被final修饰，使得其无法被继承
b. 储存String 的字符数组value被final修饰，则其地址不可变
b. 储存String 的字符数组value被 private修饰， 且未提供setter getter，外部无法修改String
c. String 的内部设计中，各方法不直接操作value，而是copy一个新的字符数组，以改变value的指向来达到修改的目的




