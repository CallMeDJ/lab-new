package homework.tonemy.session5;

public class HashMap<K,V> extends Map<K,V> {
	//默认长度
	private static int default_length = 16;
	//默认负载因子
	private static double default_load_factor = 0.75;
	//定义的数组
	private Entry<K, V>[] arr ;
	//数组中元素的最大个数
	private int threshold;
	//数组中元素的个数
	private int size ;
	public HashMap() {
		//泛型数组的初始化
		arr = (Entry<K, V>[]) new Entry[default_length] ;
		threshold = (int) (default_length*default_load_factor);
		size = 0 ;
	}
	@Override
	int size() {
		return this.size;
	}

	@Override
	boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	boolean containsKey(K key) {
		if (key == null) {
			return false;
		}
		return getEntry(key) != null;
	}

	@Override
	V get(K key) {
		if (key == null) {
			return null;
		}
		Entry<K, V> entry = getEntry(key);
		if(entry != null) {
			return entry.value;
		}
		return null;
	}

	@Override
	V put(K key, V value){
		if (key == null) {
			return null;
		}

		int index = getIndex(key);
		//先判断已有的数据中是否存在相同的key,如果有直接改变其值
		Entry<K, V> entry = arr[index];
		while (entry != null) {
			if (entry.getKey().hashCode() == key.hashCode() &&(entry.getKey() == key || entry.getKey().equals(key))){
				entry.setValue(value);
				return entry.value;
			}
			entry = entry.getNext();
		}
		//如果没有,就添加
		Entry<K, V> newEntry = new Entry<K, V> (key, value, null);
		//暂不考虑扩容
		arr[index] = newEntry;
		return newEntry.value;

	}

	@Override
	V remove(K key) {
		if (key == null){
			return null;
		}
		int index = getIndex(key);
		Entry<K, V> entry = arr[index];
		Entry<K, V> preEntry = null; //便于删除当前的Entry
		while (entry != null) {
			if (entry.getKey().hashCode() == key.hashCode() &&(entry.getKey() == key || entry.getKey().equals(key))){
				//判断是否是桶里面的第一个元素
				if (preEntry == null) {
					arr[index] = entry.getNext();
				}else {
					preEntry.setNext(entry.getNext());
				}
				//元素的个数减少
				this.size --;
				return entry.getValue();
			}
			preEntry = entry;
			entry = entry.getNext();
		}
		return null;
	}
	/**
	 * 根据key的hashCode得到在arr中的位置
	 * @param key
	 * @return
	 */
	int getIndex(K key) {
		return key.hashCode() % arr.length;
	}
	/**
	 * 根据key 在当前的桶中entry进行寻找数组中位置相同并且在链表中的值相同的
	 * @param key
	 * @return
	 */
	Entry<K, V> getEntry(K key) {
		Entry<K, V> entry = arr[getIndex(key)];
		while (entry != null) {
			if (entry.getKey().hashCode() == key.hashCode() &&(entry.getKey() == key || entry.getKey().equals(key))){
				return entry;
			}
			entry = entry.getNext();
		}
		return null;
	}
	class Entry<K, V> {
		K key;
		V value;

		Entry<K, V> next;
		public Entry(K key, V value,  Entry<K, V> next) {
			this.key = key;
			this.value = value;

			this.next = next;
		}

		public void setValue(V value) {
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public Entry<K, V> getNext() {
			return next;
		}

		public void setNext(Entry<K, V> next) {
			this.next = next;
		}
	}
}
