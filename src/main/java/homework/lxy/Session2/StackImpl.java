package homework.lxy.Session2;

import homework.lxy.utils.PrintUtils;
import org.junit.Test;

public class StackImpl implements Stack{
    private static final int DEFAULT_CAPACITY = 4;

    private int[] item;

    private int capacity;

    private int size;

    public StackImpl(){
        this(DEFAULT_CAPACITY);
    }

    StackImpl(int capacity){
        if(capacity <= 0){
            throw new IllegalArgumentException("capacity can not be zero or less than zero");
        }
        this.capacity = capacity;
        item = new int[capacity];
    }

    @Override
    public int push(int value) {
        if(size == capacity){
            resize();
        }
        item[size] = value;
        size++;
        return value;
    }

    @Override
    public int pop() {
        if(size == 0){
            throw new StackEmptyException("the stack is empty");
        }
        int topValue = item[size-1];
        item[size-1] = 0;
        size--;
        return topValue;
    }

    @Override
    public int peak() {
        if(size == 0){
            throw new StackEmptyException("the stack is empty");
        }
        return item[size-1];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        capacity *= 2;
        int[] newStack = new int[capacity];
        for(int i = 0; i< size; i++){
            newStack[i] = item[i];
        }
        this.item = newStack;
    }


    @Test
    public void test() {
        Stack stack = new StackImpl();
        for (int i = 0; i < 9; i++) {
            stack.push(i);
        }
        //出栈
        System.out.println(stack.pop());
        //获取栈顶元素
        System.out.println(stack.peak());

        System.out.println(stack.size());
        System.out.println(stack.isEmpty());
    }

}
class StackEmptyException extends RuntimeException {
    StackEmptyException(String message){
        super(message);
    }
}


