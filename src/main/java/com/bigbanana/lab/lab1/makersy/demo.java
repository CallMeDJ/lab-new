package com.bigbanana.lab.lab1.makersy;

import org.junit.Test;

import java.util.Arrays;
import java.util.Objects;

public class demo extends com.bigbanana.lab.lab1.CalculateSum{


	@Override
	public long sum(Integer... numbers){

		if( numbers==null || numbers.length==0 ) {
			return 0L;
		} else {
			return Arrays.stream(numbers)
					.filter(Objects::nonNull)
					.map(Integer::longValue)
					.reduce(0L, (a, b) -> (a + b));
		}

	}

	@Test
	public void myTest(){
		demo demo = new demo();
		Integer[] arr = {1, 3, 4, 5};
		System.out.println(sum(arr));
	}


}
