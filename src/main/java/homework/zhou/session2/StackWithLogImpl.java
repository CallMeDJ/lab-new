package homework.zhou.session2;

public class StackWithLogImpl extends StackImpl {

    public StackWithLogImpl() {
        super();
    }

    public StackWithLogImpl(int size) {
        super(size);
    }

    @Override
    public boolean push(int value) {
        System.out.println("start push");
        boolean result = super.push(value);
        System.out.println("end push");
        return result;
    }

    @Override
    public int pop() {
        System.out.println("start pop");
        int result = super.pop();
        System.out.println("end pop");
        return result;
    }

    @Override
    public int peak() {
        System.out.println("start peak");
        int result = super.peak();
        System.out.println("end peak");
        return result;
    }

    @Override
    public int size() {
        System.out.println("start size");
        int result = super.size();
        System.out.println("end size");
        return result;
    }

    @Override
    public boolean isEmpty() {
        System.out.println("start isEmpty");
        boolean result = super.isEmpty();
        System.out.println("end isEmpty");
        return result;
    }

}
