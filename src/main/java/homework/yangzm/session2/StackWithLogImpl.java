package homework.yangzm.session2;

public class StackWithLogImpl extends StackImpl  {

    public StackWithLogImpl(int i) throws Exception {
        super(i);
    }

    public StackWithLogImpl() {
        super();
    }

    /**
     * 入栈
     * @return
     */
    public boolean push(int value){
        System.out.println("start push");
        boolean success = super.push(value);
        System.out.println("end push");
        return success;
    }

    /**
     * 出栈
     * @return
     */
    public int pop() throws Exception {
        System.out.println("start pop");
        int data = super.pop();
        System.out.println("end pop");
        return data;
    }

    /**
     * 查看栈顶元素
     */
    public int peak() throws Exception{
        System.out.println("start peak");
        int data = super.peak();
        System.out.println("end peak");
        return data;
    }

    /**
     * 栈的大小
     */
    public int size() {
        System.out.println("start size");
        int data = super.size();
        System.out.println("end size");
        return data;
    }

    /**
     * 栈是否为空
     * @return
     */
    public boolean isEmpty(){
        System.out.println("start isEmpty");
        boolean success = super.isEmpty();
        System.out.println("end isEmpty");
        return success;
    }
}
