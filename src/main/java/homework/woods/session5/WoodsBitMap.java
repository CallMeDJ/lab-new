package homework.woods.session5;

import homework.woods.utils.WoodsHashUtils;

/**
 * 去重统计的 BitMap
 * @param <K>
 */
public class WoodsBitMap<K> implements CountingInterface<K> {

	public static final int DEFAULT_CAPACITY = 16;
	private int size;
	private byte[] data;

	public WoodsBitMap(){
		new WoodsBitMap(DEFAULT_CAPACITY);
	}

	public WoodsBitMap(int capacity)
	{
		data = new byte[capacity];
	}

	@Override
	public boolean add(K key) {
		try {
			int index = getIndex(key);
			if (data[index] == 0) {
				data[index] = 1;
				size ++;
			}
			return true;
		}catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int size() {
		return size;
	}

	public boolean containsKey(K key)
	{
		try {
			int index = getIndex(key);
			if (data[index] == 1)
			{
				return true;
			}
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	private int getIndex(K key) throws Exception
	{
		int digest;
		try {
			digest = new WoodsHashUtils("SHA-256").getHash(key.toString());
		}catch (Exception e)
		{
			throw new Exception("计算角标异常");
		}
		return digest % data.length;
	}
}
