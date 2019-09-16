package homework.zhou.session2;

//        1、使用int[] 存储
//
//        2、如果栈满了，扩容2倍。
//
//        3、如果栈为空 pop 和 peak 抛异常。
//
//        ps: 带日志输出的栈：在所有操作的前面打出日志，以push为例子，开始push之前
//        System.out.println("start push");
//        push 结束的时候
//        System.out.println("end push");

import java.util.Arrays;
import java.util.EmptyStackException;

public class StackImpl implements Stack {
    public static void main(String[] args) {
        Stack stack1 = new StackImpl();
        Stack stack2 = new StackWithLogImpl();
        stack1.push(1);
        stack1.push(2);
        stack1.pop();
        stack1.peak();
        stack2.push(2);
        stack2.push(10);
        stack2.size();
    }

    private int [] stack ;
    private int top;
    private static final int DEFAULT_CAPACITY = 16;

    public StackImpl() {
        this(DEFAULT_CAPACITY);
    }
    public StackImpl(int size) {
        stack = new int[size];
        top = 0;
    }

    @Override
    public boolean push(int value) {
        //扩容
        if (top ==  stack.length){
            int[] temp = Arrays.copyOf(stack,2*stack.length);
            stack = temp;
        }
        stack[top++] = value;
        return false;
    }

    @Override
    public int pop() {
        if (top==0){
            throw new EmptyStackException();
        }
        else{
            return stack[--top];
        }

    }

    @Override
    public int peak() {
        if (top == 0){
            throw new EmptyStackException();
        }
        return  stack[top-1];
        //return 0;
    }

    @Override
    public int size() {
        return top;
    }

    @Override
    public boolean isEmpty() {
        return top ==0 ;
    }
}
