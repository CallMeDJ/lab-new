package homework.stark.session2;

/**
 * @author Stark
 * @date 2019-07-27
 * <p>
 * 带日志输出的栈：在所有操作的前面打出日志，以push为例子，开始push之前
 * System.out.println("start push");
 * push 结束的时候
 * System.out.println("end push");
 */
public class StackWithLogImpl extends StackImpl {
    private void printState(String state, String methodName) {
        System.out.println(String.format("%s %s", state, methodName));
    }


    @Override
    public boolean push(int value) {
        printState("start", "push");
        boolean result = super.push(value);
        printState("end", "push");
        return result;
    }

    @Override
    public int pop() {
        printState("start", "pop");
        int result = super.pop();
        printState("end", "pop");
        return result;
    }

    @Override
    public int peak() {
        printState("start", "peak");
        int result = super.peak();
        printState("end", "peak");
        return result;
    }

    @Override
    public int size() {
        printState("start", "size");
        int result = super.size();
        printState("end", "size");
        return result;
    }

    @Override
    public boolean isEmpty() {
        printState("start", "isEmpty");
        boolean result = super.isEmpty();
        printState("end", "isEmpty");
        return result;
    }
}
