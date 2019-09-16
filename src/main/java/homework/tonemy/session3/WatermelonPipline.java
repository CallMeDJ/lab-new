package homework.tonemy.session3;

import com.bigbanana.lab.utils.Printer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WatermelonPipline {

    public static class BananaWatermelon{
        int bananaQuantity;
        public BananaWatermelon(int bananaQuantity){
            this.bananaQuantity = bananaQuantity;
        }
    }


    public static class AppleWatermelon{
        int appleQuantity;
        public AppleWatermelon(int appleQuantity){
            this.appleQuantity = appleQuantity;
        }
    }


    public static class CommonWatermelon{
        int quantity;
        public CommonWatermelon(int quantity){
            this.quantity = quantity;
        }
    }


    public static void main(String[] args) throws InterruptedException {

        int[] bananaWatermelonArray = {-1,0,5,60};

        List<BananaWatermelon> bananaWatermelons = new ArrayList<>();

        for(int i = 0 ; i < bananaWatermelonArray.length ; i++){
            bananaWatermelons.add(new BananaWatermelon(bananaWatermelonArray[i]));
        }

        int[] appleWatermelonArray = {-1,0,5,60};

        List<AppleWatermelon> appleWatermelons = new ArrayList<>();

        for(int i = 0 ; i < appleWatermelonArray.length ; i++){
            appleWatermelons.add(new AppleWatermelon(appleWatermelonArray[i]));
        }


        List<CommonWatermelon> commonWatermelons = mergeWatermelons(bananaWatermelons, appleWatermelons);

        List<CommonWatermelon> filteredWatermenlon = filterWatermelons(commonWatermelons);

        writeWatermelonReport(filteredWatermenlon);

        sendoutWatermelons(filteredWatermenlon);

        countingWatermelons(filteredWatermenlon);

    }



    public static List<CommonWatermelon> mergeWatermelons(List<BananaWatermelon> bananaWatermelons,List<AppleWatermelon> appleWatermelons){
        //TODO 这里是需要你自己实现的
        // 1、把两种西瓜使用 stream 遍历，然后 Function 转换为同一种西瓜。
        List<CommonWatermelon> collect1 = bananaWatermelons.stream().map(bananaWatermelon -> new CommonWatermelon(bananaWatermelon.bananaQuantity)).collect(Collectors.toList());
        List<CommonWatermelon> collect2 = appleWatermelons.stream().map(appleWatermelon -> new CommonWatermelon(appleWatermelon.appleQuantity)).collect(Collectors.toList());
        //       flatMap
        List<CommonWatermelon> collect = Stream.of(collect1, collect2).flatMap(waterMelon -> waterMelon.stream()).collect(Collectors.toList());
        return  collect;
    }

    public static List<CommonWatermelon> filterWatermelons(List<CommonWatermelon>  filterWatermelons){
        //TODO 这里是需要你自己实现的
        //2、使用 Predicate 将西瓜中质量小0和质量大于50的瓜挑出来，丢掉。
        Predicate<CommonWatermelon> smaller = (waterMelon) -> waterMelon.quantity >= 0;
        Predicate<CommonWatermelon> bigger = (waterMelon) -> waterMelon.quantity <= 50;
        List<CommonWatermelon> collect = filterWatermelons.stream().filter(smaller.and(bigger)).collect(Collectors.toList());
        return collect;
    }




    public static void writeWatermelonReport(List<CommonWatermelon>  filterWatermelons) throws InterruptedException {
        //TODO 这里是需要你自己实现的
        //2、使用 Consumer 创建出5个检查人员，每个检查人员都会检查每个西瓜，使用 System.out.println("X 号检察员检查第 N 个西瓜，质量为 Y 完毕")。该过程使用多线程完成。
        //  也就是说我们会创建出 5 * N 个线程，待所有检查人员检查完成后（使用 CountDownlatch 来确认所有线程都执行完成了），观察所有的检验报告。
        AtomicInteger watermelonNumberGenerator = new AtomicInteger(1);
        CountDownLatch countDown = new CountDownLatch(5 * filterWatermelons.size());
        Consumer<CommonWatermelon> commonWatermelonConsumer1 = (commonWatermelon -> {
            int i = watermelonNumberGenerator.getAndIncrement();
            new Thread(() ->{countDown.countDown(); System.out.println("1 号检察员检查第 "+i+" 个西瓜，质量为 "+commonWatermelon.quantity +" 完毕");} ).start();
        });

        Consumer<CommonWatermelon> commonWatermelonConsumer2 = (commonWatermelon -> {
            int i = watermelonNumberGenerator.getAndIncrement();
            new Thread(() ->{countDown.countDown(); System.out.println("2 号检察员检查第 "+i+" 个西瓜，质量为 "+commonWatermelon.quantity +" 完毕");} ).start();
        });

        Consumer<CommonWatermelon> commonWatermelonConsumer3 = (commonWatermelon -> {
            int i = watermelonNumberGenerator.getAndIncrement();
            new Thread(() ->{countDown.countDown(); System.out.println("3 号检察员检查第 "+i+" 个西瓜，质量为 "+commonWatermelon.quantity +" 完毕");} ).start();
        });

        Consumer<CommonWatermelon> commonWatermelonConsumer4 = (commonWatermelon -> {
            int i = watermelonNumberGenerator.getAndIncrement();
            new Thread(() ->{countDown.countDown(); System.out.println("4 号检察员检查第 "+i+" 个西瓜，质量为 "+commonWatermelon.quantity +" 完毕");} ).start();
        });

        Consumer<CommonWatermelon> commonWatermelonConsumer5 = (commonWatermelon -> {
            int i = watermelonNumberGenerator.getAndIncrement();
            new Thread(() ->{countDown.countDown(); System.out.println("5 号检察员检查第 "+i+" 个西瓜，质量为 "+commonWatermelon.quantity +" 完毕");} ).start();
        });

        for(CommonWatermelon commonWatermelon : filterWatermelons){
            commonWatermelonConsumer1.accept(commonWatermelon);
            commonWatermelonConsumer2.accept(commonWatermelon);
            commonWatermelonConsumer3.accept(commonWatermelon);
            commonWatermelonConsumer4.accept(commonWatermelon);
            commonWatermelonConsumer5.accept(commonWatermelon);
        }

        //如果没有全部报告都写完，阻塞在这里不允许返回。
        countDown.await();

    }


    public static void sendoutWatermelons(List<CommonWatermelon>  commonWatermelons){
        List<Integer> collect = commonWatermelons.stream().map(commonWatermelon -> commonWatermelon.quantity).collect(Collectors.toList());
        Printer.println("最后的西瓜数量的列表为: ->"+collect);
    }


    public static void countingWatermelons(List<CommonWatermelon>  commonWatermelons){
        Integer sum = commonWatermelons.stream().map(commonWatermelon -> commonWatermelon.quantity).reduce((a, b) -> a + b).get();
        System.out.println("最后的西瓜数量的总和为: -> " + sum );
    }

}