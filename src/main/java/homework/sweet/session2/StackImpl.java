package homework.sweet.session2;

import java.util.EmptyStackException;

public class StackImpl implements Stack{
    private static final int DEFAULT_CAPACIT = 10;
    protected int[] elementData;
    protected int capacity;
    protected int index;

    public StackImpl() {
        this(DEFAULT_CAPACIT);
    }

    public StackImpl(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("initialCapacity must > 0");
        }
        this.elementData = new int[initialCapacity];
        this.capacity = initialCapacity;
        this.index = -1;
    }

    @Override
    public boolean push(int value) {

        if (index + 1 == capacity) {
            capacity <<= 1;
            int[] newArray = new int[capacity];
            System.arraycopy(elementData, 0, newArray, 0, elementData.length);
            elementData = newArray;
        }
        //赋值
        elementData[++index] = value;
        return true;

    }

    @Override
    public int pop() {
        if (index < 0) {
            throw new EmptyStackException();
        } else {
            return elementData[index--];
        }
    }

    @Override
    public int peak() {
        if (index < 0) {
            throw new EmptyStackException();
        } else {
            return elementData[index];
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

}
