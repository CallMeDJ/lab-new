package homework.imle0n.session2;

import java.util.Arrays;
import java.util.EmptyStackException;

public class StackImpl implements Stack {
    /**
     * 定义栈的最大容量
     */
    public static final int MAX_SIZE = 1024;

    private int[] data;
    /**
     * 初始化栈容量
     */
    private static int initCapacity = 10;
    /**
     * 栈大小
     */
    private int size;
    /**
     * 栈指针
     */
    private int top;

    public StackImpl() {
        this(initCapacity);
    }

    /**
     * 构造指定大小的栈
     *
     * @param initSize 初始化大小
     */
    public StackImpl(int initSize) {
        if (initSize > 0) {
            data = new int[initSize];
            size = 0;
            top = -1;
        } else {
            throw new IllegalArgumentException("初始化栈容量不能为0以及负数");
        }
    }

    @Override
    public boolean push(int value) {
        if (data.length == size) {
            //扩容
            stackCapacity(size);
        }
        data[++top] = value;
        size++;
        return true;
    }

    @Override
    public int pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        size--;
        return data[top--];
    }

    @Override
    public int peak() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return data[top];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    private void stackCapacity(int size) {
        int capacity =  size*2;
        if (capacity > MAX_SIZE) {
            throw new IllegalArgumentException("扩容超过最大容量");
        }
        data = Arrays.copyOf(data, capacity);
    }
}
