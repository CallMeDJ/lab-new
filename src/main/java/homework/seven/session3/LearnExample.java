package homework.seven.session3;

import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 学习样例代码
 *
 * @author Seven-Steven
 * @date 2019-07-27 00:45
 */
public class LearnExample {

  /**
   * 1. 实现Runnable线程案例
   */
  @Test
  public void thread() {
    new Thread(() -> System.out.println("runnable")).start();
  }

  /**
   * 3. 使用Lambda表达式遍历List集合
   */
  @Test
  public void forEach() {
    List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f");
    list.forEach(System.out::print);
    list.forEach(LearnExample::show);
    list.forEach(s -> System.out.print(s));
  }

  private static void show(String s) {
    System.out.println(s);
  }

  /**
   * 4. 使用lambda表达式和函数式接口Predicate
   */
  @Test
  public void filter() {
    List<String> list = Arrays.asList("Java", "PHP", "Python", "Html", "CSS", "JavaScript", "TypeScript");
    filter(list, s -> ((String) s).startsWith("J"));
  }

  private static void filter(List list, Predicate predicate) {
    list.stream().filter(predicate::test).forEach(System.out::println);

    list.forEach(item -> {
      if (predicate.test(item)) {
        System.out.println(item);
      }
    });
  }

  /**
   * 5. 如何在lambda表达式中加入Predicate
   */
  @Test
  public void predicate() {
    List<String> list = Arrays.asList("Java", "PHP", "Python", "Html", "CSS", "JavaScript", "TypeScript");

    Predicate<String> startWithJ = n -> n.startsWith("J");
    Predicate<String> longerThan4 = n -> n.length() > 4;

    list.stream().filter(startWithJ.and(longerThan4)).forEach(System.out::println);
  }

  /**
   * 例6、Java 8中使用lambda表达式的Map和Reduce示例
   */
  @Test
  public void map() {
    List<String> list = Arrays.asList("java", "php", "python", "css");
    list.stream().map(String::toUpperCase).forEach(System.out::println);
    list.forEach(System.out::println);

    list.stream().map((item) -> item = item.toUpperCase()).forEach(System.out::println);
    list.forEach(System.out::println);
  }

  /**
   * 例6.2、Java 8中使用lambda表达式的Map和Reduce示例
   */
  @Test
  public void reduce() {
    List<Integer> list = Arrays.asList(12, 23, 34, 45, 56, 67, 78, 89, 90);

    // TODO don't understand
    Integer integer = list.stream().reduce((sum, item) -> sum + item).get();
    System.out.println(integer);
  }

  /**
   * 例7、通过过滤创建一个String列表
   */
  @Test
  public void string() {
    List<String> list = Arrays.asList("Java", "PHP", "Python", "Html", "CSS", "JavaScript", "TypeScript");

    List<String> filtered = list.stream().filter(x -> x.length() > 3).collect(Collectors.toList());
    System.out.println(filtered);
  }

  /**
   * 例8、对列表的每个元素应用函数
   */
  @Test
  public void function() {
    List<String> list = Arrays.asList("Java", "PHP", "Python", "Html", "CSS", "JavaScript", "TypeScript");

    String newList = list.stream().map(i -> i.toUpperCase()).collect(Collectors.joining(","));
    System.out.println(newList);
  }

  /**
   * 例9、复制不同的值，创建一个子列表
   */
  @Test
  public void distinct() {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 9);
    List<Integer> collect = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
    List<Integer> collect1 = numbers.stream().map(i -> i * i).collect(Collectors.toList());

    System.out.println(collect);
    System.out.println(collect1);
    System.out.println(numbers.stream().distinct().collect(Collectors.toList()));
  }

  /**
   * 例10、计算集合元素的最大值、最小值、总和以及平均值
   */
  @Test
  public void calculate() {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 9);
    IntSummaryStatistics intSummaryStatistics = numbers.stream().mapToInt(x -> x).summaryStatistics();
    System.out.println("Max: " + intSummaryStatistics.getMax());
    System.out.println("Min: " + intSummaryStatistics.getMin());
    System.out.println("Sum: " + intSummaryStatistics.getSum());
    System.out.println("Ave: " + intSummaryStatistics.getAverage());
  }

  /**
   * lambda表达式有个限制，那就是只能引用 final 或 final 局部变量，这就是说不能在lambda内部修改定义在域外的变量。
   * 另外，只是访问它而不作修改是可以的
   */
  @Test
  public void useParam() {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 9);
    int a = 10;
    numbers.forEach(n -> System.out.println(n * a));
  }

  /**
   * Consumer<T>
   */
  @Test
  public void consumer() {
    StringBuffer sb = new StringBuffer("Hello ");
    Consumer<StringBuffer> stringBufferConsumer = str -> str.append("Bob! ");

    stringBufferConsumer.accept(sb);
    System.out.println(sb);

    sb = new StringBuffer("Hello ");
    Consumer<StringBuffer> after = str -> str.append("After! ");
    stringBufferConsumer.andThen(after).accept(sb);
    System.out.println(sb);
  }

  /**
   * BiConsumer
   */
  @Test
  public void biConsumer() {
    StringBuffer sb = new StringBuffer();
    BiConsumer<String, Integer> biConsumer = (a, b) -> {
      sb.append(a);
      sb.append(b);
    };

    biConsumer.accept("Hello ", 1);
    System.out.println(sb);
    
    BiConsumer<String , Integer> subString = (a, b) -> {
      System.out.println(a + b);
    };

    biConsumer.andThen(subString).accept("Hello ", 1);
    System.out.println(sb);
  }
}
