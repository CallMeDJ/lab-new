package homework.yangzm.session3;

import homework.yangzm.util.SysytemUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WatermelonPipeline {

    public static class BananaWatermelon {
        int bananaQuality;

        public BananaWatermelon(int bananaQuality) {
            this.bananaQuality = bananaQuality;
        }
    }

    public static void main(String[] args) {
        // 初始化两种西瓜
        int[] bananaWatermelonArray = {-1, 1, 5, 60};
        int[] yzmWatermelonArray = {-2, 2, 10, 80};

        List<BananaWatermelon> mergeWatermelons = merge(bananaWatermelonArray, yzmWatermelonArray);

        List<BananaWatermelon> filterWatermelons = filter(mergeWatermelons);

        writeWatermelonReport(filterWatermelons);

        List<BananaWatermelon> sortWatermelons = sendoutWatermelons(filterWatermelons);

        countingWatermelons(sortWatermelons);
    }

    /**
     * 把两种西瓜使用 stream 遍历，然后 Function 转换为同一种西瓜。
     *
     * @param firstArray
     * @param secondArray
     * @return
     */
    private static List<BananaWatermelon> merge(int[] firstArray, int[] secondArray) {
        int maxLength = firstArray.length + secondArray.length;
        List<BananaWatermelon> merges = new ArrayList<>(maxLength);

        Arrays.stream(firstArray).forEach(a -> merges.add(new BananaWatermelon(a)));
        Arrays.stream(secondArray).forEach(a -> merges.add(new BananaWatermelon(a)));

        return merges;
    }

    /**
     * 使用 Predicate 将西瓜中质量小0和质量大于50的瓜挑出来，丢掉。
     *
     * @param watermelons
     * @return
     */
    private static List<BananaWatermelon> filter(List<BananaWatermelon> watermelons) {
        List<BananaWatermelon> filterWatermelons = watermelons.stream().filter(a -> a.bananaQuality >= 0 && a.bananaQuality <= 50).collect(Collectors.toList());
        printArray(filterWatermelons);
        return filterWatermelons;
    }

    private static void printArray(List<BananaWatermelon> watermelons) {
        int[] bananArray = new int[watermelons.size()];
        int index = 0;
        for (BananaWatermelon bananaWatermelon : watermelons) {
            bananArray[index++] = bananaWatermelon.bananaQuality;
        }
        SysytemUtil.printArry(bananArray);
        System.out.println();
    }

    /**
     * 使用 Consumer 创建出5个检查人员，每个检查人员都会检查每个西瓜，
     * 使用 System.out.println("X 号检察员检查第 N 个西瓜，质量为 Y 完毕")。
     * 该过程使用多线程完成。 也就是说我们会创建出 50 个线程，
     * 待所有检查人员检查完成后（使用 CountDownlatch 来确认所有线程都执行完成了），
     * 观察所有的检验报告。
     *
     * @param filterWatermelons
     */
    private static void writeWatermelonReport(List<BananaWatermelon> filterWatermelons) {
        final CountDownLatch countDownLatch = new CountDownLatch(5 * filterWatermelons.size());
        BiConsumer<BananaWatermelon, Integer> consumer1 = (watermelon, index) -> {
            new Thread(() -> {
                System.out.println("1 号检察员检查第 " + index + " 个西瓜，质量为 " + watermelon.bananaQuality + " 完毕");
                countDownLatch.countDown();
            }
            ).start();
        };
        BiConsumer<BananaWatermelon, Integer> consumer2 = (watermelon, index) ->  {
            new Thread(() -> {
                System.out.println("2 号检察员检查第 " + index + " 个西瓜，质量为 " + watermelon.bananaQuality + " 完毕");
                countDownLatch.countDown();
            }
            ).start();
        };
        BiConsumer<BananaWatermelon, Integer> consumer3 = (watermelon, index) ->  {
            new Thread(() -> {
                System.out.println("3 号检察员检查第 " + index + " 个西瓜，质量为 " + watermelon.bananaQuality + " 完毕");
                countDownLatch.countDown();
            }
            ).start();
        };
        BiConsumer<BananaWatermelon, Integer> consumer4 = (watermelon, index) ->  {
            new Thread(() -> {
                System.out.println("4 号检察员检查第 " + index + " 个西瓜，质量为 " + watermelon.bananaQuality + " 完毕");
                countDownLatch.countDown();
            }
            ).start();
        };
        BiConsumer<BananaWatermelon, Integer> consumer5 = (watermelon, index) ->  {
            new Thread(() -> {
                System.out.println("5 号检察员检查第 " + index + " 个西瓜，质量为 " + watermelon.bananaQuality + " 完毕");
                countDownLatch.countDown();
            }
            ).start();
        };
        int index = 1;
        for (BananaWatermelon watermelon : filterWatermelons) {
            consumer1.accept(watermelon, index);
            consumer2.accept(watermelon, index);
            consumer3.accept(watermelon, index);
            consumer4.accept(watermelon, index);
            consumer5.accept(watermelon, index);
            index++;
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把现在还剩下的西瓜质量按顺序打印出来，格式 [1，3，4，5，6]。
     *
     * @param filterWatermelons
     */
    private static List<BananaWatermelon> sendoutWatermelons(List<BananaWatermelon> filterWatermelons) {
        List<BananaWatermelon> sortWatermelons = filterWatermelons.stream().sorted(Comparator.comparingInt(x -> x.bananaQuality)).collect(Collectors.toList());
        printArray(sortWatermelons);
        return sortWatermelons;
    }

    /**
     * 使用 reduce 计算一下，最终这批西瓜总计有多重，并打印出来
     *
     * @param filterWatermelons
     */
    private static void countingWatermelons(List<BananaWatermelon> filterWatermelons) {
        int sum = filterWatermelons.stream().mapToInt(a -> a.bananaQuality).sum();
        System.out.println("这批西瓜总计有" + sum + "重!");
    }

}
