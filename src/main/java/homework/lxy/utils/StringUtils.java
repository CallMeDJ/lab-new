package homework.lxy.utils;

/**
 * Created by liuxinyan on 2019/8/10.
 */
public class StringUtils {
    public static void reverseChar(char[] chars){
        if(chars == null || chars.length <= 1){
            return;
        }
        int length = chars.length;
        for(int i = 0; i<length/2; i++){
            char temp = chars[i];
            chars[i] = chars[length-i-1];
            chars[length-i-1] = temp;
        }
    }
}
