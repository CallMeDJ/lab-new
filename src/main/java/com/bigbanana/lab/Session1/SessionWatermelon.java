package com.bigbanana.lab.Session1;

import com.bigbanana.lab.utils.Printer;

public class SessionWatermelon {
	public static void main(String[] args) {

		int[] nums1 = {-1,0,5,60};
		int[] sell = sell(nums1);
		Printer.printArray(sell);

	}

	public static int[] sell(int[] buyNum){
		if(buyNum == null){
			return new int[0];
		}

		if(buyNum.length == 0){
			return new int[0];
		}

		int[] result = new int[buyNum.length];
		int totalBuyNum = 0;

		for(int i = 0 ; i < buyNum.length ; i ++){
			int currenBuyNum = buyNum[i];
			int sellNum = 0;
			try {
				sellNum = sell0(currenBuyNum);
			}catch (Exception e){
				sellNum = 0;
			}


			result[i] = sellNum;
			totalBuyNum += sellNum;
		}

		System.out.println("大蕉总共卖出去" + totalBuyNum + "个");



		return result;
	}

	private static int sell0(int buyNum) throws Exception {
		if(buyNum < 0){
			throw new Exception("超卖了");
		}

		if(buyNum > 50){
			return 50;
		}


		return buyNum;
	}



}
