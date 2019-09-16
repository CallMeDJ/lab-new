package homework.woods.session5;

/**
 * 这是一个去重计数器的接口，主要就是 add 添加一个值 和 size 当前的统计值，请不要用任何 JDK容器类
 *
 * @param <K>
 */
public interface CountingInterface<K> {

	boolean add(K key);

	int size();
}
