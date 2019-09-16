package com.bigbanana.lab.lab1.MrC;


import org.junit.Test;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class demo extends com.bigbanana.lab.lab1.CalculateSum{



	@Override
	public  long sum(Integer... numbers){
		long sum = 0;
		if(numbers.length != 0){

			for (Integer sum_: numbers ) {
				sum += sum_;
			}
		}

		return sum;
	}

	@Test
	public void myTest(){
		demo demo = new demo();
		super.test(demo);
	}


}
