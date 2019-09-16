package com.bigbanana.lab.Session3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class WatermelonPipeline {


  public static class BananaWatermelon{
    int bananaQuality;
    public BananaWatermelon(int bananaQuality){
      this.bananaQuality = bananaQuality;
    }
  }


  public static class AppleWatermelon{
    int appleQualtity;
    public AppleWatermelon(int appleQualtity){
      this.appleQualtity = appleQualtity;
    }
  }


  public static class CommonWatermelon{
    int quality;
    public CommonWatermelon(int quality){
      this.quality = quality;
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


  public static List<CommonWatermelon> mergeWatermelons(List<BananaWatermelon> bananaWatermelons, List<AppleWatermelon> appleWatermelons) {
    //TODO 这里是需要你自己实现的
    // 1、把两种西瓜使用 stream 遍历，然后 Function 转换为同一种西瓜。

    return null;
  }


  public static List<CommonWatermelon> filterWatermelons(List<CommonWatermelon> filterWatermelons) {
    //TODO 这里是需要你自己实现的
    //2、使用 Predicate 将西瓜中质量小0和质量大于50的瓜挑出来，丢掉。

    return null;
  }


  public static void writeWatermelonReport(List<CommonWatermelon> filterWatermelons) {
    //TODO 这里是需要你自己实现的
    //2、使用 Consumer 创建出5个检查人员，每个检查人员都会检查每个西瓜，使用 System.out.println("X 号检察员检查第 N 个西瓜，质量为 Y 完毕")。该过程使用多线程完成。
    //  也就是说我们会创建出 5 * N 个线程，待所有检查人员检查完成后（使用 CountDownlatch 来确认所有线程都执行完成了），观察所有的检验报告。

    Consumer<CommonWatermelon> commonWatermelonConsumer1 = (commonWatermelon -> {
    });

    for (CommonWatermelon commonWatermelon : filterWatermelons) {

    }

    //如果没有全部报告都写完，阻塞在这里不允许返回。
  }


  public static void sendOutWatermelons(List<CommonWatermelon> commonWatermelons) {

  }


  public static void countingWatermelons(List<CommonWatermelon> commonWatermelons) {

  }
}
