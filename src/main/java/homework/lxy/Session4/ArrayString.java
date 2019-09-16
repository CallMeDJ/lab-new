package homework.lxy.Session4;



import homework.lxy.utils.StringUtils;

import java.util.Arrays;

/**
 * @Author 大蕉
 * 这里你自己实现的时候，要去掉 abstract
 */
public class ArrayString implements StringInterface {

	char[] chars;

	ArrayString(){chars = "".toCharArray();}

	ArrayString(char value[]){
		chars = Arrays.copyOf(value,value.length);
	}

	ArrayString(String str){
		chars = str.toCharArray();
	}

	/**
	 * 根据数字返回对应的字符串
	 * @param number
	 * @return
	 */
	public static StringInterface valueOf(Integer number) throws NumberFormatException{
		int[] intValue = new int[8];
		if(number != null){
			int n = number;
			boolean isNegative = n < 0;
			if(isNegative){
				n = - n;
			}
			int length = 0;
			//计算数字的位数并将其每一位按照倒序输入到intValue数组
			do{
				intValue[length++] = n % 10;
			}while ((n = n/10) > 0);
			//根据位数创建char[],如果为负数则保留符号位的位置
			char[] charValue = new char[isNegative ? length+1 : length];
			//拷贝intValue->charValue
			for(int i = 0; i<length; i++){
				charValue[i] = (char)('0'+intValue[i]);
			}
			//补充符号位
			if(isNegative){
				charValue[length-1] = '-';
			}
			//反转
			StringUtils.reverseChar(charValue);
			return new ArrayString(charValue);
		}
		return new ArrayString();
	}

	@Override
	public int length() {
		if(chars != null){
			return chars.length;
		}
		return 0;
	}

	@Override
	public char charAt(int position) {
		if(position < 0 && position > chars.length-1){
			throw new StringIndexOutOfBoundsException(position);
		}
		return chars[position];
	}

	@Override
	public int indexOf(char[] target) {
		if(target == null || target.length == 0 || target.length > chars.length){
			return -1;
		}
		int targetLength = target.length;
		int srcLength = this.chars.length;
		int index = -1;
		for(int i = 0; i < srcLength; i++){
			//没有匹配到第一个字符并且当前字符与目标的第一个字符不相等，跳过
			if(index == -1 && chars[i] != target[0]){
				continue;
			}
			//没有匹配到第一个字符并且剩余长度小于target的长度，匹配失败
			if(index == -1 && (srcLength - i) < targetLength){
				return -1;
			}
			char value = this.charAt(i);
			//获取target中应匹配字符
			int j = 0;
			if(index != -1){
				j = i-index;
			}
			char targetValue = target[j];
			//匹配
			if(value == targetValue){
				if(index == -1){
					index = i;
				}
				if(j == targetLength - 1){
					return index;
				}
			}else {
				i = index;//防止重复字符漏匹配，如12123中匹配123
				index = -1;
			}
		}
		return index;
	}

	@Override
	public StringInterface subString(int start, int end) {
		if(start > this.chars.length
				|| end > this.chars.length
				|| start > end){
			throw new IllegalArgumentException();
		}
		if(start < 0){
			start = 0;
		}
		return new ArrayString(Arrays.copyOfRange(this.chars,start,end));
	}

	@Override
	public StringInterface reverse() {
		StringUtils.reverseChar(this.chars);
		return new ArrayString(this.chars);
	}

	@Override
	public String toString(){
		return String.valueOf(this.chars);
	}
	public static void main(String[] args) {
		StringInterface stringInterface  = valueOf(4321321);
		stringInterface.reverse();
		System.out.println(stringInterface.toString());
		StringInterface newStringInterface = stringInterface.subString(-1,3);
		System.out.println(newStringInterface);

		System.out.println(stringInterface.length());

		System.out.println(stringInterface.indexOf(new char[]{'1','2','3'}));
		System.out.println(newStringInterface.indexOf(new char[]{'2','3','4','5'}));

	}

}
