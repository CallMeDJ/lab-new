package homework.seven.session3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 西瓜过滤器
 *
 * @author Seven-Steven
 * @date 2019-07-30 00:24
 */
public class WatermelonPipeline {


  public static class BananaWatermelon {
    int bananaQuantity;

    public BananaWatermelon(int bananaQuantity) {
      this.bananaQuantity = bananaQuantity;
    }
  }


  public static class AppleWatermelon {
    int appleQuantity;

    public AppleWatermelon(int appleQuantity) {
      this.appleQuantity = appleQuantity;
    }
  }


  public static class CommonWatermelon {
    int quantity;

    public CommonWatermelon(int quantity) {
      this.quantity = quantity;
    }
  }


  public static void main(String[] args) {
    int[] bananaWatermelonArray = {-1, 0, 5, 60};

    List<BananaWatermelon> bananaWatermelons = new ArrayList<>();

    for (int i = 0; i < bananaWatermelonArray.length; i++) {
      bananaWatermelons.add(new BananaWatermelon(bananaWatermelonArray[i]));
    }

    int[] appleWatermelonArray = {-1, 0, 5, 60};

    List<AppleWatermelon> appleWatermelons = new ArrayList<>();

    for (int i = 0; i < appleWatermelonArray.length; i++) {
      appleWatermelons.add(new AppleWatermelon(appleWatermelonArray[i]));
    }

    List<CommonWatermelon> commonWatermelons = mergeWatermelons(bananaWatermelons, appleWatermelons);

    List<CommonWatermelon> filteredWatermelon = filterWatermelons(commonWatermelons);

    writeWatermelonReport(filteredWatermelon);

    sendOutWatermelons(filteredWatermelon);

    countingWatermelons(filteredWatermelon);
  }


  /**
   * 1、把两种西瓜使用 stream 遍历，然后 Function 转换为同一种西瓜。
   * @param bananaWatermelons 香蕉西瓜
   * @param appleWatermelons 苹果西瓜
   * @return 大众西瓜
   */
  public static List<CommonWatermelon> mergeWatermelons(List<BananaWatermelon> bananaWatermelons, List<AppleWatermelon> appleWatermelons) {
    // 1、把两种西瓜使用 stream 遍历，然后 Function 转换为同一种西瓜。
    List<CommonWatermelon> commonWatermelons = new ArrayList<>();
    Function<BananaWatermelon, CommonWatermelon> banana2Common = b -> new CommonWatermelon(b.bananaQuantity);
    Function<AppleWatermelon, CommonWatermelon> apple2Common = b -> new CommonWatermelon(b.appleQuantity);

    commonWatermelons.addAll(bananaWatermelons.stream().map(banana2Common).collect(Collectors.toList()));
    commonWatermelons.addAll(appleWatermelons.stream().map(apple2Common).collect(Collectors.toList()));

    return commonWatermelons;
  }


  /**
   * 2、使用 Predicate 将西瓜中质量小0和质量大于50的瓜挑出来，丢掉。
   * @param filterWatermelons 待筛选的西瓜
   * @return 筛选之后的西瓜
   */
  public static List<CommonWatermelon> filterWatermelons(List<CommonWatermelon> filterWatermelons) {
    if (Objects.isNull(filterWatermelons)) {
      throw new IllegalArgumentException();
    }

    Predicate<CommonWatermelon> moreThanZero = e -> e.quantity >= 0;
    Predicate<CommonWatermelon> lessThan50 = e -> e.quantity <= 50;

    return filterWatermelons.stream().filter(moreThanZero.and(lessThan50)).collect(Collectors.toList());
  }


  /**
   * 2、使用 Consumer 创建出5个检查人员，每个检查人员都会检查每个西瓜，使用 System.out.println("X 号检察员检查第 N 个西瓜，质量为 Y 完毕")。该过程使用多线程完成。
   * 也就是说我们会创建出 5 * N 个线程，待所有检查人员检查完成后（使用 CountDownlatch 来确认所有线程都执行完成了），观察所有的检验报告。
   * @param filterWatermelons 待检查的西瓜
   */
  public static void writeWatermelonReport(List<CommonWatermelon> filterWatermelons) {
    AtomicInteger employeeNumber = new AtomicInteger(1);
    AtomicInteger watermelonsNumber = new AtomicInteger(1);
    CountDownLatch countDownLatch = new CountDownLatch(filterWatermelons.size() * 5);

    Consumer<CommonWatermelon> commonWatermelonConsumer1 = (commonWatermelon -> {
      int currentWatermelon = watermelonsNumber.getAndIncrement();
      for (int i = 0; i < 5; i++) {
        new Thread(() -> {
          System.out.println(employeeNumber.getAndIncrement() + " 号检察员检查第 " + currentWatermelon + " 个西瓜，质量为 " + commonWatermelon.quantity + "完毕");
          countDownLatch.countDown();
        }).start();
      }

      // 重置检察员计数器
      employeeNumber.set(1);
    });

    for (CommonWatermelon commonWatermelon : filterWatermelons) {
      commonWatermelonConsumer1.accept(commonWatermelon);
    }

    //如果没有全部报告都写完，阻塞在这里不允许返回。
    try {
      countDownLatch.await();
    } catch (InterruptedException e) {
      System.out.println("操作被打断！");
    }
  }


  /**
   * 4、把现在还剩下的西瓜按顺序打印出来，格式 [1，3，4，5，6]。
   * @param commonWatermelons 待打印的西瓜
   */
  public static void sendOutWatermelons(List<CommonWatermelon> commonWatermelons) {
    StringBuffer sb = new StringBuffer().append('[');
    commonWatermelons.forEach(e -> sb.append(e.quantity).append(','));
    sb.deleteCharAt(sb.length() - 1).append(']');
    System.out.println(sb.toString());
  }


  /**
   * 5、使用 reduce 计算一下，最终这批西瓜总计有多少个，并打印出来
   * @param commonWatermelons 待计算的西瓜
   */
  public static void countingWatermelons(List<CommonWatermelon> commonWatermelons) {
    int count = commonWatermelons.stream().mapToInt(e -> 1).reduce(Integer::sum).getAsInt();
    System.out.println("最终有 " + count + " 个西瓜");
  }
}
