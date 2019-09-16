package homework.tonemy.session5;

import java.util.BitSet;

/**
 * 去重统计的布隆过滤器
 * @param <K>
 */
public class BloomFilter<K> implements CountingInterface<K> {
	private static final int DEFAULT_SIZE = 2 << 24;//布隆过滤器的比特长度
	private static final int[] seeds = {3,5,7, 11, 13, 31, 37, 61};//这里要选取质数，能很好的降低错误率
	private static BitSet bits = new BitSet(DEFAULT_SIZE);
	private static SimpleHash[] func = new SimpleHash[seeds.length];
	private static int count = 0;
	public BloomFilter() {
		for (int i = 0; i < seeds.length; i++) {
			func[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);
		}
	}
	@Override
	public void add(K key) {
		if(key == null) return;
		boolean ret = true;
		for(SimpleHash simpleHash : func) //只要一次ret==false那么就不包含这个字符串
			ret = ret && bits.get(simpleHash.hash((String) key));
		if(ret) return;
		this.count ++;
		for(SimpleHash simpleHash : func)//将字符串value哈希为8个或多个整数，然后在这些整数的bit上变为1
			bits.set(simpleHash.hash((String) key),true);

	}

	@Override
	public int size() {
		return this.count;
	}

	public static class SimpleHash {

		private int cap;
		private int seed;

		public SimpleHash(int cap, int seed) {
			this.cap = cap;
			this.seed = seed;
		}

		public int hash(String value) {//字符串哈希
			int result = 0;
			int len = value.length();
			for (int i = 0; i < len; i++) {
				result = seed * result + value.charAt(i);
			}
			return (cap - 1) & result;
		}

	}
}
