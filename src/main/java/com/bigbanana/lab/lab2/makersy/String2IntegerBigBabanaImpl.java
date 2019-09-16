package com.bigbanana.lab.lab2.makersy;

import com.bigbanana.lab.lab2.String2Integer;
import org.junit.Test;


public class String2IntegerBigBabanaImpl extends String2Integer {

	@Override
	public Integer string2Integer(String str) {
		if( str.length() == 0 ) return 0;
		int idx = 0, len = str.length();

		while( idx < len && str.charAt(idx) == ' ' ) idx++;
		if( idx >= len ){
			return 0;
		}else{
			StringBuilder sb = new StringBuilder();
			boolean flag = true;  //默认为正数
			long res = 0L;
			if( str.charAt(idx) == '-' ) {
				flag = false;
				idx++;
			} else if (str.charAt(idx) == '+') {
				idx++;
			}
			while( idx < len && str.charAt(idx)=='0' ) idx++;  //去掉0
			//找出整数字符串
			for( ; idx<len; idx++ ){
				if( str.charAt(idx)>='0' && str.charAt(idx)<='9' )
					sb.append(str.charAt(idx));
				else break;
			}
			String nums = sb.toString();
			long base = 1L;
			for( int i=nums.length()-1; i>=0; i-- ){
				int temp = nums.charAt(i)-'0';
				res += temp * base;
				if (flag && (base > Integer.MAX_VALUE || res >= Integer.MAX_VALUE)) {
					return Integer.MIN_VALUE;
				}
				if (!flag && res > Integer.MAX_VALUE) {
					return Integer.MIN_VALUE;
				}
				base *= 10L;
			}
			if( !flag ) res = -res;
			return (int) res;
		}
	}

	@Test
	public void bananaTest(){
		String2IntegerBigBabanaImpl demo = new String2IntegerBigBabanaImpl();
		super.test(demo);
	}

}
