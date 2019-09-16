package com.bigbanana.lab.lab2.MrC;

import com.bigbanana.lab.lab2.String2Integer;
import org.junit.Test;

/**
 * @Author Mr.C
 */
public class String2IntegerBigBabanaImpl extends String2Integer {
	@Override
	public Integer string2Integer(String target) {

	    //定义ASCii的二进制常量，0和9的二进制。用来比较每个字符的二进制。
        //0的ASCII二进制
        final int forty_Eight = 0b110000;

        //9的ASCII二进制
        final int fifty_Seven = 0b111001;

        //定义个空字符串，用来拼串
        String str = "";

        //判断是否为空
        if(target !=null){

            //取每个字符进行判断
            outer:for(int i = 0; i<target.length(); i++){

                char c = target.charAt(i);

                //如果有空格直接进行下一次循环
                if(c == ' '){
                    continue outer;
                }
                //如果有负号直接拼到str上
                if(c == '-'){
                    str +=c;
                }

                //如果截取的字符在ASCII 48~57之内，说明是数字0~9
                if(forty_Eight <= c && c <= fifty_Seven){
                    str += c;
                }
            }

            //判断最后拼成的串的值，超出Integer返回MIN_VALUE
            if(str.length()>=10){
                Long lastLongValue = Long.parseLong(str);
                if(lastLongValue >= Integer.MAX_VALUE || lastLongValue <= Integer.MIN_VALUE){
                    return Integer.MIN_VALUE;
                }

            }
            Integer lastValue = Integer.parseInt(str);
            return lastValue;
        }
        return 0;
	}

	@Test
	public void bananaTest(){
		String2IntegerBigBabanaImpl demo = new String2IntegerBigBabanaImpl();
		super.test(demo);
	}

}
