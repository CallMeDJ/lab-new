package homework.tonemy.session4;


import java.util.Arrays;

/**
 * @author tonemy
 * 实现的时候，要去掉 abstract
 */
public  class ArrayString implements StringInterface {

	char[] chars;
	/**
	 * 初始化
	 */
	public ArrayString(String str) {
		this.chars = str.toCharArray();
	}
	public ArrayString(char[] str) {
		chars = new char[str.length];
		for (int i = 0; i < str.length; i ++) {
			chars[i] = str[i];
		}
	}
	/**
	 * 根据数字返回对应的字符串
	 * @param number
	 * @return
	 */
	public  StringInterface  valueOf(Integer number)  {
		if (number < Integer.MIN_VALUE || number > Integer.MAX_VALUE) {
			try {
				// 不在int范围的数字出现异常
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		boolean flag = false;
		//用来标识 正(false)负(true)
		int newNum  = number;
		newNum = Math.abs(newNum);
		if(newNum == number) {
			flag = false;
		} else {
			flag = true;
		}
		//计算number除了符号位还有几位数字
		int tol = 0, temp = newNum;
		while (temp != 0) {
			temp /= 10 ;
			tol ++;
		}
		char[] res = new char[tol];
		int index = 0;
		while (newNum != 0) {
			res[index ++] = (char)(newNum % 10 +'0');
			newNum /= 10;
		}
		//判断是否加上负号
		if(flag) {
			res[index ++] = '-';
		}
		//此时，数组res中存储的整数的顺序是倒着的
		for(int i = 0; i < res.length/ 2; i ++) {
			char tmp = res[i];
			res[i] = res[res.length - i - 1];
			res[res.length - i - 1] = tmp;
		}
		return  new ArrayString(res);
	}

	/**
	 * 字符串的长度
	 *
	 * @return
	 */
	@Override
	public int length() {
		return chars.length;
	}

	/**
	 * 获取第 position 个字符
	 *
	 * @param position
	 * @return
	 */
	@Override
	public char charAt(int position) {
		if (position < 0 || position >= chars.length) {
			try {
				//超出数组长度限制异常
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return chars[position];
	}

	/**
	 * 根据target 获取第一个匹配的字符串的位置，如果没找到，返回 -1
	 * 字符串匹配算法
	 * @param target
	 * @return
	 */
	@Override
	public int indexOf(char[] target) {

		StringInterface tarStr = new ArrayString(target);
		StringInterface oriStr = this.subString(0, chars.length);
		int[] next = getNext(tarStr);
	 	int i = 0, j = 0;
//		System.out.println(Arrays.toString(next));
//		System.out.println(tarStr.length() + ":" + oriStr.length());
		while (j < tarStr.length() && i < oriStr.length()) {
			if( j == -1 || tarStr.charAt(j) == oriStr.charAt(i)){
				i ++;
				j ++;
			}else {
				j = next[j];
			}
		}
//		System.out.println(j +"," + i);
		if (j == tarStr.length()) {
			return i - j;
		}
		return -1;
	}
	public int[] getNext(StringInterface str) {

		int[] next = new int[str.length()];
		int i = 0, j = -1;
		next[0] = -1;
//		System.out.println( charts.length);
		while (i < str.length() - 1) {
			if(j == -1 || str.charAt(i) == str.charAt(j)) {
				++i;
				++j;
			//	System.out.println("i = " + i +" j = " + j);
				next[i] = j;
			}else {
				j = next[j];
			}
		}
		return next;
	}

	/**
	 * 根据开始节点和结束节点，返回新的字符串。
	 *
	 * @param start
	 * @param end
	 * @return
	 */
	@Override
	public StringInterface  subString(int start, int end) {
		if(start < 0 || start > chars.length || end < 0 || end > chars.length || start > end) {
			try {
				//索引位置异常
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (start == end) {
			return new ArrayString("");
		}
		char[] res = new char[end - start + 1];
		int index = 0;
		for (int i = start; i < end; i ++) {
				res[index ++] = chars[i];
		}
		return new ArrayString(res);
	}

	/**
	 * 首尾翻转字符串，要求只能占用 O(1) 的额外空间
	 *
	 * @return
	 */
	@Override
	public StringInterface  reverse() {
		for (int i = 0; i < chars.length / 2; i ++) {
			char left = chars[i];
			char right = chars[chars.length - i - 1];
			chars[i] = right;
			chars[chars.length - i - 1] = left;
		}
		return new ArrayString(chars);
	}
}
