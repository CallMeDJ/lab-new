package homework.zhou.session4;


import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @Author 大蕉
 * 这里你自己实现的时候，要去掉 abstract
 */
public  class ArrayString implements StringInterface {

	char[] chars;

	public ArrayString(char[] chars) {
		this.chars = chars;
	}

	/**
	 * 根据数字返回对应的字符串
	 * @param number
	 * @return
	 */
	public static StringInterface valueOf(Integer number) throws NumberFormatException{
		List<Integer> values = Lists.newArrayList();

		int currentValue = number;

		while (currentValue != 0){
			int nextNumber = currentValue % 10;
			values.add(nextNumber);
			currentValue = currentValue/10;
		}
		int size = values.size();
		char[] res = new char[size];
		for (int i = size -1 ; i>=0; i--){
			res[size-1-i] = (char)('0'+values.get(i));
		}
		return new ArrayString(res);
	}

	@Override
	public int length() {
		return chars.length;
	}

	@Override
	public char charAt(int position) {
		if ((position < 0) || (position >= chars.length)) {
			throw new StringIndexOutOfBoundsException(position);
		}
		return chars[position];
	}

	@Override
	public int indexOf(char[] target) {
		Objects.requireNonNull(target);
		//进行空判断，或者匹配的字符串大于原字符串返回-1
		if (target.length == 0 || target.length > chars.length) {
			return -1;
		}

		int max = chars.length - target.length;
		for (int i = 0; i <= max; i++) {
			if (target[0] == chars[i]) {
				for (int j = 1; j < target.length; j++) {
					if (target[j] != chars[i + j]) {
						return -1;
					}
				}
				return i;
			}
		}
		return -1;
	}

	@Override
	public StringInterface subString(int start, int end) {
		if (start < 0) {
			throw new StringIndexOutOfBoundsException(start);
		}
		if (end > chars.length) {
			throw new StringIndexOutOfBoundsException(end);
		}
		int subLen = end - start;
		if (subLen < 0) {
			throw new StringIndexOutOfBoundsException(subLen);
		}
		char[] chars = Arrays.copyOfRange(this.chars, start, end);
		return new ArrayString(chars);

	}

	@Override
	public StringInterface reverse() {
		for (int i = 0; i < chars.length / 2; i ++) {
			char left = chars[i];
			char right = chars[chars.length - i - 1];
			chars[i] = right;
			chars[chars.length - i - 1] = left;
		}
		return new ArrayString(chars);
	}

	/**
	 * 方便测试打印输出
	 * @return
	 */
	@Override
	public String toString() {
		return  Arrays.toString(chars);
	}
}
