package homework.seven.session5;

/**
 * HashMap
 *
 * @author Seven-Steven
 * @date 2019/9/5 下午10:39
 */
public class BananaHashMap<K, V> extends BananaMap<K, V> {
  /**
   * Map 存储元素个数
   */
  private int size = 0;

  /**
   * 数组 table
   */
  private Entry<K, V>[] table;

  /**
   * 创建一个 BananaMap
   *
   * @param capacity 初始容量
   * @author Seven-Steven
   * @date 2019/9/5 下午10:57
   */
  public BananaHashMap(int capacity) {
    if (capacity < 0) {
      throw new IllegalArgumentException("capacity should not be less than 0!");
    }

    this.table = (Entry<K, V>[]) new Entry[capacity];
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
    int index = indexFor(key);
    Entry<K, V> entry = this.table[index];
    while (entry != null) {
      K entryKey = entry.getKey();
      if (entryKey == key || (entryKey != null && entry.equals(key))) {
        return true;
      }

      entry = entry.getNext();
    }

    return false;
  }

  @Override
  V get(K key) {
    int index = indexFor(key);
    Entry<K, V> entry = this.table[index];
    while (entry != null) {
      K entryKey = entry.getKey();
      if (entryKey == key || (entryKey != null && entry.equals(key))) {
        return entry.getValue();
      }

      entry = entry.getNext();
    }

    return null;
  }

  @Override
  void put(K key, V value) {
    if (this.size == this.table.length) {
      this.resize();
    }

    int index = this.indexFor(key);
    Entry<K, V> entry = this.table[index];
    if (null == entry) {
      this.table[index] = new Entry<>(key, value);
      this.size++;
      return;
    }

    Entry<K, V> last;
    do {
      last = entry;
      K entryKey = entry.getKey();
      if (entryKey == key || (entryKey != null && entry.equals(key))) {
        entry.setValue(value);
        return;
      }

      entry = entry.getNext();
    } while (null != entry);

    last.setNext(new Entry<>(key, value));
    this.size++;
  }

  @Override
  V remove(K key) {
    int index = this.indexFor(key);
    Entry<K, V> entry = this.table[index];

    Entry<K, V> last = entry;
    while (entry != null) {
      K entryKey = entry.getKey();
      if (entryKey == key || (entryKey != null && entry.equals(key))) {
        if (last == entry) {
          this.table[index] = null;
          this.size--;
          return entry.getValue();
        }

        last.setNext(entry.getNext());
        this.size--;
        return entry.getValue();
      }

      last = entry;
      entry = entry.getNext();
    }

    return null;
  }

  /**
   * 自动扩容为原来的两倍
   */
  private void resize() {
    Entry<K, V>[] oldTable = this.table;
    this.table = new Entry[this.size() * 2];
    this.size = 0;

    for (Entry<K, V> entry : oldTable) {
      while (entry != null) {
        this.put(entry.getKey(), entry.getValue());
        entry = entry.getNext();
      }
    }
  }

  /**
   * 计算指定 hash 在 table 中的索引位置
   *
   * @param hash hash 值
   * @return int 索引位置
   * @author Seven-Steven
   * @date 2019-09-05 23:05:57
   */
  private int indexFor(int hash) {
    return hash & (this.table.length - 1);
  }

  /**
   * 计算指定 key 在 table 中的索引位置
   *
   * @param key key
   * @return int key 在 table 中的索引位置
   * @author Seven-Steven
   * @date 2019-09-05 23:07:11
   */
  private int indexFor(K key) {
    return this.indexFor(hash(key));
  }

  /**
   * 计算 key 的 hash 值
   *
   * @param key key
   * @return int 对应 key 的 hash 值
   * @author Seven-Steven
   * @date 2019-09-05 23:07:48
   */
  static final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
  }
}

class Entry<K, V> {
  private K key;
  private V value;
  private Entry<K, V> next;

  public Entry(K key, V value) {
    this.key = key;
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

  public Entry<K, V> getNext() {
    return next;
  }

  public void setNext(Entry<K, V> next) {
    this.next = next;
  }
}
