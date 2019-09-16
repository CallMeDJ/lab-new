package homework.mrglint.Session5;

/**
 * 去重统计的布隆过滤器
 * @param <K>
 */
public class BananaBloomFilter<K> implements CountingInterface<K> {

	/**
	 * 存储数据的byte数组
	 */
	private byte[] bitsMap;
	private int size;

	/**
	 * @param length 布隆过滤器的最大长度。值越大误差越小
	 */
	public BananaBloomFilter(long length) {
		this.size = 0;
		// length >> 3 相当于 length / 8.
		// length & 7 相当于 length % 8 用8对length取模.
		// 计算出存储数据的数组长度
		bitsMap = new byte[(int) (length >> 3) + ((length & 7) > 0 ? 1 : 0)];
	}

	@Override
	public void add(K key) {
		long keyHash = hash(key);
		// 在bitsMap中的索引
		int bitsMapIndex = (int) (keyHash >> 3);
		// 在byte中的偏移量
		int byteOffset = (int) (keyHash & 7);
		byte bitMapData = bitsMap[bitsMapIndex];
		// 将偏移量所在的bit置为1
		byte i = (byte) (0x01 << byteOffset);
		// 取出原来偏移量所在的bit
		byte byteWithOffset = (byte) (i & bitMapData);
		// 如果计算后的byte为0，那么key在此之前未添加
		if (byteWithOffset == 0){
			size++;
			// 置为1，表示添加key到bitmap中
			bitsMap[bitsMapIndex] = (byte) (bitMapData | (0x01 << byteOffset));
		}
	}

	/**
	 * 计算key的hash值
	 * 对于hash表的hash函数，只要hash值分布均匀、计算过程高效就符合要求
	 * @param key
	 * @return
	 */
	private int hash(Object key) {
		int h;
		int hashTmp = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
		return hashTmp % bitsMap.length;
	}

	@Override
	public int size() {
		return size;
	}

	public static void main(String[] args) {
		BananaBloomFilter<String> bloomFilter = new BananaBloomFilter<>(1000000000000L);
		bloomFilter.add("AA");
		System.out.println(bloomFilter.size());
		bloomFilter.add("BB");
		System.out.println(bloomFilter.size());
		bloomFilter.add("BB");
		bloomFilter.add("我是");
		System.out.println(bloomFilter.size());
	}
}
