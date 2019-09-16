package homework.linxd.Session1;

import com.bigbanana.lab.utils.Printer;

import javax.validation.constraints.Size;

public class SessionWatermelon {
	public static void main(String[] args) {
		int[] buyNum = new int[] {0, -1, 25 , 50, 60};
		int[] sellNum = sell(buyNum);
		printArray(sellNum);
	}

	/**
	 * 多个销售
	 * @param buyNum 购买的数量的数组
	 * @return 卖出数量的数组
	 */
	public static int[] sell(int[] buyNum){
		if (buyNum == null || buyNum.length <= 0) {
			return new int[0];
		}
		int size = buyNum.length;
		int[] sellNum = new int[size];
		int sum = 0;

		//按顺序购买
		for (int i = 0; i < size; i++) {
			int sell = 0;

			//一个个购买
			try {
				sell = sell0(buyNum[i]);
			} catch (Exception e) {
				//异常出现说明购买数为负数，不卖
				sell = 0;
			}

			//将卖出的数量添加到数组中
			sellNum[i] = sell;
			//将卖出的数量增加到总和中
			sum += sell;

		}
		System.out.println("linxd总共卖出去" + sum + "个。");

		return sellNum;
	}

	/**
	 * 单个销售
	 * @param buyNum 购买的数量
	 * @return 卖出数量
	 */
	private static int sell0(int buyNum) throws Exception{

		//分情况出售
		if (buyNum > 50) {
			//多与50只卖50
			return 50;
		} else if (buyNum < 0) {
			//少于0抛异常
			throw new RuntimeException();
		} else {
			//其他的买多少卖多少
			return buyNum;
		}

	}

	/**
	 * 打印数组
	 * @param arr 要打印的数组
	 */
	private static void printArray(int [] arr) {
		if (arr == null || arr.length <= 0) {
			System.out.print("[]");
			return;
		}
		System.out.print("[");

		int size = arr.length;

		for (int i = 0; i < size; i++) {
			//除了最后一个元素不用打印逗号，其他都要在后面加逗号
			if (i != size-1) {
				System.out.print(arr[i] + ",");
			} else {
				System.out.print(arr[i]);
			}
		}

		System.out.print("]");
	}
}
