package com.bigbanana.lab.CombitionSum;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {


	public static void main(String[] args){
		int[] data = {1,2,3,6,7};
		System.out.println(combinationSum(data,7));

	}


	private static List<List<Integer>> res = new ArrayList<>();
	private static  int[] arr;
	private static int target;
	public static List<List<Integer>> combinationSum(int[] candidates, int t) {
		target = t;
		arr = candidates;
		helper(new ArrayList<>(), 0, 0);
		return res;
	}
	static int o = 0;

	private static void helper(ArrayList<Integer> list, int index, int sum){
		if(sum > target) return;
		if(sum == target) {
			res.add(list);
		}
		for(int i=index; i<arr.length; i++){
			ArrayList<Integer> copy = (ArrayList)list.clone();
			copy.add(arr[i]);
			helper(copy, i, sum+arr[i]);
		}
	}
}
