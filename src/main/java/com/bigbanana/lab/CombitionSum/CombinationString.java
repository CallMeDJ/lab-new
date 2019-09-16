package com.bigbanana.lab.CombitionSum;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class CombinationString {
	public static void main(String[] args){
		String target = "abcd";
		System.out.println(combineUnifiy(target));

	}

	static ArrayList<String> result = Lists.newArrayList();
	static Set<String> resultSet = Sets.newHashSet();
	static String target ;

	/**
	 * 如何unifiy
	 * @param str
	 * @return
	 */


	public static Set<String> combineUnifiy(String str){
		target = str;
		helper("" , 0 , target.length());

		return resultSet;
	}



	public static ArrayList<String> combine(String str){
		target = str;
		helper("" , 0 , target.length());

		return result;
	}




	private static void add2Set(String current){
		char[] chars = current.toCharArray();
		Arrays.sort(chars);

		String sorted = new String(chars);

		resultSet.add(sorted);

	}


	public static void helper(String current , int index , int length){
		if(current.length() > length){
			return ;
		}

		if(current.length() == length){
			add2Set(current);
		}

		for(int i = index ; i < length ; i++){
			String newStr = current+target.charAt(i);
			helper(newStr , i , length);
		}






	}



}
