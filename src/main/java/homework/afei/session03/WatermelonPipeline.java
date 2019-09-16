package session003;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 实现对于西瓜的流水线处理，最终产出合格的西瓜。
 * <p>
 * <p>
 * 1、把两种西瓜使用 stream 遍历，然后 Function 转换为同一种西瓜。
 * <p>
 * 2、使用 Predicate 将西瓜中质量小0和质量大于50的瓜挑出来，丢掉。
 * <p>
 * 3、使用 Consumer 创建出5个检查人员，每个检查人员都会检查每个西瓜，使用 System.out.println("X 号检察员检查第 N 个西瓜，质量为 Y 完毕")。该过程使用多线程完成。
 * 也就是说我们会创建出 50 个线程，待所有检查人员检查完成后（使用 CountDownlatch 来确认所有线程都执行完成了），观察所有的检验报告。
 * <p>
 * 4、把现在还剩下的西瓜按顺序打印出来，格式 [1，3，4，5，6]。
 * <p>
 * 5、使用 reduce 计算一下，最终这批西瓜总计有多少个，并打印出来
 *
 * ------------------------------------------------------------
 * 本实现尽可能展现 Lambda 编程的各种技巧，尽可能实践函数式编程范式。
 * ------------------------------------------------------------
 *
 * @author logow.whl
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

        int[] bananaWatermelonArray = {8,5,7,5,8,4,-1, 0, 5, 60, 30};
        List<BananaWatermelon> bananaWatermelons = Arrays.stream(bananaWatermelonArray).mapToObj(BananaWatermelon::new).collect(Collectors.toList());

        int[] appleWatermelonArray = {7,5,8,4,5,8,6,8,5,8,4,-1, 0, 5, 60, 20};
        List<AppleWatermelon> appleWatermelons = Arrays.stream(appleWatermelonArray).mapToObj(AppleWatermelon::new).collect(Collectors.toList());

        List<CommonWatermelon> commonWatermelons = mergeWatermelons(bananaWatermelons, appleWatermelons);

        List<CommonWatermelon> filteredWatermenlon = filterWatermelons(commonWatermelons);

        writeWatermelonReport(filteredWatermenlon);

        sendoutWatermelons(filteredWatermenlon);

        countingWatermelons(filteredWatermenlon);
    }


    public static List<CommonWatermelon> mergeWatermelons(List<BananaWatermelon> bananaWatermelons, List<AppleWatermelon> appleWatermelons) {
        Objects.requireNonNull(bananaWatermelons);   //判断对象是否为空，空的时候报空指针异常的时候就可以使用这个方法
        Objects.requireNonNull(appleWatermelons);

        // 1、把两种西瓜使用 stream 遍历，然后 Function 转换为同一种西瓜。
        Stream<CommonWatermelon> bananaStream = bananaWatermelons.stream().map(w -> new CommonWatermelon(w.bananaQuantity));
        Stream<CommonWatermelon> appleStream = appleWatermelons.stream().map(w -> new CommonWatermelon(w.appleQuantity));

        return Stream.concat(bananaStream, appleStream).collect(Collectors.toList());
    }


    public static List<CommonWatermelon> filterWatermelons(List<CommonWatermelon> filterWatermelons) {
        Objects.requireNonNull(filterWatermelons);

        //2、使用 Predicate 将西瓜中质量小0和质量大于50的瓜挑出来，丢掉。
        return filterWatermelons.stream().filter(w -> !(w.quantity < 0 || w.quantity > 50)).collect(Collectors.toList());
    }


    public static void writeWatermelonReport(List<CommonWatermelon> filterWatermelons) {
//        Objects.requireNonNull(filterWatermelons);

        Consumer<CommonWatermelon> consumer = watermelon -> {
            try {
                TimeUnit.MILLISECONDS.sleep(watermelon.quantity * 100L);//???
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        int checkerCount = 5;
        AtomicInteger seq = new AtomicInteger(0);//原子操作。
        CountDownLatch latch = new CountDownLatch(checkerCount * filterWatermelons.size()); //计数器闭锁

        // 创建 5 个检查员，5 * N 个线程同时检查所有西瓜
        Stream.generate(seq::incrementAndGet)
                .limit(checkerCount)
                .flatMap(id -> mapToList(filterWatermelons,
                        (index, watermelon) -> new WatermelonCheck(id, index, watermelon, latch)).stream())
                .forEach(watermelonCheck -> new Thread(() -> watermelonCheck.execute(consumer)).start());

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void sendoutWatermelons(List<CommonWatermelon> commonWatermelons) {
        Objects.requireNonNull(commonWatermelons);
        String string = commonWatermelons.stream().map(watermelon -> String.valueOf(watermelon.quantity))
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println(string);
    }

    public static void countingWatermelons(List<CommonWatermelon> commonWatermelons) {
        Objects.requireNonNull(commonWatermelons);
        int count = commonWatermelons.stream().mapToInt(w -> w.quantity).reduce(0, (a, b) -> a + b);
        System.out.println("最终西瓜总计 " + count + "个");
    }

    //这个方法目前还看不懂，需要再仔细研究研究！
    private static <T, R> List<R> mapToList(List<T> list, BiFunction<Integer, T, R> mapper) {
        List<R> results = new ArrayList<>(list.size());
        int index = 0;
        for (T t : list) {
            R r = mapper.apply(index++, t);
            results.add(r);
        }
        return results;
    }

    private static class WatermelonCheck {
        final int checkerId;
        final int index;
        final CommonWatermelon watermelon;
        final CountDownLatch latch;

        WatermelonCheck(int checkerId, int index, CommonWatermelon watermelon, CountDownLatch latch) {
            this.checkerId = checkerId;
            this.index = index;
            this.watermelon = watermelon;
            this.latch = latch;
        }
        //这里这种用法，也是弄不清楚为什么要这样做，得考虑用自己目前能够实现的方法，重新实现一遍。 2019-08-01  21:55
        void execute(Consumer<? super CommonWatermelon> consumer) {
            try {
                consumer.accept(watermelon);
                System.out.printf("第 %d 号检查员检查第 %d 个西瓜，质量为 %d 完毕%n", checkerId, index + 1, watermelon.quantity);
            } catch (Exception e) {
                System.out.printf("第 %d 号检查员检查第 %d 个西瓜，质量为 %d 失败: %s%n", checkerId, index + 1, watermelon.quantity, e);
            }
            latch.countDown();
        }
    }
}