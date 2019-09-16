package homework.woods.session5;

import homework.woods.utils.WoodsPrinter;

import java.util.Map;
import java.util.Objects;

public class WoodsHashMap<K,V> extends WoodsMap<K,V> {

	/** 初始容量，默认16 */
	static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

	/** 最大初始容量，2^30 */
	static final int MAXIMUM_CAPACITY = 1 << 30;

	/** 负载因子，默认0.75，负载因子越小，hash冲突机率越低 */
	static final float DEFAULT_LOAD_FACTOR = 0.75f;

	/** 初始化一个Entry的空数组 */
	static final Entry<?,?>[] EMPTY_TABLE = {};

	/** 将初始化好的空数组赋值给table，table数组是HashMap实际存储数据的地方，并不在EMPTY_TABLE数组中 */
	transient Entry<K,V>[] table = (Entry<K,V>[]) EMPTY_TABLE;

	/** HashMap实际存储的元素个数 */
	transient int size;

	/** 临界值（HashMap 实际能存储的大小）,公式为(threshold = capacity * loadFactor) */
	int threshold;

	/** 负载因子 */
	final float loadFactor;

	/** HashMap的结构被修改的次数，用于迭代器 */
	transient int modCount;


	static class Entry<K,V> implements Map.Entry<K,V> {
		final K key;
		V value;
		/** 指向下一个元素的引用 */
		Entry<K,V> next;
		int hash;

		/**
		 * 构造方法为Entry赋值
		 */
		Entry(int h, K k, V v, Entry<K,V> n) {
			value = v;
			next = n;
			key = k;
			hash = h;
		}


		@Override
		public final K getKey()        { return key; }
		@Override
		public final V getValue()      { return value; }
		@Override
		public final String toString() { return key + "=" + value; }

		@Override
		public final int hashCode() {
			return Objects.hashCode(key) ^ Objects.hashCode(value);
		}

		@Override
		public final V setValue(V newValue) {
			V oldValue = value;
			value = newValue;
			return oldValue;
		}

		@Override
		public final boolean equals(Object o) {
			if (o == this) {
				return true;
			}
			if (o instanceof Map.Entry) {
				Map.Entry<?,?> e = (Map.Entry<?,?>)o;
				if (Objects.equals(key, e.getKey()) &&
						Objects.equals(value, e.getValue()))
					return true;
			}
			return false;
		}
	}


