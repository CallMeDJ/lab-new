package homework.seven.session2;

/**
 * 栈实现
 *
 * @author Seven-Steven
 * @date 2019-07-17 21:25
 */
public class StackImpl implements Stack {
  /**
   * 栈大小
   */
  private int size = 0;

  /**
   * 栈内元素
   */
  private int[] elements;

  /**
   * 创建新栈, 默认初始化大小为 16
   */
  public StackImpl() {
    this.elements = new int[16];
  }

  /**
   * 创建新栈
   *
   * @param initSize 栈初始化大小
   */
  public StackImpl(int initSize) {
    this.elements = new int[initSize];
  }

  /**
   * 数组扩容
   *
   * @param array        原数组
   * @param increaseSize 需要另外扩充的大小
   * @return 扩容后的数组
   */
  private int[] increaseArraySpace(int[] array, int increaseSize) {
    if (increaseSize < 0) {
      throw new IllegalArgumentException("increaseSize shouldn't be less than 0");
    }

    if (increaseSize == 0) {
      return array;
    }

    int[] newArray = new int[array.length + increaseSize];
    System.arraycopy(array, 0, newArray, 0, array.length);
    return newArray;
  }

  @Override
  public boolean push(int element) {
    // 自动扩容
    if (this.size > this.elements.length) {
      this.elements = this.increaseArraySpace(this.elements, this.elements.length);
    }

    size++;
    this.elements[size - 1] = element;
    return true;
  }

  @Override
  public int pop() throws Exception {
    if (this.isEmpty()) {
      throw new Exception("this stack is empty!");
    }

    return this.elements[--this.size];
  }

  @Override
  public int peak() throws Exception {
    if (this.isEmpty()) {
      throw new Exception("this stack is empty!");
    }

    return this.elements[this.size - 1];
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public boolean isEmpty() {
    return this.size() == 0;
  }
}
