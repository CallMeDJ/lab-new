package com.bigbanana.lab.Combinations;

import com.google.common.util.concurrent.RateLimiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Combinations {




	static List<List<Integer>> result = new ArrayList<>();

	public List<List<Integer>> combine(int n, int k) {


		if (n <= 0 || k <= 0) {
			return result;
		}

		List<Integer> input = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			input.add(i + 1);
		}

		List<Integer> current = new ArrayList<>();




		return new ArrayList<>();
	}


	public static void main(String[] args){

		/**
		 * SmoothBuisty
		 */
		RateLimiter limiter = RateLimiter.create(1);

		for(int i = 1; i < 10; i = i + 2 ) {
			double waitTime = limiter.acquire(i);
			System.out.println("cutTime=" + System.currentTimeMillis() + " acq:" + i + " waitTime:" + waitTime);
		}


		RateLimiter smoothBuisty = RateLimiter.create(1);
		RateLimiter smoothWarmingUp = RateLimiter.create(1 , 1 , TimeUnit.SECONDS);

		smoothBuisty.acquire();
		smoothWarmingUp.acquire(5);
	}




	public static void helper(int n, int k, ArrayList<ArrayList<Integer>> combSet, ArrayList<Integer> comb, int start) {
			if(comb.size() == k){
				combSet.add(comb);
				return ;
			}


			for(int i = start ; i <= n ; i++){


			}




	}




	}
