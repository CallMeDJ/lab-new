package homework.ashuoa.session2;

/**
 * StackWithLog
 *
 * @author ashuoa
 */
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
}
