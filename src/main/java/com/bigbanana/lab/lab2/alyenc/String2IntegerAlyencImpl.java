package com.bigbanana.lab.lab2.alyenc;

import com.bigbanana.lab.lab2.String2Integer;
import org.junit.Test;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description 有点粗糙
 * @package com.bigbanana.lab.lab2.alyenc
 * @author alyenc
 * @email alyenc@outlook.com
 * @date 2018/7/6 17:17
 * @version v1.0.0
 */
public class String2IntegerAlyencImpl extends String2Integer {

    private static String regEx  = "-*\\d+(\\.\\d+)?";
    private static Pattern pattern = Pattern.compile(regEx);

    @Override
    public Integer string2Integer(String target) {
        Matcher matcher = pattern.matcher(Optional.ofNullable(target).orElse("0"));

        if(!matcher.find()) {
            return Integer.MIN_VALUE;
        }
        //提取匹配到的结果
        String string = matcher.group();
        Long longValue = Long.valueOf(string);

        if(longValue > Integer.MAX_VALUE){
            return Integer.MIN_VALUE;
        }

        if(Integer.MIN_VALUE > longValue){
            return Integer.MIN_VALUE;
        }

        return Integer.valueOf(string);
    }

    @Test
    public void alyencTest(){
        String2IntegerAlyencImpl demo = new String2IntegerAlyencImpl();
        super.test(demo);
    }
}
