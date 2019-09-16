package homework.zhou.session4;





import java.util.Arrays;
import java.util.Objects;

/**
 * @Author 大蕉
 * 这里你自己实现的时候，要去掉 abstract
 */
public  class LinedString implements StringInterface {

	private LinkedNode head = new LinkedNode();

	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args){
		char[] chars = {'a','b','c','d','e','f'};
		char[] subChar = {'c','d','e','f'};
 		ArrayString arrayString = new ArrayString(chars);
		LinedString linedString = new LinedString(chars);
		System.out.println(arrayString.charAt(3));
		System.out.println(arrayString.subString(0,2).toString());
		System.out.println(arrayString.indexOf(subChar));
		System.out.println(arrayString.length());
		System.out.println(arrayString.reverse().toString());
		System.out.println(linedString.charAt(3));
		System.out.println(linedString.subString(0,2).toString());
		System.out.println(linedString.indexOf(subChar));
		System.out.println(linedString.length());
		System.out.println(linedString.reverse().toString());

	}

	public LinedString(LinkedNode head) {
		this.head = head;
	}

	public LinedString(char[] chars){
		for (int i = chars.length - 1; i >= 0; i --) {
			LinkedNode node = new LinkedNode();
			node.setValue(chars[i]);
			node.setPrevious(head);
			node.setNext(head.getNext());
			head.setNext(node);
		}
	}

	/**
	 * 根据数字返回对应的字符串
	 * @param number
	 * @return
	 */
	public static StringInterface valueOf(Integer number) throws NumberFormatException{
		char[] numberArray;
		// 取绝对值
		int positiveNumber = Math.abs(number);
		// 是否正整数
		boolean positive = false;
		if (positiveNumber == number)
		{
			positive = true;
			numberArray = new char[16];
		}else {
			numberArray = new char[17];
		}
		int index = numberArray.length - 1;

		while (positiveNumber >= 10)
		{
			numberArray[index--] = (char)((positiveNumber%10) + '0');
			positiveNumber = positiveNumber/10;
		}
		numberArray[index] = (char)(positiveNumber + '0');

		if (!positive)
		{
			numberArray[--index] = '-';
		}

		char[] result = Arrays.copyOfRange(numberArray, index, numberArray.length);

		return new LinedString(result);

	}

	/**
	 * 返回字符串的长度
	 * @return
	 */
	@Override
	public int length() {
		int len = 0;
		LinkedNode node = head.getNext();
		while (node != null) {
			len ++;
			node = node.getNext();

		}
		return len ;

	}

	/**
	 * 返回指定位置的字符
	 * @param position
	 * @return
	 */
	@Override
	public char charAt(int position) {
		if (position<0||position>length()-1){
			throw new StringIndexOutOfBoundsException(position);
		}

		LinkedNode node = head;
		int index = 0;
		while (index < position )
		{
			node = node.getNext();
			index ++;
		}
		return node.getValue();

	}

	/**
	 * 字符串匹配
	 * @param target
	 * @return
	 */
	@Override
	public int indexOf(char[] target) {
		Objects.requireNonNull(target);
		if (target.length == 0 || target.length > this.length()) {
			return -1;
		}

		int max = this.length() - target.length;
		int index = 0;
		for (LinkedNode node = head; index <= max && node != null; node = node.getNext()) {
			if (target[0] == node.getValue()) {
				LinkedNode tmp = node.getNext();
				for (int j = 1; j < target.length; j++) {
					if (target[j] == tmp.getValue()) {
						tmp = tmp.getNext();
					} else {
						return -1;
					}
				}
				return index;
			}
			index++;
		}

		return -1;

	}

	/**
	 * 获取子串
	 * @param start
	 * @param end
	 * @return
	 */
	@Override
	public StringInterface subString(int start, int end) {
		if (start < 0) {
			throw new StringIndexOutOfBoundsException(start);
		}
		if (end > this.length()) {
			throw new StringIndexOutOfBoundsException(end);
		}
		int subLen = end - start;
		if (subLen < 0) {
			throw new StringIndexOutOfBoundsException(subLen);
		}
		int pos = 0 ;
		char[] chars = new char[subLen];
		LinkedNode node = head.getNext();
		while (node != null) {
			if(pos >= start && pos < end) {
				chars[pos] = node.getValue();
			}
			node = node.getNext();
			pos ++;
		}
		return new LinedString(chars);
	}

	/**
	 * 链表的翻转
	 * @return
	 */
	@Override
	public StringInterface reverse() {
		if(this.length() <= 1){
			return this;
		}
		LinkedNode pre = head;
		LinkedNode cur = head.getNext();
		LinkedNode tmp;
		while (cur != null) {
			tmp = cur.getNext();
			cur.setNext(pre);
			cur.setPrevious(tmp);
			pre.setPrevious(cur);
			pre = cur;
			cur = tmp;
		}
		//构造新head
		LinkedNode newHead = new LinkedNode();
		newHead.setNext(pre);
		pre.setPrevious(newHead);
		//删除原有head
		LinkedNode lastNode = head.getPrevious();
		lastNode.setNext(null);
		return new LinedString(newHead);
	}

	/**
	 * 方便测试打印输出
	 * @return
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.length());
		for (LinkedNode node = head; node != null; node = node.getNext()) {
			sb.append(node.getValue());
		}
		return sb.toString();
	}
}
