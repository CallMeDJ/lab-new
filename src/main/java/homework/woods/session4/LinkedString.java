package homework.woods.session4;


import homework.woods.utils.KMPMatcher;
import homework.woods.utils.WoodsPrinter;

import java.util.Arrays;

/**
 * @Author woods
 */
public class LinkedString implements StringInterface{

	private LinkedNode head;

	public LinkedString(){

	}

	public LinkedString(char[] chars)
	{
		if (chars == null || chars.length == 0)
		{
			return;
		}
		head = new LinkedNode();
		head.setValue(chars[0]);

		LinkedNode pre = head;

		for (int i = 1; i < chars.length; i ++)
		{
			LinkedNode next = new LinkedNode();
			next.setValue(chars[i]);
			next.setPrevious(pre);
			pre.setNext(next);
			pre = next;
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

		return new LinkedString(result);
	}

	@Override
	public int length() {
		if (head == null)
		{
			return 0;
		}
		int len = 1;
		LinkedNode node = head;
		while (node.getNext() != null){
			len ++;
			node = node.getNext();
		}
		return len;
	}

	@Override
	public char charAt(int position) {
		LinkedNode node = this.nodeAt(position);
		return node.getValue();
	}

	public LinkedNode nodeAt(int position)
	{
		if (position < 0 || position > this.length() - 1)
		{
			throw new StringIndexOutOfBoundsException(position);
		}
		LinkedNode node = head;
		int index = 0;
		while (index < position )
		{
			node = node.getNext();
			index ++;
		}
		return node;
	}

	@Override
	public int indexOf(char[] target) {
		return KMPMatcher.match(this, target);
	}

	@Override
	public StringInterface subString(int start, int end) {
		if (start > this.length() || end > this.length() || start > end)
		{
			throw new StringIndexOutOfBoundsException(start);
		}

		char[] chars = new char[end - start + 1];
		for (int i = start, j = 0; i <= end ; i ++, j ++)
		{
			chars[j] = this.charAt(i);
		}

		return new LinkedString(chars);
	}

	@Override
	public StringInterface reverse() {
		int len = this.length();
		LinkedNode newHead = this.nodeAt(len -1);
		LinkedNode node = newHead;
		LinkedNode pre ;
		do {
			pre = node.getPrevious();
			node.setPrevious(node.getNext());
			node.setNext(pre);
			node = pre;
		}while (pre != null);
		this.head = newHead;
		return this;
	}


	public static void main(String[] args) {
		char[] chars = {'a','b','1','d'};
//		char[] chars1 = {'1', 'f'};
		LinkedString ls = new LinkedString(chars);
		StringInterface nls = ls.reverse();
		for (int i =0 ; i < nls.length(); i ++)
		{
			WoodsPrinter.println(nls.charAt(i));
		}
	}
}
