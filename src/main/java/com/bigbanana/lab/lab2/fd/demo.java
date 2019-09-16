package com.bigbanana.lab.lab2.fd;

import com.bigbanana.lab.lab2.String2Integer;
import org.junit.Test;


public class demo extends String2Integer {
    @Override
    public Integer string2Integer(String target) {
        //排除null以及空字符串
        if (target == null || target.equals("")) {
            return 0;
        }
        StringBuffer sb = new StringBuffer();
        for (char everyone : target.toCharArray()) {//取出只包含
            byte b = (byte) everyone;
            if (b >= 48 && b <= 57 || b == 43 || b == 45) {
                sb.append(everyone);
            }
        }
        if (sb.charAt(0) == 43 || sb.charAt(0) == 45) {//有符号判断
            if (sb.length() > 11) {//有符号超出长度
                return Integer.MIN_VALUE;
            } else {//数字部分长度和最值长度一样
                if (sb.length() == 11 && sb.charAt(0) == 43 && sb.substring(1, sb.length()).compareTo(Integer.toString(Integer.MAX_VALUE)) > 0) {//数字部分大于Integer最大值
                    return Integer.MIN_VALUE;
                }

                if (sb.length() == 11 && sb.charAt(0) == 45 && sb.substring(1, sb.length()).compareTo("2147483648") > 0) {//数字部分大于Integer最小值的绝对值，不用Math.abs().无法取出最小值的绝对值
                    return Integer.MIN_VALUE;
                }
            }
        } else {//数字部分长度和最值长度一样
            if (sb.length() > 10) {//无符号超出长度
                return Integer.MIN_VALUE;
            }
            if (sb.length() == 10 && sb.toString().compareTo(Integer.toString(Integer.MAX_VALUE)) >= 0) {//大于Integer最大值
                return Integer.MIN_VALUE;
            }
        }
        return  Integer.valueOf(sb.toString());
    }

    @Test
    public void test() {
        demo demo = new demo();
        super.test(demo);
    }
}
