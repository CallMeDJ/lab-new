package homework.woods.session2;

import homework.woods.utils.WoodsPrinter;

/**
 * @author woods
 */
public class StackWithLogImpl extends StackImpl{

    @Override
    public boolean push(int value) {
        WoodsPrinter.println("start push:" + value);
        super.push(value);
        WoodsPrinter.println("end push");
        return true;
    }

    @Override
    public int pop() {
        WoodsPrinter.println("start pop");
        int value = super.pop();
        WoodsPrinter.println("end pop");
        return value;
    }

    @Override
    public int peak() {
        WoodsPrinter.println("start peak");
        int value = super.peak();
        WoodsPrinter.println("end peak");
        return value;
    }

}
