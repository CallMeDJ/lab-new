package homework.afei.session2;

public interface Stack {
	/**
	 * ��ջ
	 * @return boolean
	 * @throws Exception 
	 */
	boolean push(int value) throws Exception;

	/**
	 * ��ջ
	 * @return int
	 * @throws Exception 
	 */
	int pop() throws Exception;

	/**
	 * �鿴ջ��Ԫ��
	 * @throws Exception 
	 */

	int peak() throws Exception;

	/**
	 * ջ�Ĵ�С
	 */

	int size();

	/**
	 * ջ�Ƿ�Ϊ��
	 * @return
	 */
	boolean isEmpty();
}
