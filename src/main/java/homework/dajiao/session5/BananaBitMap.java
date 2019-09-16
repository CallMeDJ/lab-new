package homework.dajiao.session5;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;


/**
 * 去重统计的 BitMap
 * @param <K>
 */
public class BananaBitMap<K> implements CountingInterface<K> {
	private int[] filter;
	private int size;
	private DigestInterface digestInterface;


	private static final Integer DEFAULT_CAPACITY = 1000000;

	public BananaBitMap(){
		init(DEFAULT_CAPACITY);
	}

	public BananaBitMap(int capacity){
		init(capacity);
	}


	private void init(int capacity){
		this.filter = new int[capacity];
		this.size = 0;
		this.digestInterface = new DigestMD5();
	}



	private static int getBit(int position){
		int result = 1 << (position);
		return result;

	}


	private boolean containsBit0(int position,int target){
		return (getBit(position) & target) == getBit(position);
	}


	private  void setBit(int position){
		int index = position / 32;
		int pos = position % 32;

		int target = filter[index];

		if(containsBit0(pos,target)){
			return;
		}
		target = target + getBit(position);
		filter[index] = target;
	}


	private  boolean containsBit(int position){
		int index = position / 32;
		int pos = position % 32;
		int target = filter[index];

		return containsBit0(pos,target);
	}


	@Override
	public void add(K key) {
		String keyString = key.toString();
		int digest = digestInterface.digest(keyString);
		int position = digest % (32 * filter.length);
		if (!containsBit(position)) {
			setBit(position);
			size++;
		}
	}



	@Override
	public int size() {
		return size;
	}



	private class DigestMD5 implements DigestInterface {


		@Override
		public int digest(String key) {
			return Math.abs(encryptMD5(key));
		}

		/**
		 * 对字节流进行MD5摘要。
		 */
		private int encryptMD5(String data) {
			byte[] bytes = null;
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				bytes = md.digest(data.getBytes());
				return new String(bytes).hashCode();
			} catch (GeneralSecurityException gse) {

			}

			return 0;
		}
	}



	private interface DigestInterface {
		int digest(String key);
	}
}
