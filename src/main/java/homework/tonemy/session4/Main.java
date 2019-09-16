package homework.tonemy.session4;

/**
 * Created by tonemy on 2019/7/30.
 * 测试
 */
public class Main {
    public static void main(String[] args) {
        // ArrayString 测试
        System.out.println("ArrayString 测试 : ---------------");
        ArrayString arr = new ArrayString("ababcabcdabc");
        System.out.println("原始字符串 : " + "ababcabcdabc");

        System.out.println("长度 : "+ arr.length());
        char[] tar = {'a','b','c','d','a','b'};
        System.out.println("字符串"+String.valueOf(tar)+"匹配原始字符串的索引为 ： "+ arr.indexOf(tar));
        System.out.println("字符串末尾字符为 : " + arr.charAt(arr.length() - 1));


        // LinkedString 测试
        System.out.println("LinkedString 测试 : ---------------");
        LinkedString link = new LinkedString("ababcabcdabc");

        System.out.println("长度 : "+ link.length());
        char[] tar0 = {'a','b','c','d','a','b'};
        System.out.println("字符串"+String.valueOf(tar0)+"匹配原始字符串的索引为 ： "+ link.indexOf(tar));
        System.out.println("字符串末尾字符为 : " + link.charAt(link.length() - 1));

    }
}
