package homework.linxd.Session2;

public class StackImpl implements Stack {
    private Object[] stack = new Object[16];
    private int upIndex = 0;

    public StackImpl() {
    }

    public StackImpl(int size) {
        if (size <= 0) {
            stack = new Object[0];
        } else {
            stack = new Object[size];
        }

    }

    @Override
    public int push(Object i) {
        if (upIndex >= stack.length) {
            //栈满
            return -1;
        }
        stack[upIndex] = i;
        upIndex++;
        return 0;


    }

    @Override
    public Object pop() {
        if (this.isEmpty() != 0) {
            //栈不为空
            //初始化栈顶的元素并修改栈顶坐标
            Object el = stack[upIndex - 1];
            stack[upIndex - 1] = 0;
            upIndex--;
            return el;
        }

        return -1;
    }

    @Override
    public Object peak() {
        if (this.isEmpty() != 0) {
            //栈不为空
            return stack[upIndex - 1];
        }

        return -1;
    }

    @Override
    public int size() {
        return stack.length >= upIndex ? upIndex : stack.length;
    }

    @Override
    public int isEmpty() {
        int i = upIndex;
        return this.upIndex == 0 ? 0 : -1;
    }
}
