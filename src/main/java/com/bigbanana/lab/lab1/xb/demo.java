package com.bigbanana.lab.lab1.xb;

import com.bigbanana.lab.lab1.CalculateSum;
import org.junit.Test;

public class demo extends CalculateSum{


	@Override
	public long sum(Integer... numbers){

		Integer sum_ = 0;

		Integer len = numbers.length;
		for (int i = 0;i<len;i++){
			sum_ += numbers[i];
		}


		return sum_;

	}

	@Test
	public void myTest(){
		demo demo = new demo();
		super.test(demo);
	}

}
