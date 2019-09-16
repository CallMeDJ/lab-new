package homework.tonemy.session5;



/**
 * 去重统计的 BitMap
 * @param <K>
 */
public class BitMap<K> implements CountingInterface<K> {

	//每个 byte的每一位bit可以用来表示 一个数字的有无,n 表示数字中最大的一个,比如10亿，
	//除以 8 再加一就是 bits数组的长度.
	byte[] bits;
	int count = 0;
	public BitMap(int n) {
		bits = new byte[n >> 3  + 1];
	}

	/**
	 * 检查 key 所在的bit位是否为1
	 * @param key
	 */
	@Override
	public void add(K key) {
		if (key == null) return;
		if(!(key instanceof Integer)) {
			return;
		}
		// 找到key在bits中的索引 这里假设key为整数
		int index =(Integer) key >> 8;
		// 找到key在bits[index]的位置
		int pos = (Integer) key & 0x07;
		// 检测
		if(!((bits[index] & 1 << pos) != 0)){
			this.count ++;
			// 变为1
			bits[index]  |= 1 << pos;
		}
	}

	/**
	 * 去重后的数量
	 * @return
	 */
	@Override
	public int size() {

		return this.count;
	}
}
