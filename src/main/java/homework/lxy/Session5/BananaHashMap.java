package homework.lxy.Session5;

import java.util.Set;

public class BananaHashMap<K,V> extends BananaMap<K,V> {
	private Node<K, V>[] table;

	private final int MAX_CAPACITY = Integer.MAX_VALUE;

	private static final int DEFAULT_CAPACITY = 16;

	private static final float DEFAULT_FACTOR = 0.75f;


	private float loadFactor = 0.75f;

	private int threadhold;

	private int size;

	private int capacity;

	BananaHashMap(){
		this(DEFAULT_CAPACITY,DEFAULT_FACTOR);
	}

	BananaHashMap(int capacity, float loadFactor){
		if(capacity < 0 || loadFactor <= 0 || Float.isNaN(loadFactor)){
			throw new IllegalArgumentException("param is illegal");
		}
		if(capacity >= MAX_CAPACITY){
			capacity = MAX_CAPACITY;
		}
		this.loadFactor = loadFactor;
		this.threadhold = (int)(capacity * loadFactor);
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
		return this.getNode(hash(key), key) != null;
	}

	@Override
	V get(K key) {
		return this.getNode(hash(key), key).value;
	}

	@Override
	V put(K key, V value) {
		Node[] tab;Node e;int hash = hash(key),n = 0,i;
		if(((tab = table) == null) || ((n = tab.length) == 0)){
			n = (tab = resize()).length;
		}
		if((e = tab[i= ((n - 1) & hash)]) == null){
			table[i] = new Node(hash, key, value);
			return value;
		}else {
			do{
				if(hash == e.hash && (key != null && key.equals(e.key))){
					e.value = value;
					return value;
				}
			}while ((e = e.next) != null);
			Node newNode = new Node(hash, key, value);
			e.next = newNode;
		}
		if(++size > threadhold){
			resize();
		}
		return value;
	}

	@Override
	V remove(K key) {
		Node[] tab;Node target = null,firstNode = null,preNode = null,m;int hash = hash(key),n,index;
		if((tab = table) != null && (n = tab.length) > 0
				&& (firstNode = tab[index = hash & (n-1)]) != null){
			if(hash == firstNode.hash &&
					(key == firstNode.key) || (key != null && key.equals(firstNode.key))){
				 target = firstNode;
			}else if((m = firstNode.next) != null){
				do {
					if(hash == m.hash &&
							(key == m.key) || (key != null && key.equals(m.key))){
						target = m;
						break;
					}
					preNode = m;
				}while ((m = m.next) != null);
			}
			if(firstNode == target){
				tab[index] = target.next;
			}else {
				preNode.next = target.next;
			}
			size--;
		}
		return null;
	}

	private Node<K, V> getNode(int hash, K key){
		Node<K, V>[] tab = table;
		if(tab != null && tab.length > 0){
			int length = tab.length;
			Node<K, V> firstNode = tab[(length-1)&hash];
			if(firstNode != null){
				K firstKey = firstNode.key;
				if(hash == hash(firstKey) && key.equals(firstKey)){
					return firstNode;
				}
				Node<K,V> n = firstNode.next;
				if(n != null){
					do{
						if(hash == hash(n.key) && key.equals(n.key)){
							return n;
						}
					}while ((n = n.next) != null);
				}
				return firstNode;
			}else {
				return null;
			}
		}
		return null;
	}

	/**
	 * 取最近一个大于等于size的2的指数幂，如2 4 8 16
	 * @param size
	 * @return
	 */
	private int tableSizeFor(int size){
		int n = size - 1;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		n = (n > MAX_CAPACITY) ? MAX_CAPACITY : n+1;
		return n;
	}

	private int hash(K key){
		int hash;
		return key == null ? 0 : (hash = key.hashCode()) ^ (hash >>> 16);
	}

	private final Node[] resize(){
        Node[] oldTab = table;
        int oldCap = oldTab == null ? 0 : oldTab.length;
        int oldThr = threadhold;
		int newCap,newThr = 0;
		if(oldCap > 0){
			if(oldCap >= MAX_CAPACITY){
				threadhold = Integer.MAX_VALUE;
				return oldTab;
			}else if((newCap = oldCap << 1) <= MAX_CAPACITY
					&& oldCap > DEFAULT_CAPACITY){
				newThr = oldThr << 1;
			}
		}else if(oldThr > 0){
			newCap = oldCap;
		}else {
			newCap = DEFAULT_CAPACITY;
			newThr = (int)(loadFactor * DEFAULT_CAPACITY);
		}

		if(newThr == 0){
			int tempThr = (int)(newCap*loadFactor);
			newThr = tempThr >= MAX_CAPACITY ? MAX_CAPACITY : tempThr;
		}
		threadhold = newThr;
		Node[] newTab = new Node[newCap];
		table = newTab;
		if(oldTab != null){
			for(int i = 0; i < oldCap; i++){
				Node n;
				if((n = oldTab[i]) != null){
					oldTab[i] = null;
					if(n.next == null){
						newTab[n.hash & (newCap -1)] = n;
					}else {
						Node loHead = null; Node loTail = null;
						Node hiHead = null; Node hiTail = null;
						Node next;
						do {
							n = n.next;
							if((n.hash & oldCap) == 0){
								if(loTail == null){
									loHead = n;
								}else {
									loTail.next = n;
								}
								loTail = n;
							}else {
								if(hiTail == null){
									hiHead = n;
								}else {
									hiTail.next = n;
								}
								hiTail = n;
							}
						}while (n.next != null);
						if(loTail != null){
							loTail.next = null;
							newTab[i] = loHead;
						}
						if(hiHead != null){
							hiTail.next = null;
							newTab[oldCap+i] = hiHead;
						}
					}
				}
			}
		}
		return newTab;
	}

	static class Node<K,V>{
		public final int hash;
		public K key;
		public V value;
		public Node<K, V> next;
		Node(int hash, K key, V value){
			this.hash = hash;
			this.key = key;
			this.value = value;
		}
	}
}
