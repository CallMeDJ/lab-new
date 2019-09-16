package homework.mrglint.Session5;

/**
 * @author luhuancheng
 * @param <K>
 * @param <V>
 */
public class BananaHashMap<K,V> extends BananaMap<K,V> {

	/**
	 * hash桶中存放数据的类
	 * @param <K>
	 * @param <V>
	 */
	private static class Node<K, V> {
		private K key;
		private V value;
		// 维护双向链表，方便删除节点
		private Node next;
		private Node prev;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	/**
	 * 计算key的hash值
	 * 对于hash表的hash函数，只要hash值分布均匀、计算过程高效就符合要求
	 * @param key
	 * @return
	 */
	static final int hash(Object key) {
		int h;
		return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
	}

	private static final float DEFAULT_LOAD_FACTOR= 0.75f;

	private Node<K,V>[] table;
	private int size;
	private float loadFactor;
	private int threshold;
	private int capacity;

	public BananaHashMap() {
		this(16);
	}

	public BananaHashMap(int capacity) {
		this(capacity, DEFAULT_LOAD_FACTOR);
	}

	public BananaHashMap(int capacity, float loadFactor) {
		table = (Node<K,V>[]) new Node[capacity];
		this.loadFactor = loadFactor;
		threshold = (int) (capacity * loadFactor);
		size = 0;
		this.capacity = capacity;
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
		Node<K, V> node = findNode(key);
		return node != null;
	}

	/**
	 * 直接使用取模的方式.JDK中为了提高性能，使用了位运算取模的方式
	 * @param key
	 * @return
	 */
	private int calcIndex(K key, int capacity) {
		int hash = hash(key);
		return hash % capacity;
	}

	private Node<K, V> findNode(K key) {
		int index = calcIndex(key, capacity);
		Node<K, V> p;
		if ((p = table[index]) == null) {
			return null;
		}
		while (p != null) {
			if (p.key == key) {
				return p;
			}
			p = p.next;
		}
		return null;
	}

	@Override
	V get(K key) {
		Node<K, V> node = findNode(key);
		if (node == null) {
			return null;
		}
		return node.value;
	}

	@Override
	V put(K key, V value) {
		// 达到负载因子，需要扩容，在扩容过程中对已有数据进行重新hash后放入新的hash表中
		resize();
		int index = calcIndex(key, capacity);
		Node p;
		if ((p = table[index]) == null) {
			table[index] = newNode(key, value, null);
			size++;
		} else {
			Node prev = p;
			while (p.next != null) {
				if (p.key == key) {
					break;
				}
				prev = p;
				p = p.next;
			}
			if (p.key == key) {
				// key相等说明已经存在，重新赋值即可
				p.value = value;
			} else {
				// key不相等，新增键值对
				p.next = newNode(key, value, prev);
				size++;
			}
		}
		return value;
	}

	private void resize() {
		if (size + 1 > threshold) {
			// 扩容
			Node<K,V>[] newTable = new Node[capacity * 2];
			// 执行重新hash.同样，JDK中为了性能的极致，使用了位运算的方式和hash表大小为2的倍数这个技巧。可以避免重新hash
			for (Node<K, V> node : table) {
				while (node != null) {
					int index = calcIndex(node.key, newTable.length);
					Node p;
					if ((p = newTable[index]) == null) {
						newTable[index] = newNode(node.key, node.value, null);
					} else {
						Node prev = null;
						while (p.next != null) {
							prev = p;
							p = p.next;
						}
						p.next = newNode(node.key, node.value, prev);
					}
					node = node.next;
				}
			}
			threshold = (int) (newTable.length * loadFactor);
			table = newTable;
			capacity = newTable.length;
		}
	}

	@Override
	V remove(K key) {
		if (!containsKey(key)) {
			throw new IllegalArgumentException("key is not exists");
		}
		int index = calcIndex(key, capacity);
		Node<K, V> node = table[index];
		if (node.next == null) {
			table[index] = null;
			size--;
			return node.value;
		} else {
			V value = null;
			while (node.next != null) {
				// 找到了key所在的位置
				if (node.key == key) {
					// 维护链表
					value = node.value;
					Node prev = node.prev;
					Node next = node.next;
					if (prev == null) {
						// 第一个节点
						next.prev = null;
						table[index] = next;
					} else {
						next.prev = prev;
						node.prev.next = next;
					}
				}
				node = node.next;
			}
			size--;
			return value;
		}
	}

	private Node<K, V> newNode(K key, V value, Node prev) {
		Node<K, V> node = new Node<>(key, value);
		node.prev = prev;
		return node;
	}

	public static void main(String[] args) {
		BananaHashMap<String, String> map = new BananaHashMap<>(2, 1);
		System.out.println(map.size());
		map.put("A", "A");
		System.out.println(map.size());
		System.out.println(map.get("A"));
		map.put("A", "AA");
		map.put("B", "B");
		map.put("C", "C");
		map.put("D", "D");
		System.out.println(map.size());
		System.out.println(map.get("A"));
		map.remove("A");
		map.remove("B");
		map.remove("C");
		map.remove("D");
		System.out.println(map.get("A"));
		System.out.println(map.size());
	}
}
