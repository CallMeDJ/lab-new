package homework.dajiao.session5;

import com.google.common.collect.Lists;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.List;

/**
 * 去重统计的布隆过滤器
 *
 * @param <K>
 */
public class BananaBloomFilter<K> implements CountingInterface<K> {

	private int[] filter;

	private int size;

	private List<DigestInterface> digestInterfaceList;

	private static final Integer DEFAULT_CAPACITY = 1000000;

	public BananaBloomFilter() {
		init(DEFAULT_CAPACITY);
	}
	public BananaBloomFilter(int capacity) {
		init(capacity);
	}


	private void init(int capacity) {
		this.filter = new int[capacity];
		this.size = 0;

		digestInterfaceList = Lists.newArrayList();
		digestInterfaceList.add(new DigestMD5());
		digestInterfaceList.add(new HMAC("KEY1"));
		digestInterfaceList.add(new HMAC("KEY2"));
		digestInterfaceList.add(new HMAC("KEY3"));

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
		boolean isFound = false;

		for (DigestInterface digestInterface : digestInterfaceList) {
			int digest = digestInterface.digest(keyString);
			int position = digest % (32 * filter.length);
			if (!containsBit(position)) {
				setBit(position);
				isFound = true;
			}
		}


		if (isFound) {
			size++;
		}

	}




	private static final String CHARSET_UTF8 = "utf-8";

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


	private class HMAC implements DigestInterface {

		private String secret;

		public HMAC(String secret) {
			this.secret = secret;
		}

		@Override
		public int digest(String key) {
			return Math.abs(encryptHMAC(key));
		}

		/**
		 * 对字节流进行HMAC_MD5摘要。
		 */
		private int encryptHMAC(String data) {
			byte[] bytes = null;
			try {
				SecretKey secretKey = new SecretKeySpec(secret.getBytes(CHARSET_UTF8), "HmacMD5");
				Mac mac = Mac.getInstance(secretKey.getAlgorithm());
				mac.init(secretKey);
				bytes = mac.doFinal(data.getBytes(CHARSET_UTF8));
				return new String(bytes).hashCode();
			} catch (GeneralSecurityException gse) {

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			return 0;
		}

	}


	private interface DigestInterface {
		int digest(String key);
	}


}

