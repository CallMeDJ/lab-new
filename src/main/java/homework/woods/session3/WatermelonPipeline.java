package homework.woods.session3;

import homework.woods.exception.BizServiceException;
import homework.woods.utils.WoodsPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.ObjIntConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 西瓜流水线
 * @author woods
 */
public class WatermelonPipeline {

  private static final int REPORTER_NUM = 5;

  /**
   * 记录当前检查到第几个西瓜
   */
  private static AtomicInteger index = new AtomicInteger(1);


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
    int[] bananaWatermelonArray = {-1, 0, 5, 1, 60};

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

    List<CommonWatermelon> filteredWatermenlon = filterWatermelons(commonWatermelons);

    writeWatermelonReport(filteredWatermenlon);

    sendoutWatermelons(filteredWatermenlon);

    countingWatermelons(filteredWatermenlon);
  }


  public static List<CommonWatermelon> mergeWatermelons(List<BananaWatermelon> bananaWatermelons, List<AppleWatermelon> appleWatermelons) {
    //TODO 这里是需要你自己实现的
    // 1、把两种西瓜使用 stream 遍历，然后 Function 转换为同一种西瓜。
    Function<Object, CommonWatermelon> watermelonFunction = (t) -> {
      int quantity = 0;
      if (t instanceof BananaWatermelon) {
        quantity = ((BananaWatermelon) t).bananaQuantity;
      } else if (t instanceof AppleWatermelon) {
        quantity = ((AppleWatermelon) t).appleQuantity;
      }
      return new CommonWatermelon(quantity);
    };

    return Stream.of(bananaWatermelons, appleWatermelons).flatMap(t -> t.stream()).map(watermelonFunction).collect(Collectors.toList());
  }


  public static List<CommonWatermelon> filterWatermelons(List<CommonWatermelon> filterWatermelons) {
    //TODO 这里是需要你自己实现的
    // 2、使用 Predicate 将西瓜中质量小0和质量大于50的瓜挑出来，丢掉。
    Predicate<CommonWatermelon> predicate1 = t -> t.quantity >= 0;
    Predicate<CommonWatermelon> predicate2 = t -> t.quantity <= 50;

    return filterWatermelons.stream().filter(predicate1.and(predicate2)).collect(Collectors.toList());
  }


  public static void writeWatermelonReport(List<CommonWatermelon> filterWatermelons) {
    //TODO 这里是需要你自己实现的
    //2、使用 Consumer 创建出5个检查人员，每个检查人员都会检查每个西瓜，使用 System.out.println("X 号检察员检查第 N 个西瓜，质量为 Y 完毕")。该过程使用多线程完成。
    //  也就是说我们会创建出 5 * N 个线程，待所有检查人员检查完成后（使用 CountDownlatch 来确认所有线程都执行完成了），观察所有的检验报告。

    int size = filterWatermelons.size();
    CountDownLatch _latch = new CountDownLatch(REPORTER_NUM * size);

    ObjIntConsumer<CommonWatermelon> consumer = (commonWatermelon, melonId) -> {
      for (int i = 1; i <= REPORTER_NUM; i ++) {
        new Thread(() -> {
          WoodsPrinter.println(Thread.currentThread().getName() + "号检察员检查第" + melonId + "个西瓜，质量为" + commonWatermelon.quantity + "，完毕。");
          Optional.ofNullable(_latch).ifPresent(t -> t.countDown());
        }, "" + i).start();
      }
    };

    for (CommonWatermelon commonWatermelon : filterWatermelons) {
      consumer.accept(commonWatermelon, index.getAndIncrement());
    }

    try {
      _latch.await();
    }catch (InterruptedException e)
    {
      throw new BizServiceException("线程中断");
    }finally {
      WoodsPrinter.println("检查完成。");
    }

    //如果没有全部报告都写完，阻塞在这里不允许返回。



//    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
//            .setNameFormat("demo-pool-%d").build();
//    ExecutorService singleThreadPool = new ThreadPoolExecutor(5 * filterWatermelons.size(), 5 * filterWatermelons.size(),
//            0L, TimeUnit.MILLISECONDS,
//            new LinkedBlockingQueue<Runnable>(10), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
//
//    singleThreadPool.execute(()-> System.out.println(Thread.currentThread().getName()));
//    singleThreadPool.shutdown();

  }


  public static void sendoutWatermelons(List<CommonWatermelon> commonWatermelons) {
    String a = commonWatermelons.stream().map(t -> t.quantity + "").sorted().collect(Collectors.joining(","));
    WoodsPrinter.println("[" + a + "]");
  }


  public static void countingWatermelons(List<CommonWatermelon> commonWatermelons) {
    WoodsPrinter.println("西瓜总计" + commonWatermelons.stream().mapToInt(t -> 1).reduce((sum, q) -> sum + q).getAsInt() + "个");
    WoodsPrinter.println("西瓜quantity总计" + commonWatermelons.stream().mapToInt(t -> t.quantity).reduce(0, (sum, q) -> sum + q) + "");
  }
}
