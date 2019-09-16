package homework.seven.session2;

/**
 * 带日志的栈
 *
 * @author Seven-Steven
 * @date 2019-07-19 23:14
 */
public class StackWithLogImpl extends StackImpl {
  @Override
  public boolean push(int element) {
    System.out.println("start push");
    boolean pushResult = super.push(element);
    System.out.println("end push");
    return pushResult;
  }

  @Override
  public int pop() throws Exception {
    System.out.println("start pop");
    int popResult = super.pop();
    System.out.println("end pop");
    return popResult;
  }

  @Override
  public int peak() throws Exception {
    System.out.println("start peak");
    int peakResult = super.peak();
    System.out.println("end peak");
    return peakResult;
  }

  @Override
  public int size() {
    System.out.println("start size");
    int size = super.size();
    System.out.println("end size");
    return size;
  }

  @Override
  public boolean isEmpty() {
    System.out.println("start isEmpty");
    boolean isEmpty = super.isEmpty();
    System.out.println("end isEmpty");
    return isEmpty;
  }
}
