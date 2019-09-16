package homework.mrglint.Session2;

import java.util.Arrays;

/**
 * @author luhuancheng
 */
public class StackImpl implements Stack {

    private int[] data;
    private int size;
    private int capacity;

    public StackImpl(int capacity) {
        this.capacity = capacity;
        data = new int[capacity];
        size = 0;
    }

    public StackImpl() {
        this(10);
    }

    @Override
    public boolean push(int value) {
        ensureCapacity(size+1);
        data[size++] = value;
        return true;
    }

    private void ensureCapacity(int newSize) {
        if (newSize > capacity) {
            // 进行2倍扩容
            data = Arrays.copyOf(data, data.length * 2);
            capacity = data.length * 2;
        }
    }

    @Override
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("栈为空");
        }
        int result = data[--size];
        // 为了避免不必要的空间消耗和时间复杂度震荡，当元素个数达到1/4 * data.length时，数组缩容到data.length / 2大小
        shrinkCapacity(size);
        return result;
    }

    private void shrinkCapacity(int newSize) {
        if (newSize == data.length / 4) {
            data = Arrays.copyOf(data, capacity / 2);
            capacity = capacity / 2;
        }
    }

    @Override
    public int peak() {
        if (isEmpty()) {
            throw new IllegalStateException("栈为空");
        }
        return data[size - 1];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        Stack stack = new StackImpl();
        for (int i = 0; i < 11; i++) {
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
