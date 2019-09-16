package homework.mrglint.Session5;

/**
 * 去重统计的 BitMap
 * @author luhuancheng
 */
public class BananaBitMap implements CountingInterface<Long> {

	private int size;

	/**
	 * 存储数据的byte数组
	 */
	private byte[] bitsMap;

	/**
	 *
	 * @param length 位图长度,可以添加[0, length]
	 */
	public BananaBitMap(long length) {
		this.size = 0;
		// length >> 3 相当于 length / 8.
		// length & 7 相当于 length % 8 用8对length取模.
		// 计算出存储数据的数组长度
		bitsMap = new byte[(int) (length >> 3) + ((length & 7) > 0 ? 1 : 0)];
	}

	@Override
	public void add(Long key) {
		// 在bitsMap中的索引
		int bitsMapIndex = (int) (key >> 3);
		// 在byte中的偏移量
		int byteOffset = (int) (key & 7);
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

	@Override
	public int size() {
		return size;
	}

	public static void main(String[] args) {
		BananaBitMap bitMap = new BananaBitMap(10);
		bitMap.add(1L);
		System.out.println(bitMap.size());
		bitMap.add(2L);
		System.out.println(bitMap.size());
		bitMap.add(1L);
		bitMap.add(2L);
		System.out.println(bitMap.size());
	}
}
