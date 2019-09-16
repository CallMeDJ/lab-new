package com.bigbanana.lab.CombitionSum;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

public class Fix2 {


	public static void main(String[] args){

		char[] chars = valueOf(1120123012);

		System.out.println(Arrays.toString(chars));

	}



	public static char[] valueOf(Integer value){
		List<Integer> values = Lists.newArrayList();

		int currentValue = value;

		while (currentValue != 0){
			int nextValue = currentValue % 10;
			values.add(nextValue);
			currentValue = currentValue / 10;
		}


		int size = values.size();
		char[] result = new char[size];


		for(int i = size - 1 ; i >= 0 ; i--){
			result[size - 1 - i] = (char)('0' + values.get(i));
		}

	return result;
	}
}
