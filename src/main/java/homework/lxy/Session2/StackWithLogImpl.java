package homework.lxy.Session2;

public class StackWithLogImpl extends StackImpl{
    @Override
    public int push(int value){
        System.out.println("start push");
        super.push(value);
        System.out.println("end push");
        return value;
    }

    @Override
    public int pop(){
        System.out.println("start pop");
        int value = super.pop();
        System.out.println("end pop");
        return value;
    }

    @Override
    public int peak(){
        System.out.println("start peak");
        int value = super.peak();
        System.out.println("end peak");
        return value;
    }

    @Override
    public int size(){
        System.out.println("start count size");
        int value = super.size();
        System.out.println("end count size");
        return value;
    }

    @Override
    public boolean isEmpty(){
        System.out.println("start isEmpty");
        boolean isEmpty = super.isEmpty();
        System.out.println("end isEmpty");
        return isEmpty;
    }
}
