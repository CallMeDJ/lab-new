package com.bigbanana.lab.Session5;

import java.util.BitSet;

/**
 * 去重统计的布隆过滤器
 * @param <K>
 */

public class BananaBloomFilter<K> implements CountingInterface<K>{
	private static final int DEFAULT_SIZE = 2 << 24;//布隆过滤器的比特长度
	private static final int[] seeds = {3,5,7, 11, 13, 31, 37, 61};//这里要选取质数，能很好的降低错误率
	private static BitSet bits = new BitSet(DEFAULT_SIZE);
	private static SimpleHash[] func = new SimpleHash[seeds.length];
	static int count = 0;

	@Override
	public void add(K key) {
		if(key == null) return;
		boolean ret = true;
		if(key instanceof String) {
			for(SimpleHash f : func) {
				//只要一次ret==false那么就不包含这个字符串
				ret = ret && bits.get(f.hash((String) key));
			}
			if (!ret) {
				//如果不包含就加一
				this.count ++;
			}

			for(SimpleHash simpleHash : func) {
				bits.set(simpleHash.hash((String) key), true);
			}
		}
	}

	@Override
	public int size() {
		return this.count;
	}

	private static class SimpleHash {
		private int cap;
		private int seed;

		public  SimpleHash(int cap, int seed) {
			this.cap = cap;
			this.seed = seed;
		}

		public int hash(String value) {
			int result = 0;
			int len = value.length();
			for (int i = 0; i < len; i++) {
				result = seed * result + value.charAt(i);
			}
			return (cap - 1) & result;
		}

	}
}
