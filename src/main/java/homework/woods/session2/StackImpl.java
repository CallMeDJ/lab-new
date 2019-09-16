package homework.woods.session2;

import homework.woods.exception.BizServiceException;

import java.util.Arrays;

/**
 * @author woods
 */
public class StackImpl implements Stack{
    
    private static final int ORIGINAL_LENGTH = 2;
    private int[] stackArray;
    private int size;

    public StackImpl(){
        stackArray = new int[ORIGINAL_LENGTH];
        size = 0;
    }
    
    @Override
    public boolean push(int value) {
        // 栈是否已满，满则扩容
        if (size >= stackArray.length)
        {
            stackArray = Arrays.copyOf(stackArray, stackArray.length + ORIGINAL_LENGTH);
        }
        stackArray[size] = value;
        size ++;
        return true;
    }

    @Override
    public int pop() {
        if (size <= 0)
        {
            throw new BizServiceException("空栈！");
        }
        size --;
        int value = stackArray[size];
        if (size < stackArray.length - ORIGINAL_LENGTH - ORIGINAL_LENGTH)
        {
            stackArray = Arrays.copyOf(stackArray, stackArray.length - ORIGINAL_LENGTH);
        }
        return value;
    }

    @Override
    public int peak() {
        if (size <= 0)
        {
            throw new BizServiceException("空栈！");
        }
        return stackArray[size-1];
    }

    @Override
    public int size() {
        return size;
    }

    public int maxSize(){
        return stackArray.length;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0)
        {
            return true;
        }else {
            return false;
        }
    }
}
