package homework.imle0n.session2;

public class StackWithLogImpl extends StackImpl {

    @Override
    public boolean push(int value) {
        logB4Method("push");
        boolean flag = super.push(value);
        logAftMethod("push");
        return flag;
    }

    @Override
    public int pop() {
        logB4Method("pop");
        int i = super.pop();
        logAftMethod("pop");
        return i;
    }

    @Override
    public int peak() {
        logB4Method("peak");
        int i = super.peak();
        logAftMethod("peak");
        return i;
    }

    private void logB4Method(String methodName) {
        System.out.println("before  --------->  " + methodName);
    }

    private void logAftMethod(String methodName) {
        System.out.println("after  --------->  " + methodName);
    }
}
