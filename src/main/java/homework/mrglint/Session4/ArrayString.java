package homework.mrglint.Session4;


import java.util.Arrays;

/**
 * @Author 大蕉
 * 这里你自己实现的时候，要去掉 abstract
 */
public class ArrayString implements StringInterface {

    public ArrayString(char[] data) {
        chars = Arrays.copyOf(data, data.length);
    }

    private char[] chars;

    private final static int [] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999,
            99999999, 999999999, Integer.MAX_VALUE };

    /**
     * 借用一下JDK的代码，用于获取转换后的字符串长度
     * @param x
     * @return
     */
    static int stringSize(int x) {
        for (int i=0; ; i++) {
            if (x <= sizeTable[i]) {
                return i + 1;
            }
        }
    }

    private final static char[] NUMBERS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    /**
     * 根据数字返回对应的字符串
     *
     * @param number
     * @return
     */
    public static StringInterface valueOf(Integer number) throws NumberFormatException {
        boolean negative = number < 0;
        if (number < 0) {
            number = - number;
        }
        int length = stringSize(number);
        char[] result;
        if (negative) {
            // 负数
            result = new char[++length];
        } else {
            result = new char[length];
        }
        while (true) {
            int mod = number % 10;
            result[--length] = NUMBERS[mod];
            number = number / 10;
            if (number == 0) {
                break;
            }
        }
        if (negative) {
            result[--length] = '-';
        }
        return new ArrayString(result);
    }

    @Override
    public int length() {
        return chars.length;
    }

    @Override
    public char charAt(int position) {
        if (position < 0 || position >= chars.length) {
            throw new IllegalArgumentException("参数非法");
        }
        return chars[position];
    }

    @Override
    public int indexOf(char[] target) {
        // 原串头指针
        int i = 0;
        // 原串尾指针
        int j = 0;
        // 匹配串指针
        int x = 0;
        // 当原串剩余长度小于匹配串长度时，退出循环
        while (chars.length - i >= target.length) {
            if (chars[j] == target[x]) {
                // 匹配串指针到达最后一个字符，匹配成功
                if (x == target.length - 1) {
                    return i;
                }
                x++;
                j++;
            } else {
                x = 0;
                j = i + 1;
                i++;
            }
        }
        return -1;
    }

    @Override
    public StringInterface subString(int start, int end) {
        if (start < 0 || end < 0 || start >= chars.length || end >= chars.length) {
            throw new IllegalArgumentException("参数非法");
        }
        if (start > end) {
            throw new IllegalArgumentException("参数非法");
        }
        char[] subChar = new char[end - start + 1];
        int i = 0;
        while (start <= end) {
            subChar[i++] = chars[start++];
        }
        return new ArrayString(subChar);
    }

    @Override
    public StringInterface reverse() {
        char[] result = new char[chars.length];
        int i = 0;
        int j = chars.length - 1;
        while (i <= j) {
            result[i] = chars[j];
            result[j] = chars[i];
            i++;
            j--;
        }
        return new ArrayString(result);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            res.append(chars[i]);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        StringInterface s = new ArrayString(new char[]{'a', 'b', 'c', 'd'});
        System.out.println(s.reverse());
        System.out.println(s.length());
        System.out.println(s.subString(0, 3));
        System.out.println(ArrayString.valueOf(-123));

        System.out.println(s.indexOf(new char[]{'b', 'd'}));
    }
}
