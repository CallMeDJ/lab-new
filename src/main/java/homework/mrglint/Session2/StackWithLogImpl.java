package homework.mrglint.Session2;

public class StackWithLogImpl extends StackImpl {
    @Override
    public boolean push(int value) {
        System.out.println("before push");
        boolean result = super.push(value);
        System.out.println("after push");
        return result;
    }

    @Override
    public int pop() {
        System.out.println("before pop");
        int result = super.pop();
        System.out.println("after pop");
        return result;
    }

    public static void main(String[] args) {
        Stack stack1 = new StackImpl();
        Stack stack2 = new StackWithLogImpl();
        stack1.push(2);
        stack2.push(2);
    }
}
