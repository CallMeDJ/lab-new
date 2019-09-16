package homework.woods.session2;

import homework.woods.exception.BizServiceException;
import homework.woods.utils.WoodsPrinter;

/**
 * @author: woods
 * @date: 2019/7/27
 * @description:
 */
public class WoodsSession2Test {

    public static void main(String[] args) {

        Stack stack1 = new StackImpl();
        Stack stack2 = new StackWithLogImpl();

        stack1.push(1);
        WoodsPrinter.println("---------------------------------------");
        stack2.push(2);

        // 测试功能实现
//        test1();

        // 测试缩减栈大小
//        test2();

    }

    /**
     * 测试功能实现
     */
    public static void test1()
    {
        StackImpl stack = new StackImpl();
        WoodsPrinter.println("size=" + stack.size());
        try {
            stack.pop();
        } catch (BizServiceException pope) {
            WoodsPrinter.println(pope.getMessage());
        }

        try {
            stack.peak();
        } catch (BizServiceException peake) {
            WoodsPrinter.println(peake.getMessage());
        }

        stack.push(1);
        WoodsPrinter.println("size=" + stack.size());
        WoodsPrinter.println("max=" + stack.maxSize());

        stack.push(2);
        WoodsPrinter.println("size=" + stack.size());
        WoodsPrinter.println("max=" + stack.maxSize());

        stack.push(3);
        WoodsPrinter.println("size=" + stack.size());
        WoodsPrinter.println("max=" + stack.maxSize());

        WoodsPrinter.println("peak=" + stack.peak());
        WoodsPrinter.println("size=" + stack.size());
        WoodsPrinter.println("max=" + stack.maxSize());

        WoodsPrinter.println("pop=" + stack.pop());
        WoodsPrinter.println("size=" + stack.size());
        WoodsPrinter.println("max=" + stack.maxSize());
    }
    /**
     * 测试节省空军
     */
    public static void test2()
    {
        StackImpl stack = new StackImpl();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        WoodsPrinter.println("size=" + stack.size());
        WoodsPrinter.println("max=" + stack.maxSize());

        WoodsPrinter.println("pop=" + stack.pop());
        WoodsPrinter.println("pop=" + stack.pop());
        WoodsPrinter.println("pop=" + stack.pop());
        WoodsPrinter.println("pop=" + stack.pop());
        WoodsPrinter.println("pop=" + stack.pop());
        WoodsPrinter.println("size=" + stack.size());
        WoodsPrinter.println("max=" + stack.maxSize());
    }

}
