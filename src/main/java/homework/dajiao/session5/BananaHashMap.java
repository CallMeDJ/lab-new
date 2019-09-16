package homework.dajiao.session5;

public class BananaHashMap<K,V> extends BananaMap<K,V> {

	private HashEntry<K,V>[] data;
	private Integer size = 0;


	private static final int DEFAULT_SIZE = 10;

	public BananaHashMap(){
		this.data = new HashEntry[DEFAULT_SIZE];
	}

	public BananaHashMap(int size){
		this.data = new HashEntry[size];
	}

	@Override
	public boolean put(K key,V value){
		int hash = hash(key);
		int bucket = hash % data.length;

		HashEntry<K,V> firstData = data[bucket];

		if(firstData == null){
			data[bucket] = new HashEntry(key,value);
			size++;
			return true;
		}


		while (firstData != null){
			if(firstData.key.equals(key)){
				firstData.setValue(value);
				break;
			}


			if(firstData.next == null) {
				firstData.next = new HashEntry(key,value);
				size++;
				break;
			}

			firstData = firstData.next;
		}

		return true;
	}

	@Override
	public boolean remove(K key){
		int hash = hash(key);
		int bucket = hash % data.length;

		HashEntry<K,V> firstData = data[bucket];

		HashEntry<K,V> previous = new HashEntry<>(null,null);
		previous.next = firstData;

		while (firstData != null){
			if(firstData.key.equals(key)){
				HashEntry<K,V> next = firstData.next;
				previous.next = next;
				firstData.next = null;
				size--;
				return  true;
			}
			previous = firstData;
			firstData = firstData.next;
		}
		return false;
	}
	@Override
	public V get(K key){
		int hash = hash(key);
		int bucket = hash % data.length;

		HashEntry<K,V> firstData = data[bucket];


		while (firstData != null){
			if(firstData.key.equals(key)){
				return firstData.value;
			}
			firstData = firstData.next;
		}
		return null;
	}


	@Override
	public int size() {
		return size;
	}
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	boolean containsKey(K key) {
		return false;
	}


	static final int hash(Object key) {
		int h;
		return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
	}



	public static class HashEntry<K,V>{
		K key;
		V value;
		HashEntry next;

		HashEntry( K key,
		           V value){
			this.key =key;
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}

		public HashEntry getNext() {
			return next;
		}

		public void setNext(HashEntry next) {
			this.next = next;
		}
	}


}
