package homework.sweet.session2;

public class StacklTest {
    public static void main(String[] args) {
        StackImpl stack = new StackImpl();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.size());
        System.out.println(stack.isEmpty());
        System.out.println(stack.pop());
        System.out.println(stack.size());
        System.out.println(stack.peak());
        System.out.println(stack.size());

        StackWithLogImpl stackWithLog = new StackWithLogImpl();
        stackWithLog.push(1);
        stackWithLog.push(2);
        stackWithLog.push(3);
        System.out.println(stackWithLog.size());
        System.out.println(stackWithLog.isEmpty());
        System.out.println(stackWithLog.pop());
        System.out.println(stackWithLog.size());
        System.out.println(stackWithLog.peak());
        System.out.println(stackWithLog.size());


    }
}
