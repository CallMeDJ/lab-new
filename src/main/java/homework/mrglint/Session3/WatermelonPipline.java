package homework.mrglint.Session3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WatermelonPipline {

	private static AtomicInteger watermelonNumberGenerator = new AtomicInteger(1);
	private static AtomicInteger checkerNumberGenerator = new AtomicInteger(1);
	private static CountDownLatch countDownLatch;

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


	public static void main(String[] args) {

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
		Function<BananaWatermelon, CommonWatermelon> bananaWatermelonTransverter = (e -> new CommonWatermelon(e.bananaQuantity));
		List<CommonWatermelon> commonWatermelons = bananaWatermelons.stream().map(bananaWatermelonTransverter).collect(Collectors.toList());

		Function<AppleWatermelon, CommonWatermelon> appleWatermelonTransverter = (e -> new CommonWatermelon(e.appleQuantity));
		commonWatermelons.addAll(appleWatermelons.stream().map(appleWatermelonTransverter).collect(Collectors.toList()));
		return  commonWatermelons;
	}




	public static List<CommonWatermelon> filterWatermelons(List<CommonWatermelon>  filterWatermelons){
		//TODO 这里是需要你自己实现的
		//2、使用 Predicate 将西瓜中质量小0和质量大于50的瓜挑出来，丢掉。
		Predicate<CommonWatermelon> quantityFilter = (e -> e.quantity > 0 && e.quantity < 50);
		return filterWatermelons.stream().filter(quantityFilter).collect(Collectors.toList());

	}


	public static void writeWatermelonReport(List<CommonWatermelon>  filterWatermelons){
		//TODO 这里是需要你自己实现的
		//2、使用 Consumer 创建出5个检查人员，每个检查人员都会检查每个西瓜，使用 System.out.println("X 号检察员检查第 N 个西瓜，质量为 Y 完毕")。该过程使用多线程完成。
		//  也就是说我们会创建出 5 * N 个线程，待所有检查人员检查完成后（使用 CountDownlatch 来确认所有线程都执行完成了），观察所有的检验报告。

        countDownLatch = new CountDownLatch(filterWatermelons.size() * 5);

		Consumer<CommonWatermelon> commonWatermelonConsumer1 = (commonWatermelon -> {
		    int watermelonNumber = watermelonNumberGenerator.getAndIncrement();
		    for (int i = 0; i < 5; i++) {
                Thread thread = new Thread(() -> {
                    System.out.println(String.format("%s 号检察员检查第 %s 个西瓜，质量为 %s 完毕", checkerNumberGenerator.getAndIncrement(), watermelonNumber, commonWatermelon.quantity));
                    countDownLatch.countDown();
                });
                thread.start();
            }
        });

		for(CommonWatermelon commonWatermelon : filterWatermelons){
            commonWatermelonConsumer1.accept(commonWatermelon);
		}

		//如果没有全部报告都写完，阻塞在这里不允许返回。
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new IllegalThreadStateException("countdownlatch await exception");
        }

    }


	public static void sendoutWatermelons(List<CommonWatermelon>  commonWatermelons){
        commonWatermelons.sort(Comparator.comparing(commonWatermelon -> commonWatermelon.quantity));
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i <= commonWatermelons.size() - 1; i++) {
            result.append(commonWatermelons.get(i).quantity);
            if (i != (commonWatermelons.size() - 1)) {
                result.append(", ");
            }
        }
        result.append("]");
        System.out.println(result.toString());
	}


	public static void countingWatermelons(List<CommonWatermelon>  commonWatermelons){
        Integer count = commonWatermelons.stream().map(e -> 1).reduce(0, Integer::sum);
        System.out.println(String.format("这批西瓜总计有 %s 个", count));
    }





}
