package homework.imle0n.session2;

/**
 * @author iMLe0n
 * @version 1.0.0
 * @date 19-7-21 下午11:05
 */
public class Test {
    public static void main(String[] args) {
        Stack stack1 = new StackImpl();
        Stack stack2 = new StackWithLogImpl();
        stack1.push(2);
        stack2.push(2);
        stack2.pop();
    }
}
