package homework.ashuoa.session2;

/**
 * Stack
 *
 * @author ashuoa
 */
public class StackImpl implements Stack {

    /**
     * 默认初始化大小
     */
    private static final int DEFAULT_INIT_SIZE = 16;

    /**
     * 存储数据的数组
     */
    private int[] data;

    /**
     * 栈顶位置
     */
    private int top = -1;

    /**
     * 无参构造
     */
    public StackImpl() {
        this(DEFAULT_INIT_SIZE);
    }

    /**
     * 含参构造
     *
     * @param size 初始化大小
     */
    public StackImpl(int size) {
        if (size < 1) {
            throw new IllegalAccessError("Stack size must be greater than 0.");
        }
        this.data = new int[size];
    }

    /**
     * 入栈
     */
    @Override
    public boolean push(int value) {
        // 满栈扩容
        if (size() == data.length) {
            int[] temp = data;
            data = new int[size() * 2];

            System.arraycopy(temp, 0, data, 0, temp.length);
        }
        // 插入数据
        data[++top] = value;
        return true;
    }

    /**
     * 出栈
     */
    @Override
    public int pop() {
        if (top == -1) {
            throw new RuntimeException("The stack is empty.");
        }
        return data[top--];
    }

    /**
     * 查看栈顶元素
     */
    @Override
    public int peak() {
        if (top == -1) {
            throw new RuntimeException("The stack is empty.");
        }
        return data[top];
    }

    /**
     * 栈的大小
     */
    @Override
    public int size() {
        return top + 1;
    }

    /**
     * 栈是否为空
     */
    @Override
    public boolean isEmpty() {
        return top == -1;
    }

}
