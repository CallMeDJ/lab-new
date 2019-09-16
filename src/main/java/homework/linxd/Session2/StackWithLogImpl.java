package homework.linxd.Session2;

public class StackWithLogImpl extends StackImpl {
    private Object[] stack = new Object[16];
    private int upIndex = 0;

    public StackWithLogImpl() {
    }

    public StackWithLogImpl(int size) {
        super(size);

    }

    @Override
    public int push(Object i) {
        System.out.println("入栈开始。。。。。。");
        int result = super.push(i);
        System.out.println("入栈完成。。。。。。");
        return result;


    }

    @Override
    public Object pop() {
        System.out.println("出栈开始。。。。。。");
        Object result = super.pop();
        System.out.println("出栈完成。。。。。。");
        return result;
    }


}
