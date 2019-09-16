package homework.makersy.session3;


import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WatermelonPipeline {

	/**
	 * 记录当前检查到第几个西瓜
	 */
	private static AtomicInteger index = new AtomicInteger(1);

	//检查人员数量
	private static final int INSPECTOR_NUM = 5;

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

		List<CommonWatermelon> filteredWatermelon = filterWatermelons(commonWatermelons);

		writeWatermelonReport(filteredWatermelon);

		sendoutWatermelons(filteredWatermelon);

		countingWatermelons(filteredWatermelon);

	}


	private static List<CommonWatermelon> mergeWatermelons(List<BananaWatermelon> bananaWatermelons, List<AppleWatermelon> appleWatermelons){
		//这里是需要你自己实现的
		// 1、把两种西瓜使用 stream 遍历，然后 Function 转换为同一种西瓜。
		List<CommonWatermelon> res = new ArrayList<>();

		if (bananaWatermelons == null && appleWatermelons == null) {
			throw new NullPointerException();
		}

		//转换方法
		Function<BananaWatermelon, CommonWatermelon> banana2Common = a -> new CommonWatermelon(a.bananaQuantity);
		Function<AppleWatermelon, CommonWatermelon> apple2Common = a -> new CommonWatermelon(a.appleQuantity);

		//stream遍历
		res.addAll(bananaWatermelons.stream().map(banana2Common).collect(Collectors.toList()));
		res.addAll(appleWatermelons.stream().map(apple2Common).collect(Collectors.toList()));

		return res;
	}




	private static List<CommonWatermelon> filterWatermelons(List<CommonWatermelon> filterWatermelons){
		//这里是需要你自己实现的
		//2、使用 Predicate 将西瓜中质量小0和质量大于50的瓜挑出来，丢掉。

		//predicate 作为filter传入参数，判断用
		Predicate<CommonWatermelon> predicate = a -> a.quantity>=0;
		predicate = predicate.and(a -> a.quantity <= 50);
		/*
		 * filter:
		 * Returns a stream consisting of the elements of this stream that match
		 * the given predicate.
		 * 返回的是 符合条件 的数据
		 */

		return filterWatermelons.stream().filter(predicate).collect(Collectors.toList());

	}




	private static void writeWatermelonReport(List<CommonWatermelon> filterWatermelons){
		//这里是需要你自己实现的
		//2、使用 Consumer 创建出5个检查人员，每个检查人员都会检查每个西瓜，使用 System.out.println("X 号检察员检查第 N 个西瓜，质量为 Y 完毕")。该过程使用多线程完成。
		//  也就是说我们会创建出 5 * N 个线程，待所有检查人员检查完成后（使用 CountDownlatch 来确认所有线程都执行完成了），观察所有的检验报告。

		//对应5个检察人员，每个人员全部检查完毕后才会倒计时结束
		CountDownLatch countDownLatch = new CountDownLatch(INSPECTOR_NUM * filterWatermelons.size());

		//consumer 新建5个线程
		Consumer<CommonWatermelon> commonWatermelonConsumer1 = (commonWatermelon -> {
			int watermelonIndex = index.getAndIncrement();
			for (int i = 1; i <= 5; i++) {
				new Thread(() -> {
					System.out.println(Thread.currentThread().getName() + "号检察员检查第 " + watermelonIndex + " 个西瓜，质量为 " + commonWatermelon.quantity + "。 完毕。");
					countDownLatch.countDown();
				}, "" + i).start();
			}
		});

		filterWatermelons.forEach(commonWatermelonConsumer1);

		//如果没有全部报告都写完，阻塞在这里不允许返回。
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			throw new IllegalThreadStateException("CountDownLatch Exception");
		} finally {
			System.out.println("检查完成！");
		}
	}


	/**
	 * 打印每个西瓜质量
	 */
	private static void sendoutWatermelons(List<CommonWatermelon> commonWatermelons){
		System.out.print("[");
		int size = commonWatermelons.size();
		if (size > 0) {
			for (int i = 0; i < commonWatermelons.size(); i++) {
				if (i != 0) {
					System.out.print(",");
				}
				System.out.print(commonWatermelons.get(i).quantity);
			}
		}
		System.out.println("]");
	}

	/**
	 * 返回西瓜数量
	 */
	private static void countingWatermelons(List<CommonWatermelon> commonWatermelons) {
//		System.out.println(commonWatermelons.size());
		long res = commonWatermelons.stream().mapToLong(a->1L).reduce(0, (a, b)->a+b);
		System.out.println(res);
	}

}
