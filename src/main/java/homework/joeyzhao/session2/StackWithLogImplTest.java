package homework.joeyzhao.session2;

public class StackWithLogImplTest {
    public static void main(String[] args){
//        StackImpl s=new StackWithLogImpl();
//        StackImplTest.testStack(s);
        StackImpl s=new StackImpl();
        Stack s2= StackWithLogWrapper.wrap(s);
        StackImplTest.testStack(s2);
    }
}
