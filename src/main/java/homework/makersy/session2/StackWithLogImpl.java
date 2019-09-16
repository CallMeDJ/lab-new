package homework.makersy.session2;

import org.junit.Test;

/**
StackWithLogImpl 实现一个栈，继承StackImpl，并在处理前后打出日志，目的为理解继承这个概念。
 */
public class StackWithLogImpl extends StackImpl{


    public StackWithLogImpl() {
        super();
    }

    public StackWithLogImpl(int capacity) {
        super(capacity);
    }

    @Override
    public int push(int v) {
        logBefore("push");
        int res = super.push(v);
        logAfter("push");
        return res;
    }

    @Override
    public int pop() {
        logBefore("pop");
        int res = super.pop();
        logAfter("pop");
        return res;
    }

    @Override
    public int peek() {
        logBefore("peek");
        int res = super.peek();
        logAfter("peek");
        return res;
    }

    @Override
    public int size() {
        logBefore("size");
        int res = super.size();
        logAfter("size");
        return res;
    }

    @Override
    public boolean isEmpty() {
        logBefore("isEmpty");
        boolean res = super.isEmpty();
        logAfter("isEmpty");
        return res;
    }

    private void logBefore(String action) {
        System.out.println("Before " + this.toString() + " " + action);
    }

    private void logAfter(String action) {
        System.out.println("After " + this.toString() + " " + action);
    }


    @Test
    public static void test(String[] args) {
        Stack stack1 = new StackImpl();
        Stack stack2 = new StackWithLogImpl();
        stack1.push(2);
        stack2.push(2);
    }
}