	public WoodsHashMap(int initialCapacity, float loadFactor) {
		// 判断设置的容量和负载因子合不合理
		if (initialCapacity < 0) {
			throw new IllegalArgumentException("Illegal initial capacity: " +
					initialCapacity);
		}
		if (initialCapacity > MAXIMUM_CAPACITY) {
			initialCapacity = MAXIMUM_CAPACITY;
		}
		if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
			throw new IllegalArgumentException("Illegal load factor: " +
					loadFactor);
		}
		// 设置负载因子，临界值此时为容量大小，后面第一次put时由inflateTable(int toSize)方法计算设置
		this.loadFactor = loadFactor;
		threshold = initialCapacity;
		// 长度为2的次幂
		int capacity = roundUpToPowerOf2((int)(initialCapacity/loadFactor));
		table = new Entry[capacity];
	}

	public WoodsHashMap(int initialCapacity) {
		this(initialCapacity, DEFAULT_LOAD_FACTOR);
	}

	public WoodsHashMap() {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
	}

	@Override
	int size() {
		return size;
	}

	@Override
	boolean isEmpty() {
		return size == 0;
	}

	@Override
	boolean containsKey(K key) {
		return getEntry(key)!=null;
	}

	@Override
	public V get(Object key) {
		// 若key为null，遍历table[0]处的链表（实际上要么没有元素，要么只有一个Entry对象），取出key为null的value
		if (key == null) {
			if (table[0] == null)
			{
				return null;
			}
			return  table[0].value;
		}
		// 若key不为null，用key获取Entry对象
		Entry<K,V> entry = getEntry(key);
		// 若链表中找到的Entry不为null，返回该Entry中的value
		return null == entry ? null : entry.getValue();
	}

	final Entry<K,V> getEntry(Object key) {
		if (size == 0) {
			return null;
		}
		// 计算key的hash值
		int hash = (key == null) ? 0 : hash(key);
		// 计算key在数组中对应位置，遍历该位置的链表
		for (Entry<K,V> e = table[indexFor(hash, table.length)];
			 e != null;
			 e = e.next) {
			Object k;
			// 若key完全相同，返回链表中对应的Entry对象
			if (e.hash == hash &&
					((k = e.key) == key || (key != null && key.equals(k)))) {
				return e;
			}
		}
		// 链表中没找到对应的key，返回null
		return null;
	}

	@Override
	public V put(K key, V value) {
		// 如果table引用指向成员变量EMPTY_TABLE，那么初始化HashMap（设置容量、临界值，新的Entry数组引用）
		if (table == EMPTY_TABLE) {
			inflateTable(threshold);
		}
		// 若“key为null”，则将该键值对添加到table[0]处，遍历该链表，如果有key为null，则将value替换。没有就创建新Entry对象放在链表表头
		// 所以table[0]的位置上，永远最多存储1个Entry对象，形成不了链表。key为null的Entry存在这里
		if (key == null) {
			return putForNullKey(value);
		}
		// 若“key不为null”，则计算该key的哈希值
		int hash = hash(key);
		// 搜索指定hash值在对应table中的索引
		int i = indexFor(hash, table.length);
		// 循环遍历table数组上的Entry对象，判断该位置上key是否已存在
		for (Entry<K,V> e = table[i]; e != null; e = e.next) {
			Object k;
			// 哈希值相同并且对象相同
			if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
				// 如果这个key对应的键值对已经存在，就用新的value代替老的value，然后退出！
				V oldValue = e.value;
				e.value = value;
//				e.recordAccess(this);
				return oldValue;
			}
		}
		// 修改次数+1
		modCount++;
		// table数组中没有key对应的键值对，就将key-value添加到table[i]处
		addEntry(hash, key, value, i);
		return value;
	}

	V putForNullKey(V value)
	{
		if (table[0] == null)
		{
			Entry<K, V> nullEntry = new Entry<>(hash(null), null, value, null);
			table[0] = nullEntry;
			return value;
		}

		Entry<K, V> nullEntry = table[0];
		nullEntry.setValue(value);
		return value;
	}

	@Override
	V remove(K key) {
		return null;
	}


	/**
	 * Returns index for hash code h.
	 */
	static int indexFor(int h, int length) {
		// assert Integer.bitCount(length) == 1 : "length must be a non-zero power of 2";
		return h & (length-1);
	}

	/**
	 * 将Entry添加到数组bucketIndex位置对应的哈希桶中，并判断数组是否需要扩容
	 */
	void addEntry(int hash, K key, V value, int bucketIndex) {
		// 如果数组长度大于等于容量×负载因子，并且要添加的位置为null
		if ((size >= threshold) && (null != table[bucketIndex])) {
			// 长度扩大为原数组的两倍，代码分析见下面扩容机制
			resize(2 * table.length);
			hash = (null != key) ? hash(key) : 0;
			bucketIndex = indexFor(hash, table.length);
		}
		createEntry(hash, key, value, bucketIndex);
	}

	/**
	 * 在链表中添加一个新的Entry对象在链表的表头
	 */
	void createEntry(int hash, K key, V value, int bucketIndex) {
		Entry<K,V> e = table[bucketIndex];
		table[bucketIndex] = new Entry<>(hash, key, value, e);
		size++;
	}

	static final int hash(Object key) {
		// 为了测试，制造hash碰撞
		if (key instanceof String && "woods2".equals((String)key))
		{
			return hash("woods1");
		}
		int h;
		return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
	}

	void resize(int newCapacity) {
		Entry[] oldTable = table;
		int oldCapacity = oldTable.length;
		// 如果之前的HashMap已经扩充打最大了，那么就将临界值threshold设置为最大的int值
		if (oldCapacity == MAXIMUM_CAPACITY) {
			threshold = Integer.MAX_VALUE;
			return;
		}

		// 根据新传入的newCapacity创建新Entry数组
		Entry[] newTable = new Entry[newCapacity];
		// 用来将原先table的元素全部移到newTable里面，重新计算hash，然后再重新根据hash分配位置
		transfer(newTable, false);
		// 再将newTable赋值给table
		table = newTable;
		// 重新计算临界值，扩容公式在这儿（newCapacity * loadFactor）
		threshold = (int)Math.min(newCapacity * loadFactor, MAXIMUM_CAPACITY + 1);
	}

	void transfer(Entry[] newTable, boolean rehash) {
		int newCapacity = newTable.length;
		for (Entry<K,V> e : table) {
			while(null != e) {
				Entry<K,V> next = e.next;
				if (rehash) {
					e.hash = null == e.key ? 0 : hash(e.key);
				}
				int i = indexFor(e.hash, newCapacity);
				e.next = newTable[i];
				newTable[i] = e;
				e = next;
			}
		}
	}

	private void inflateTable(int toSize) {
		int capacity = roundUpToPowerOf2(toSize);//capacity一定是2的次幂
		threshold = (int) Math.min(capacity * loadFactor, MAXIMUM_CAPACITY + 1);//此处为threshold赋值，取capacity*loadFactor和MAXIMUM_CAPACITY+1的最小值，capaticy一定不会超过MAXIMUM_CAPACITY，除非loadFactor大于1
		table = new Entry[capacity];
	}

	private static int roundUpToPowerOf2(int number) {
		// assert number >= 0 : "number must be non-negative";
		return number >= MAXIMUM_CAPACITY
				? MAXIMUM_CAPACITY
				: (number > 1) ? Integer.highestOneBit((number - 1) << 1) : 1;
	}


}
