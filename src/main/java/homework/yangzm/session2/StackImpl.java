package homework.yangzm.session2;

/**
 * 自定义基础栈实现类
 */
public class StackImpl implements Stack {

    private int[] stackArry;
    private int capacity;
    private int initialCapacity = 10;// 初始容量
    private int peekPosition = 0;// 栈顶位置

    public StackImpl() {
        stackArry = new int[initialCapacity];
        capacity = initialCapacity;
    }

    public StackImpl(int size) throws IllegalArgumentException {
        if(size <= 0) {
            throw new IllegalArgumentException("初始化栈的大小必须大于0");
        }
        stackArry = new int[size];
        capacity = size;
    }

    @Override
    public boolean push(int value) {
        if (peekPosition >= capacity) {
            //如果栈满了，扩容2倍。
            int oldCapacity = stackArry.length;
            int newCapacity = oldCapacity + (capacity << 1);
            int[] copy = new int[newCapacity];
            System.arraycopy(stackArry, 0, copy, 0, oldCapacity);
            stackArry = copy;
            capacity = newCapacity;
        }
        stackArry[peekPosition++] = value;
        return true;
    }

    @Override
    public int pop() throws Exception {
        if (peekPosition <= 0) {
            throw new IllegalAccessException("stack is empty!");
        }
        int data = stackArry[--peekPosition];
        return data;
    }

    @Override
    public int peak() throws Exception {
        if (peekPosition <= 0) {
            throw new IllegalAccessException("stack is empty!");
        }
        int data = stackArry[peekPosition - 1];
        return data;
    }

    @Override
    public int size() {
        return stackArry.length;
    }

    @Override
    public boolean isEmpty() {
        if (peekPosition == 0) {
            return true;
        }
        return false;
    }
}
