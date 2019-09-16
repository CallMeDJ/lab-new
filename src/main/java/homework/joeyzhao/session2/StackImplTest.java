package homework.joeyzhao.session2;

public class StackImplTest {

    public static void main(String[] args){

        StackImpl s=new StackImpl();
        testStack(s);

    }

    static void testStack(Stack s) {
        s.push(1);
        s.push(2);
        int pop1=s.pop();
        System.out.println(String.format("Popped : %d",pop1));
        System.out.println(String.format("Now the peak is: %d",s.peak()));
        s.pop();
        try{
            System.out.println(s.peak());
        }catch (Exception e){
            System.out.println(String.format("Peak() got Exception: %s",e.getMessage()));
        }
        try{
            System.out.println(s.pop());
        }catch (Exception e){
            System.out.println(String.format("Pop() got Exception: %s",e.getMessage()));
        }
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        System.out.print(String.format("Now the size is %d",s.size()));
    }
}
