package homework.makersy.session2;

public interface Stack {

	/**
	 * 入栈
	 * @return
	 */
	int push(int v);

	/**
	 * 出栈
	 * @return
	 */
	int pop();

	/**
	 * 查看栈顶元素
	 */

	int peek();

	/**
	 * 栈的大小
	 */

	int size();

	/**
	 * 栈是否为空
	 * @return
	 */
	boolean isEmpty();

}
