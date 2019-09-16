package homework.zhou.session3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WatermelonPipline {

    /**
     * 记录当前检查到第几个西瓜
     */
    private static AtomicInteger index = new AtomicInteger(1);

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

		List<CommonWatermelon>list1 = bananaWatermelons.stream().map(b -> new CommonWatermelon(b.bananaQuantity))
																	.collect(Collectors.toList());
		List<CommonWatermelon>list2 = appleWatermelons.stream().map(a -> new CommonWatermelon(a.appleQuantity))
																	.collect(Collectors.toList());
		//将两个list合并为一个
		return Stream.of(list1,list2).flatMap(c -> c.stream()).collect(Collectors.toList());
	}




	public static List<CommonWatermelon> filterWatermelons(List<CommonWatermelon>  filterWatermelons){
		//TODO 这里是需要你自己实现的
		//2、使用 Predicate 将西瓜中质量小0和质量大于50的瓜挑出来，丢掉。
		return filterWatermelons.stream()
				.filter(f->(f.quantity>=0 && f.quantity<=50))
				.collect(Collectors.toList());
	}




	public static void writeWatermelonReport(List<CommonWatermelon>  filterWatermelons){
		//TODO 这里是需要你自己实现的
		//2、使用 Consumer 创建出5个检查人员，每个检查人员都会检查每个西瓜，使用 System.out.println("X 号检察员检查第 N 个西瓜，质量为 Y 完毕")。该过程使用多线程完成。
		//  也就是说我们会创建出 5 * N 个线程，待所有检查人员检查完成后（使用 CountDownlatch 来确认所有线程都执行完成了），观察所有的检验报告。

        CountDownLatch countDownLatch = new CountDownLatch(5*filterWatermelons.size());
		Consumer<CommonWatermelon> commonWatermelonConsumer1 = (commonWatermelon -> {
            int count = index.getAndIncrement();
            for (int i=1; i<6; i++){
                int finalI = i;
                new Thread(()->{
                    System.out.println(finalI +"号检察员检查第"+count+"个西瓜，质量为"+commonWatermelon.quantity+" 完毕");
                    countDownLatch.countDown();
                }).start();
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
	    System.out.print("[");
		for (int i=0; i < commonWatermelons.size();i++ ){
			if (i == 0){
				System.out.print(commonWatermelons.get(i).quantity);
			}
			else {
				System.out.print(","+commonWatermelons.get(i).quantity);
			}
		}
		System.out.println("]");

	}


	public static void countingWatermelons(List<CommonWatermelon>  commonWatermelons){
		int count = commonWatermelons.stream().map(c->c.quantity).reduce((a, b) -> a + b).orElse(0);
		System.out.println(count);

	}





}
