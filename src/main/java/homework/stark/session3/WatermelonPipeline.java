package homework.stark.session3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WatermelonPipeline {

    /**
     * 检查员的数量
     */
    private static final int CHECKER = 5;

    /**
     * 西瓜计数器
     */
    private static AtomicInteger CommonWatermelonIndex = new AtomicInteger(1);

    interface CommonFruit {
        /**
         * 水果的重量
         *
         * @return
         */
        int weight();

    }

    public static class BananaWatermelon implements CommonFruit {
        // 香蕉西瓜的重量
        int bananaWeight;

        public BananaWatermelon(int bananaWeight) {
            this.bananaWeight = bananaWeight;
        }

        @Override
        public int weight() {
            return bananaWeight;
        }
    }


    public static class AppleWatermelon implements CommonFruit {
        // 苹果西瓜的重量
        int appleWeight;

        public AppleWatermelon(int appleWeight) {
            this.appleWeight = appleWeight;
        }

        @Override
        public int weight() {
            return appleWeight;
        }
    }


    public static class CommonWatermelon {
        // 通用西瓜的重量
        int weight;

        public CommonWatermelon(int weight) {
            this.weight = weight;
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

        List<CommonWatermelon> filteredWatermenlon = filterWatermelons(commonWatermelons);

        writeWatermelonReport(filteredWatermenlon);

        sendoutWatermelons(filteredWatermenlon);

        countingWatermelons(filteredWatermenlon);
    }


    public static List<CommonWatermelon> mergeWatermelons(List<BananaWatermelon> bananaWatermelons, List<AppleWatermelon> appleWatermelons) {
        //TODO 这里是需要你自己实现的
        // 1、把两种西瓜使用 stream 遍历，然后 Function 转换为同一种西瓜。
        if (bananaWatermelons.size() == 0 && appleWatermelons.size() == 0) {
            throw new IllegalArgumentException("没什么西瓜来合并！");
        }

        Function<CommonFruit, CommonWatermelon> anyWatermelonToCommon = a -> new CommonWatermelon(a.weight());

        List<CommonWatermelon> commonWatermelons = bananaWatermelons.stream()
                .map(anyWatermelonToCommon)
                .collect(Collectors.toList());

        commonWatermelons.addAll(appleWatermelons.stream()
                .map(anyWatermelonToCommon)
                .collect(Collectors.toList()));

        return commonWatermelons;
    }


    public static List<CommonWatermelon> filterWatermelons(List<CommonWatermelon> filterWatermelons) {
        //TODO 这里是需要你自己实现的
        //2、使用 Predicate 将西瓜中质量小于0和质量大于50的瓜挑出来，丢掉。
        if (filterWatermelons.size() == 0) {
            throw new IllegalArgumentException("没啥西瓜可过滤的。");
        }
        Predicate<CommonWatermelon> weightPredicate = e -> (e.weight > 0 && e.weight <= 50);
        return filterWatermelons.stream().filter(weightPredicate).collect(Collectors.toList());
    }


    public static void writeWatermelonReport(List<CommonWatermelon> filterWatermelons) {
        //TODO 这里是需要你自己实现的
        //2、使用 Consumer 创建出5个检查人员，每个检查人员都会检查每个西瓜，使用 System.out.println("X 号检察员检查第 N 个西瓜，质量为 Y 完毕")。该过程使用多线程完成。
        //  也就是说我们会创建出 5 * N 个线程，待所有检查人员检查完成后（使用 CountDownlatch 来确认所有线程都执行完成了），观察所有的检验报告。
        CountDownLatch countDownLatch = new CountDownLatch(CHECKER * filterWatermelons.size());

        Consumer<CommonWatermelon> commonWatermelonConsumer = (commonWatermelon -> {

            for (int i = 0; i < CHECKER; i++) {
                int currentWatermelonIndex = CommonWatermelonIndex.getAndIncrement();
                new Thread(() -> {
                    System.out.println(String.format("%s 号检察员检查第 %s 个西瓜，质量为 %s 完毕",
                            Thread.currentThread().getName(),
                            currentWatermelonIndex,
                            commonWatermelon.weight)
                    );
                    countDownLatch.countDown();
                }, "" + i
                ).start();
            }
        });

        for (CommonWatermelon commonWatermelon : filterWatermelons) {
            commonWatermelonConsumer.accept(commonWatermelon);
        }

        //如果没有全部报告都写完，阻塞在这里不允许返回。
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new IllegalThreadStateException("CountDownLatch Exception");
        } finally {
            System.out.println("检查完成！");
        }
    }


    public static void sendoutWatermelons(List<CommonWatermelon> commonWatermelons) {

        StringBuilder output = new StringBuilder("[");
        int watermelonsNumber = commonWatermelons.size();
        if (watermelonsNumber == 0) {
            System.out.println("[没啥可输出的西瓜。]");
            return;
        }

        for (CommonWatermelon commonWatermelon : commonWatermelons) {
            output.append(commonWatermelon.weight);
            output.append(",");
        }
        System.out.println(output.substring(0, output.length() - 1).concat("]"));
    }


    public static void countingWatermelons(List<CommonWatermelon> commonWatermelons) {
      Integer count = commonWatermelons.stream().map(e -> 1).reduce(0, Integer::sum);
      System.out.println(String.format("这批西瓜总计有 %s 个", count));
    }
}
