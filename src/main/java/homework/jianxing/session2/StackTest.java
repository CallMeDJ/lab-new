package homework.jianxing.session2;

/**
 * Stack implementation test
 */
public class StackTest {

    public static void main(String[] args) {
        // 理解多态的原理
        Stack stack1 = new StackImpl(1);
        Stack stack2 = new StackWithLogImpl(1);
        stack1.push(3);
        stack2.push(2);
        stack2.push(3);
        stack2.pop();
    }
}
