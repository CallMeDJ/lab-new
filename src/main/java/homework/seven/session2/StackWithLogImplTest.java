package homework.seven.session2;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Seven-Steven
 * @date 2019-07-24 00:41
 */
public class StackWithLogImplTest {
  private StackWithLogImpl stack = new StackWithLogImpl();

  @Test
  public void push() {
    Assert.assertEquals(0, stack.size());
    for (int i = 0; i < 100; i++) {
      Assert.assertTrue(this.stack.push(i));
    }
    Assert.assertEquals(100, stack.size());
  }

  @Test(expected = RuntimeException.class)
  public void pop() throws Exception {
    this.stack.push(1);
    this.stack.push(2);
    Assert.assertEquals(2, this.stack.pop());
    Assert.assertEquals(1, this.stack.pop());
    Assert.assertEquals(1000, this.stack.pop());
  }

  @Test(expected = RuntimeException.class)
  public void peak() throws Exception {
    this.stack.push(100);
    this.stack.push(200);
    Assert.assertEquals(200, this.stack.peak());
    Assert.assertEquals(200, this.stack.peak());
    Assert.assertEquals(200, this.stack.pop());
    Assert.assertEquals(100, this.stack.peak());
    Assert.assertEquals(100, this.stack.peak());
    Assert.assertEquals(100, this.stack.pop());
    Assert.assertEquals(1, this.stack.peak());
  }

  @Test
  public void size() throws Exception {
    Assert.assertEquals(0, this.stack.size());
    int size = 1000;
    for (int i = 0; i < size; i++) {
      Assert.assertTrue(this.stack.push(i));
    }
    Assert.assertEquals(size, this.stack.size());
    this.stack.pop();
    Assert.assertEquals(size - 1, this.stack.size());
  }

  @Test
  public void isEmpty() {
    Assert.assertTrue(this.stack.isEmpty());
    this.stack.push(-23);
    Assert.assertFalse(this.stack.isEmpty());
  }
}
