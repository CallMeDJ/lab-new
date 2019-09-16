package homework.sweet.session2;

public class StackWithLogImpl extends StackImpl{

    public StackWithLogImpl() {
        super();
    }

    public StackWithLogImpl(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public boolean push(int value) {
        logBefore("push");
        boolean flag =  super.push(value);
        logAfter("push");
        return flag;
    }

    @Override
    public int pop() {
        logBefore("pop");
        int res = super.pop();
        logAfter("pop");
        return  res;
    }

    @Override
    public int peak() {
        logBefore("peak");
        int res = super.peak();
        logAfter("peak");
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
        boolean flag = super.isEmpty();
        logAfter("isEmpty");
        return flag;
    }

    private void logBefore(String action) {
        System.out.println("Before " + this.toString() + " " + action);
    }

    private void logAfter(String action) {
        System.out.println("After " + this.toString() + " " + action);
    }

}
