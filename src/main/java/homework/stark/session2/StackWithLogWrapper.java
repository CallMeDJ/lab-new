package homework.stark.session2;
import homework.joeyzhao.session2.Stack;
import homework.joeyzhao.session2.StackImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 参考joeyzhao 的homework
 * 这个类提供另一种实现给每个method加log的思路：Dynamic Proxies（动态代理）也就是用java.lang.reflect.Proxy
 * 不再需要给每个method都加一遍log,而是用 Proxy.newProxyInstance来包装已有的StackImpl类
 * InvocationHandler需要实现Invoke方式，在这个函数中写出每个method指派给被包装类StackImpl的运行方式
 * 参考例子：
 * http://tutorials.jenkov.com/java-reflection/dynamic-proxies.html
 * 以及这里：
 * https://stackoverflow.com/questions/30344715/automatically-delegating-all-methods-of-a-java-class
 */
public class StackWithLogWrapper implements InvocationHandler {

    private final StackImpl delegate;

    public StackWithLogWrapper(StackImpl delegate) {
        this.delegate = delegate;
    }

    /**
     * 给出包装后Stack的factory函数 例：
     * StackImpl s=new StackImpl(); Stack s1= StackWithLogWrapper.wrap(s);
     * @param wrapped 需要被包装的StackImpl实例
     * @return 包装后的Stack类（具有log功能）
     */
    public static Stack wrap(StackImpl wrapped) {
        return (Stack) Proxy.newProxyInstance(StackImpl.class.getClassLoader(), new Class[] { Stack.class }, new StackWithLogWrapper(wrapped));
    }

    /**
     * 辅助函数 找到代理clazz中对应method的函数
     * @param clazz 目标类
     * @param method 目标函数
     * @return 代理中对应的函数
     * @throws Throwable 如果没找到对应函数 抛出异常
     */
    private Method findMethod(Class<?> clazz, Method method) throws Throwable {
        try {
            return clazz.getDeclaredMethod(method.getName(), method.getParameterTypes());
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    /**
     * InvocationHandler必须要实现的函数
     * @param proxy 代理 （这里就是delegate）
     * @param method 目标函数
     * @param args 其他参数
     * @return 本来（如果delegate直接调用)期望的结果
     * @throws Throwable 可抛出异常
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method m = findMethod(delegate.getClass(), method);
        if (m != null) {
            System.out.println(String.format("start %s",method.getName()));
            try{
                return m.invoke(delegate, args);
            }catch (InvocationTargetException e){
                //https://stackoverflow.com/questions/10214525/re-throw-an-invocationtargetexception-target-exception
                throw (Exception)e.getCause();
            }finally {
                System.out.println(String.format("end %s",method.getName()));
            }
        }
        return null;
    }
}

