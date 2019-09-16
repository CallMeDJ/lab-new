package homework.ashuoa.session2;

public interface Stack {

    /**
     * 入栈
     */
    boolean push(int value);

    /**
     * 出栈
     */
    int pop();

    /**
     * 查看栈顶元素
     */
    int peak();

    /**
     * 栈的大小
     */
    int size();

    /**
     * 栈是否为空
     */
    boolean isEmpty();

}
