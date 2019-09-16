package homework.stark.session2;

/**
 * @author Stark
 * @date 2019-07-27
 * <p>
 * 1、使用int[] 存储
 * 2、如果栈满了，扩容2倍。
 * 3、如果栈为空 pop 和 peak 抛异常。
 */
public class StackImpl implements Stack {
    /**
     * Stack's Size
     */
    private int size;

    /**
     * Stack's Elements
     */
    private int[] stack;

    public StackImpl(int capacity) {
        stack = new int[capacity];
        size = 0;
    }

    public StackImpl() {
        this(16);
    }


    private int[] biggerArray(int[] originArray, int growSize) {
        if (growSize <= 0) {
            throw new IllegalArgumentException("Grow size should be positive.");
        } else {
            int[] newArray = new int[originArray.length + growSize];
            System.arraycopy(originArray, 0, newArray, 0, originArray.length);
            return newArray;
        }
    }

    /**
     * Stack's Push Method
     *
     * @param value
     * @return
     */
    @Override
    public boolean push(int value) {
        size++;

        //Stack is full
        if (this.size > this.stack.length) {
            this.stack = this.biggerArray(this.stack, this.stack.length);
        }

        this.stack[this.size - 1] = value;
        return true;
    }

    @Override
    public int pop() {

        if (isEmpty()) {
            throw new RuntimeException("Stack is empty, no more element to pop.");
        }

        return this.stack[--this.size];
    }

    @Override
    public int peak() {

        if (isEmpty()) {
            throw new RuntimeException("Stack is empty, no element can be peaked");
        }

        return this.stack[this.size - 1];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
}
