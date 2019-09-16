package com.bigbanana.lab.CombitionSum;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum2 {


	public static void main(String[] args){
		int[] data = {2,3,6,7};
		System.out.println(combinationSum(data,7));

	}

	static List<List<Integer>> result = Lists.newArrayList();
	static int[] data ;
	static int target;


	public static List<List<Integer>> combinationSum(int[] array , int sum){
		data = array;
		target = sum;


		return result;
	}


	public static void helper(ArrayList<Integer> currentArray , int sum ,int index){
		if(sum  == target){
			result.add(currentArray);
		}


		if(sum > target){
			return ;
		}

		for(int i = index ; i < data.length ; i++){
			ArrayList<Integer> copy = (ArrayList<Integer>)currentArray.clone();
			helper(copy , sum + data[i] , i);
		}

	}

}
