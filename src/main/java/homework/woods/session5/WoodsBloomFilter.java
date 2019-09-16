package homework.woods.session5;

import homework.woods.utils.WoodsHashUtils;

import java.util.List;

/**
 * 去重统计的布隆过滤器
 * @param <K>
 * @description 理解原理， 对于 hash函数个数计算比较模糊， 看大蕉实现未涉及，那就这么写一下吧
 */
public class WoodsBloomFilter<K> implements CountingInterface<K> {

	public static final int DEFAULT_CAPACITY = 1 << 10;
	private int size;
	private byte[] data;
	private List<WoodsHashUtils> hashAlgorithm;

	public WoodsBloomFilter()
	{
		new WoodsBloomFilter(DEFAULT_CAPACITY);
	}

	public WoodsBloomFilter(int capacity)
	{
		data = new byte[capacity];
		// 忽略 误判率 及 hash算法个数的逻辑
		hashAlgorithm.add(new WoodsHashUtils("SHA-256"));
		hashAlgorithm.add(new WoodsHashUtils("MD5"));
	}

	@Override
	public boolean add(K key){
		boolean succ = false;
		try {
			for (WoodsHashUtils hashUtil : hashAlgorithm) {
				int hash = hashUtil.getHash(key.toString());
				int index = hash % data.length;

				if (data[index] == 0) {
					data[index] = 1;
					succ = true;
				}
			}
			if (succ)
			{
				size ++;
			}
			return true;
		}catch (Exception e)
		{
			return false;
		}
	}

	@Override
	public int size() {
		return size;
	}

	public boolean containsKey(K key)
	{
		boolean exist = true;
		try {
			for (WoodsHashUtils hashUtil : hashAlgorithm) {
				int hash = hashUtil.getHash(key.toString());
				int index = hash % data.length;

				if (data[index] == 0) {
					exist = false;
					break;
				}
			}
			return exist;
		}catch (Exception e)
		{
			return false;
		}
	}

}
