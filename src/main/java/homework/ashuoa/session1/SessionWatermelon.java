package homework.ashuoa.session1;

// 逻辑：
// 1、如果没顾客，那就不卖
// 2、来了N个顾客，要按顺序卖，不卖的客户也要告诉他不卖（卖0）。
// 3、单个卖的逻辑在 sell0 实现。
// 4、如果超过50个，只卖50个。
// 5、如果需求小于0个，sell0 抛出异常，在 sell 捕获异常并登记为不卖（卖0）。
// 5、在控制台打印出最终这批卖出去多少个。术语：xxx总共卖出去N个。
// 6、实现自己的打印数组函数。格式: [1,2,3,4,5];
public class SessionWatermelon {
	public static void main(String[] args) {
		int[] nums = { 0, 1, 2, 3, 4, 5 };// init nums

		int[] rNums = sell(nums);// result

		printer(rNums);// print
	}

	public static int[] sell(int[] buyNum) {
		if (buyNum == null || buyNum.length == 0) {
			System.out.println("没顾客");
			return new int[0];
		}

		int[] rNums = new int[buyNum.length];
		int total = 0;

		for (int i = 0; i < buyNum.length; i++) {
			try {
				rNums[i] = sell0(buyNum[i]);
				total += rNums[i];
			} catch (Exception e) {
				rNums[i] = 0;
			}
		}

		System.out.println("阿硕啊总共卖出去" + total + "个");
		return rNums;
	}

	private static int sell0(int buyNum) throws Exception {
		if (buyNum > 50) {
			return 50;
		}

		if (buyNum < 0) {
			throw new Exception("小于0");
		}

		return buyNum;
	}

	private static void printer(int[] rNums) {
		System.out.print("[");
		for (int i = 0; i < rNums.length; i++) {
			if (i == rNums.length - 1) {
				System.out.print(rNums[i]);
			} else {
				System.out.print(rNums[i] + ",");
			}
		}
		System.out.print("]");
	}
}