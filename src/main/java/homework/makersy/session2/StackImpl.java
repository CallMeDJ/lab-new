package homework.makersy.session2;


import org.junit.Test;

import java.util.EmptyStackException;

/**
 * StackImpl 实现 Stack 为实现一个栈，要求存储的结构为 int[] 数组，目的为理解封装这个概念。
 */
public class StackImpl implements Stack{

    private static final int DEFAULT_CAPACITY = 32;

    private int[] elements;
    private int index;
    private int capacity;

    public StackImpl() {
        this(DEFAULT_CAPACITY);
    }

    public StackImpl(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must greater than 0");
        }
        this.capacity = capacity;
        elements = new int[capacity];
        index = -1;
    }

    @Override
    public int push(int v) {
        //扩容
        if (index + 1 == capacity) {
            capacity <<= 1;
            int[] newArray = new int[capacity];
            System.arraycopy(elements, 0, newArray, 0, elements.length);
            elements = newArray;
        }
        //赋值
        elements[++index] = v;
        return v;
    }

    @Override
    public int pop() {
        if (index < 0) {
            throw new EmptyStackException();
        } else {
            return elements[index--];
        }
    }

    @Override
    public int peek() {
        if (index < 0) {
            throw new EmptyStackException();
        } else {
            return elements[index];
        }
    }

    @Override
    public int size() {
        return index + 1;
    }

    @Override
    public boolean isEmpty() {
        return index == -1;
    }

    @Test
    public static void test(String[] args) {
        Stack stack = new StackImpl();
        for (int i = 0; i < 64; i++) {
            stack.push(i);
        }
    }
}
