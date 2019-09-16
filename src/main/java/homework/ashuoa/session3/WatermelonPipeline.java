package homework.ashuoa.session3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.Function;
import java.util.function.ObjIntConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * WatermelonPipeline
 *
 * @author ashuoa
 */
public class WatermelonPipeline {

    public static class BananaWatermelon {
        int bananaQuality;

        public BananaWatermelon(int bananaQuality) {
            this.bananaQuality = bananaQuality;
        }
    }

    public static class AppleWatermelon {
        int appleQuality;

        public AppleWatermelon(int appleQualtity) {
            this.appleQuality = appleQualtity;
        }
    }

    public static class CommonWatermelon {
        int quality;

        public CommonWatermelon(int quality) {
            this.quality = quality;
        }
    }

    public static void main(String[] args) {
        // int[] bananaWatermelonArray = {-1, 0, 5, 60};
        int[] bananaWatermelonArray = {-1, 1, 2, 3, 4, 5, 6, 7};

        List<BananaWatermelon> bananaWatermelons = new ArrayList<>();

        for (int i = 0; i < bananaWatermelonArray.length; i++) {
            bananaWatermelons.add(new BananaWatermelon(bananaWatermelonArray[i]));
        }

        // int[] appleWatermelonArray = {-1, 0, 5, 60};
        int[] appleWatermelonArray = {-1, 1, 2, 3, 4, 5, 6, 7};

        List<AppleWatermelon> appleWatermelons = new ArrayList<>();

        for (int i = 0; i < appleWatermelonArray.length; i++) {
            appleWatermelons.add(new AppleWatermelon(appleWatermelonArray[i]));
        }

        List<CommonWatermelon> commonWatermelons = mergeWatermelons(bananaWatermelons, appleWatermelons);
        System.out.println();

        List<CommonWatermelon> filteredWatermelon = filterWatermelons(commonWatermelons);

        writeWatermelonReport(filteredWatermelon);

        sendoutWatermelons(filteredWatermelon);

        countingWatermelons(filteredWatermelon);
    }

    public static List<CommonWatermelon> mergeWatermelons(List<BananaWatermelon> bananaWatermelons, List<AppleWatermelon> appleWatermelons) {
        //这里是需要你自己实现的
        // 1、把两种西瓜使用 stream 遍历，然后 Function 转换为同一种西瓜。
        Function<BananaWatermelon, CommonWatermelon> banana2common = bw -> new CommonWatermelon(bw.bananaQuality);
        Function<AppleWatermelon, CommonWatermelon> apple2common = aw -> new CommonWatermelon(aw.appleQuality);

        List<CommonWatermelon> cw1 = bananaWatermelons.stream().map(banana2common).collect(Collectors.toList());
        List<CommonWatermelon> cw2 = appleWatermelons.stream().map(apple2common).collect(Collectors.toList());
        cw1.addAll(cw2);
        return cw1;
    }

    public static List<CommonWatermelon> filterWatermelons(List<CommonWatermelon> filterWatermelons) {
        //这里是需要你自己实现的
        // 2、使用 Predicate 将西瓜中质量小于0和质量大于50的瓜挑出来，丢掉。
        Predicate<CommonWatermelon> filterRule = w -> w.quality > 0 && w.quality <= 50;
        return filterWatermelons.stream().filter(filterRule).collect(Collectors.toList());
    }

    public static void writeWatermelonReport(List<CommonWatermelon> filterWatermelons) {
        //这里是需要你自己实现的
        // 2、使用 Consumer 创建出5个检查人员，每个检查人员都会检查每个西瓜，使用 System.out.println("X 号检察员检查第 N 个西瓜，质量为 Y 完毕")。该过程使用多线程完成。
        //    也就是说我们会创建出 5 * N 个线程，待所有检查人员检查完成后（使用 CountDownLatch 来确认所有线程都执行完成了），观察所有的检验报告。
        final int checkerNum = 5;
        CountDownLatch countDownLatch = new CountDownLatch(filterWatermelons.size() * checkerNum);

        ObjIntConsumer<CommonWatermelon> commonWatermelonConsumer1 = ((commonWatermelon, index) -> {
            for (int i = 1; i <= checkerNum; i++) {
                int checker = i;

                new Thread(() -> {
                    System.out.println(checker + " 号检察员检查第 " + index + " 个西瓜，质量为 " + commonWatermelon.quality + " 完毕");
                    countDownLatch.countDown();
                }).start();
            }
        });

        for (CommonWatermelon commonWatermelon : filterWatermelons) {
            commonWatermelonConsumer1.accept(commonWatermelon, filterWatermelons.indexOf(commonWatermelon) + 1);
        }

        //如果没有全部报告都写完，阻塞在这里不允许返回。
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("检查完毕");
        }
    }

    public static void sendoutWatermelons(List<CommonWatermelon> commonWatermelons) {
        //把现在还剩下的西瓜质量按顺序打印出来，格式 [1，3，4，5，6]。
        String result = "[" + commonWatermelons.stream().map(cw -> String.valueOf(cw.quality)).sorted().collect(Collectors.joining(",")) + "]";
        System.out.println(result);
    }

    public static void countingWatermelons(List<CommonWatermelon> commonWatermelons) {
        //使用 reduce 计算一下，最终这批西瓜总计有多重，并打印出来。
        int result = commonWatermelons.stream().map(cw -> cw.quality).reduce(Integer::sum).orElse(0);
        System.out.println("quality: " + result);
    }
}
