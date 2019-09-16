package homework.yangzm.session2;

import org.junit.Test;

public class StackTest {

    @Test
    public void StackImplTest() throws Exception {
        Stack stackImpl = new StackImpl(10);
        System.out.println("栈初始大小：" + stackImpl.size());
        for (int i = 1; i <= 11; i++) {
            System.out.println("push: " + i);
            stackImpl.push(i);
        }
        try {
            System.out.println("目前栈顶元素为：" + stackImpl.peak());
        } catch (Exception e) {
            System.out.println("peak Exception: " + e.getMessage());
        }finally {
            System.out.println("目前栈的大小为：" + stackImpl.size());
        }
        System.out.println("目前栈是否为空：" + stackImpl.isEmpty());

        for(int i=1; i<=12; i++) {
            try {
                int data = stackImpl.pop();
                System.out.println("pop: " + data);
            } catch (Exception e) {
                System.out.println("pop Exception: " + e.getMessage());
                break;
            }
        }
        try {
            System.out.println("目前栈顶元素为：" + stackImpl.peak());
        } catch (Exception e) {
            System.out.println("peak Exception: " + e.getMessage());
        }finally {
            System.out.println("目前栈的大小为：" + stackImpl.size());
        }
        System.out.println("目前栈是否为空：" + stackImpl.isEmpty());
    }

    @Test
    public void StackWithLogTest() throws Exception {
        Stack stackImpl = new StackWithLogImpl(10);
        System.out.println("栈初始大小：" + stackImpl.size());
        for (int i = 1; i <= 11; i++) {
            System.out.println("push: " + i);
            stackImpl.push(i);
        }
        try {
            System.out.println("目前栈顶元素为：" + stackImpl.peak());
        } catch (Exception e) {
            System.out.println("peak Exception: " + e.getMessage());
        }finally {
            System.out.println("目前栈的大小为：" + stackImpl.size());
        }
        System.out.println("目前栈是否为空：" + stackImpl.isEmpty());

        for(int i=1; i<=12; i++) {
            try {
                int data = stackImpl.pop();
                System.out.println("pop: " + data);
            } catch (Exception e) {
                System.out.println("pop Exception: " + e.getMessage());
                break;
            }
        }
        try {
            System.out.println("目前栈顶元素为：" + stackImpl.peak());
        } catch (Exception e) {
            System.out.println("peak Exception: " + e.getMessage());
        }finally {
            System.out.println("目前栈的大小为：" + stackImpl.size());
        }
        System.out.println("目前栈是否为空：" + stackImpl.isEmpty());
    }
}
