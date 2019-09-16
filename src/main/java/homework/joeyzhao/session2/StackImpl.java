package homework.joeyzhao.session2;


public class StackImpl implements Stack{

    private int[] _stack;
    private int capacity;
    private int pos=0;

    public StackImpl(int capacity){
        if(capacity<0)throw new IllegalArgumentException("Positive Capacity Required");
        this.capacity=capacity;
        initializeStack();
    }

    public StackImpl(){
        capacity=3;
        initializeStack();
    }

    private void initializeStack(){
        _stack=new int[capacity];
    }

    private void doubleCapacity(){
        capacity*=2;
        int[] newStack=new int[capacity];
        for (int i=0;i<capacity/2;i++){
            newStack[i]=_stack[i];
        }
        _stack=newStack;
    }

    @Override
    public boolean push(int value) {
        if(pos==capacity)doubleCapacity();
        _stack[pos++]=value;
        return true;
    }

    @Override
    public int pop() {
        if(pos==0)throw new IllegalStateException("Stack is empty");
        return _stack[--pos];
    }

    @Override
    public int peak() {
        if(pos==0)throw new IllegalStateException("Stack is empty");
        return _stack[pos-1];
    }

    @Override
    public int size() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return pos==0;
    }
}


