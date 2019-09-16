package com.bigbanana.lab.lab1;

import org.junit.Assert;
import org.junit.Test;

public abstract class CalculateSum {


	/**
	 * 给定一个Integer 数组，计算这个数组的和
	 * @param numbers
	 * @return
	 */
	public abstract long sum(Integer... numbers);


	public void test(CalculateSum calculateSum){
		Integer[] test1 = null;
		Assert.assertEquals(calculateSum.sum(test1),0L);

		Integer[] test2 = new Integer[2];
		test2[1] = 10;
		Assert.assertEquals(calculateSum.sum(test2),10L);


		Integer[] test3 = new Integer[2];
		test2[0] = Integer.MAX_VALUE;
		test2[1] = 10;
		Assert.assertEquals(calculateSum.sum(test2),Long.valueOf(Integer.MAX_VALUE).longValue() + 10L);


	}


}
