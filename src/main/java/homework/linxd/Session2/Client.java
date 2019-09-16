package homework.linxd.Session2;

/**
 * Created with IntelliJ IDEA.
 *
 * @author linxd
 * @date 2019/7/18 1:24
 * Description: 测试
 */
public class Client {
    public static void main(String[] args) {
        Stack stack1 = new StackImpl();
        /*System.out.println(stack1.push(2));
        System.out.println(stack1.push("ssss"));
        System.out.println("size:" + stack1.size());
        System.out.println(stack1.pop());
        System.out.println("size:" + stack1.size());*/
        Stack stack2 = new StackWithLogImpl();
        /*System.out.println(stack2.push(2));
        System.out.println(stack2.push(5));
        System.out.println("size:" + stack2.size());
        System.out.println(stack2.pop());
        System.out.println("size:" + stack2.size());*/
        stack1.push(2);
        stack2.push(2);
        /*
        显示结果不同的原因：多态
        我的理解：
            当我们使用父类引用指向字类对象时，会发生多态，此时，父类引用调用的方法如果是子类重写父类的方法，
        则调用重写的方法， 对象调用方法时，是this.方法名调用，this指当前对象，这个对象实际是哪个子类的对象，
        他就调用哪个子类的重写的方法。
         */
    }
}
