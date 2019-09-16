package com.bigbanana.lab.lab2.seven;

import com.bigbanana.lab.lab2.String2Integer;
import org.junit.Test;

/**
 * @Author Seven
 */
public class String2IntegerBigBabanaImpl extends String2Integer {
	@Override
	public Integer string2Integer(String target) {
		// 整型结果
		int integer = 0;
		// 记录当前位（个十百千万...）
		int index = 1;
		// 检测空指针
		if (target == null || target.length() == 0) {
			return 0;
		} else {
			byte temp;
			// 遍历字符串中的字符并区别处理
			for (int i = target.length() - 1; i >= 0; i--) {
				temp = (byte)(target.charAt(i) - '0');
				// 过滤无效字符
				if (temp >= 0 && temp <= 9) {
					// 处理溢出
					if ((integer > Integer.MAX_VALUE % 1000000000 && temp > 1) || integer >= 1000000000) {
						return Integer.MIN_VALUE;
					}
					// 提取整数
					integer += temp * index;
					index *= 10;
				}
				// 负数
				if (temp + '0' == '-') {
					integer *= -1;
				}
			}
		}

		return integer;
	}

	@Test
	public void bananaTest(){
		String2IntegerBigBabanaImpl demo = new String2IntegerBigBabanaImpl();
		super.test(demo);
	}

}
