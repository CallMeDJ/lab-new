package homework.jianxing.session2;

import homework.jianxing.utils.StringUtils;

import java.util.EmptyStackException;

/**
 * Simple Stack implementation. Not thread safe.
 *
 * @author logow.whl
 */
public class StackImpl implements Stack {

    private static final int DEFAULT_CAPACITY_SIZE = 16;

    private int[] elements;
    private int index;

    public StackImpl() {
        this(DEFAULT_CAPACITY_SIZE);
    }

    public StackImpl(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must greater than 0");
        }

        elements = new int[capacity];
        index = -1;
    }

    @Override
    public boolean push(int e) {
        ensureCapacity();
        elements[++index] = e;
        return true;
    }

    private void ensureCapacity() {
        if (size() == elements.length) {
            int newCapacity = elements.length * 2;
            int[] arr = new int[newCapacity];
            System.arraycopy(elements, 0, arr, 0, elements.length);
            elements = arr;
        }
    }

    @Override
    public int pop() {
        if (index < 0) {
            throw new EmptyStackException();
        }

        return elements[index--];
    }

    @Override
    public int peak() {
        if (index < 0) {
            throw new EmptyStackException();
        }
        return elements[index];
    }

    @Override
    public int size() {
        return index + 1;
    }

    @Override
    public boolean isEmpty() {
        return index == -1;
    }

    @Override
    public String toString() {
        return StringUtils.toString(elements, 0, size());
    }
}
