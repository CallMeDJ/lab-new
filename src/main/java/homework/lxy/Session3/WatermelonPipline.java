package homework.lxy.Session3;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
		// 1、把两种西瓜使用 stream 遍历，然后 Function 转换为同一种西瓜。
		List<CommonWatermelon> commonWatermelonList = new ArrayList<>();
		//定义BananaWaterMelon的合并方法
		Function<BananaWatermelon,CommonWatermelon> bananaFunction = (BananaWatermelon b)->{
			CommonWatermelon commonWatermelon = new CommonWatermelon(b.bananaQuantity);
			return commonWatermelon;
		};
		//定义AppleWaterMelon的合并方法
		Function<AppleWatermelon,CommonWatermelon> appleFunction = (AppleWatermelon a)->{
			CommonWatermelon commonWatermelon = new CommonWatermelon(a.appleQuantity);
			return commonWatermelon;
		};

		//转换
		if(!CollectionUtils.isEmpty(bananaWatermelons)){
			commonWatermelonList.addAll(bananaWatermelons.stream().map(bananaFunction).collect(Collectors.toList()));
		}
		//转换
		if(!CollectionUtils.isEmpty(appleWatermelons)){
			commonWatermelonList.addAll(appleWatermelons.stream().map(appleFunction).collect(Collectors.toList()));
		}

		return commonWatermelonList;
	}




	public static List<CommonWatermelon> filterWatermelons(List<CommonWatermelon>  filterWatermelons){
		//2、使用 Predicate 将西瓜中质量小0和质量大于50的瓜挑出来，丢掉。
		Predicate<CommonWatermelon> predicate = c -> (c.quantity >= 0 && c.quantity <= 50);
//		if(!CollectionUtils.isEmpty(filterWatermelons)){
//			Iterator<CommonWatermelon> it = filterWatermelons.iterator();
//			while(it.hasNext()){
//				CommonWatermelon c = it.next();
//				if(!predicate.test(c)){
//					it.remove();//这样的写法不会抛ConcurrentModificationException，同样适用于Map
//				}
//			}
//		}
		return filterWatermelons.stream().filter(predicate).collect(Collectors.toList());

	}




	public static void writeWatermelonReport(List<CommonWatermelon>  filterWatermelons){
		//2、使用 Consumer 创建出5个检查人员，每个检查人员都会检查每个西瓜，使用 System.out.println("X 号检察员检查第 N 个西瓜，质量为 Y 完毕")。该过程使用多线程完成。
		//  也就是说我们会创建出 5 * N 个线程，待所有检查人员检查完成后（使用 CountDownlatch 来确认所有线程都执行完成了），观察所有的检验报告。
		int consumerNum = 5;
		AtomicInteger watermelonIndex = new AtomicInteger(1);
		CountDownLatch countDownLatch = new CountDownLatch(consumerNum*filterWatermelons.size());
		Consumer<CommonWatermelon> commonWatermelonConsumer1 = (commonWatermelon -> {
			for(int i = 1; i<= consumerNum; i++){
				new Thread(() -> {
					System.out.println(Thread.currentThread().getName()+"号检察员检查第 "+watermelonIndex.getAndIncrement()+" 个西瓜，质量为"+commonWatermelon.quantity+" 完毕");
					countDownLatch.countDown();
				},i+"").start();
			}
		});

		for(CommonWatermelon commonWatermelon : filterWatermelons){
			commonWatermelonConsumer1.accept(commonWatermelon);
		}

		//如果没有全部报告都写完，阻塞在这里不允许返回。
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			System.out.println("InterruptedException occur,"+e.getMessage());
		}
	}


	public static void sendoutWatermelons(List<CommonWatermelon>  commonWatermelons){
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		commonWatermelons.forEach(commonWatermelon -> sb.append(commonWatermelon.quantity).append(","));
		sb.deleteCharAt(sb.toString().length()-1);
		sb.append("]");
		System.out.println(sb.toString());
	}


	public static void countingWatermelons(List<CommonWatermelon>  commonWatermelons){
		System.out.println(commonWatermelons.stream().map(commonWatermelon -> 1L).reduce(0L,(x,y) -> x+y));
	}


}
