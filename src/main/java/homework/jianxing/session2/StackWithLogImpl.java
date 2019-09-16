package homework.jianxing.session2;

/**
 * Enhanced {@link StackImpl} with operation log
 *
 * @author logow.whl
 */
public class StackWithLogImpl extends StackImpl {

    public StackWithLogImpl() {}

    public StackWithLogImpl(int capacity) {
        super(capacity);
    }

    @Override
    public boolean push(int e) {
        logBefore("push");
        boolean ret = super.push(e);
        logAfter("push");
        return ret;
    }

    @Override
    public int pop() {
        logBefore("pop");
        int ret = super.pop();
        logAfter("pop");
        return ret;
    }

    @Override
    public int peak() {
        logBefore("peak");
        int ret = super.peak();
        logAfter("peak");
        return ret;
    }

    private void logBefore(String op) {
        System.out.println("Before " + op + ": " + this.toString());
    }

    private void logAfter(String op) {
        System.out.println("After " + op + ": " + this.toString());
    }
}
