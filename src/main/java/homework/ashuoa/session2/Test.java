package homework.ashuoa.session2;

/**
 * Test
 *
 * @author ashuoa
 */
public class Test {
    public static void main(String[] args) {
        Stack stack1 = new StackImpl();
        Stack stack2 = new StackWithLogImpl();
        stack1.push(2);
        stack2.push(2);
        System.out.println("stack2 size: " + stack2.size());
    }
}