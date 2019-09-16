package homework.woods.session5;

/**
 * @author: woods
 * @date: 2019/8/28
 * @description: v1 基本照抄学习文章内的实现； v2 基于需求简化了实现
 */
public class WoodsHashMapV2<K, V> extends WoodsMapV2<K,V>  {
    public static final int DEFAULT_CAPACITY = 16 ; // aka 1 << 4
    private int size;
    private Node<K, V>[] nodeArray;


    public WoodsHashMapV2(){
        nodeArray = new Node[DEFAULT_CAPACITY];
    }

    public WoodsHashMapV2(int size)
    {
        nodeArray = new Node[size];
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
        int h = hash(key);
        int index = h % nodeArray.length;
        Node node = nodeArray[index];
        while (node != null)
        {
            if (node.key.equals(key))
            {
                return true;
            }
            node = node.getNext();
        }
        return false;
    }

    @Override
    V get(K key) {
        int h = hash(key);
        int index = h % nodeArray.length;
        Node<K, V> node = nodeArray[index];
        while (node != null)
        {
            if (node.key.equals(key))
            {
                return node.value;
            }
            node = node.getNext();
        }
        return null;
    }

    @Override
    boolean put(K key, V value) {
        int h = hash(key);
        int index = h % nodeArray.length;
        Node<K, V> node = nodeArray[index];
        if (node == null)
        {
            nodeArray[index] = new Node<K, V>(key, value, h, null);
            size ++;
            return true;
        }

        while (node != null)
        {
            if (node.key.equals(key))
            {
                node.setValue(value);
                break;
            }
            node = node.getNext();
        }

        if (node == null)
        {
            // 不考虑resize
            nodeArray[index] = new Node<K, V>(key,value, h, nodeArray[index]);
            size ++;
        }

        return true;
    }



    @Override
    boolean remove(K key) {
        int h = hash(key);
        int index = h % nodeArray.length;
        Node<K, V> node = nodeArray[index];
        if (node.key.equals(key))
        {
            nodeArray[index] = node.getNext();
            size -- ;
            return true;
        }
        Node<K, V> pre = node;
        node = node.getNext();
        while (node != null)
        {
            if (node.key.equals(key))
            {
                pre.setNext(node.getNext());
                size -- ;
                return false;
            }
        }
        return false;
    }

    int hash(Object key){
        // 为了测试，制造hash碰撞
        if (key instanceof String && "woods2".equals((String)key))
        {
            return hash("woods1");
        }
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    class Node<K, V>{
        K key;
        V value;
        int hash;

        Node<K, V> next;

        public Node(K key, V value, int hash, Node<K, V> next)
        {
            this.key = key;
            this.value = value;
            this.next = next;
            this.hash = hash;
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

        public int getHash() {
            return hash;
        }

        public void setHash(int hash) {
            this.hash = hash;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }

        public Node<K, V> getNext() {
            return next;
        }

    }
}
