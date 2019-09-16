package homework.tonemy.session4;

public interface StringInterface {
	/**
	 * 字符串的长度
	 * @return
	 */
	int length();


	/**
	 * 获取第 position 个字符
	 * @param position
	 * @return
	 */
	char charAt(int position);


	/**
	 * 根据target 获取第一个匹配的字符串的位置，如果没找到，返回 -1
	 * @param target
	 * @return
	 */
	int indexOf(char[] target);


	/**
	 * 根据开始节点和结束节点，返回新的字符串。
	 * @param start
	 * @param end
	 * @return
	 */
	StringInterface subString(int start, int end);




	/**
	 * 首尾翻转字符串，要求只能占用 O(1) 的额外空间
	 * @return
	 */
	StringInterface  reverse();




}
