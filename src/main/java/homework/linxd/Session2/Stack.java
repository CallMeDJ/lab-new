package homework.linxd.Session2;

public interface Stack {

	/**
	 * 入栈
	 * @param i 入栈的数字
	 * @return 成功返回0，失败返回-1
	 */
	int push(Object i);

	/**
	 * 出栈
	 * @return 返回出栈的元素,,失败返回-1
	 */
	Object pop();

	/**
	 * 查看栈顶元素
	 * @return 返回栈顶元素,失败返回-1
	 */
	Object peak();

	/**
	 * 栈的大小
	 * @return 返回栈大小
	 */
	int size();

	/**
	 * 栈是否为空
	 * @return 空返回0，不空返回-1
	 */
	int isEmpty();

}
