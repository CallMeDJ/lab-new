package homework.stark.session4;


import java.util.Arrays;
import java.util.Objects;

/**
 * @Author Stark
 * 数组实现String 接口
 */
public class ArrayString implements StringInterface {

    /**
     * The chars is used for character storage.
     */
    char[] chars;

    public ArrayString() {
        this.chars = "".toCharArray();
    }

    public ArrayString(char[] chars) {
        if (chars == null) {
            throw new NullPointerException("字符串为空哦～");
        }
        this.chars = Arrays.copyOf(chars, chars.length);
    }

    public ArrayString(String str) {
        if (str == null) {
            throw new NullPointerException("字符串为空哦～");
        }
        this.chars = str.toCharArray();
    }

    /**
     * 根据数字返回对应的字符串
     * 参考了JDK的Integer.toString 实现
     *
     * @param number
     * @return
     */
    final static int[] sizeTable = {9, 99, 999, 9999, 99999, 999999, 9999999,
            99999999, 999999999, Integer.MAX_VALUE};

    final static char[] Digit = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
    };

    static int stringSize(int x) {
        for (int i = 0; ; i++) {
            if (x <= sizeTable[i]) {
                return i + 1;
            }
        }
    }

    public StringInterface valueOf(Integer number) throws NumberFormatException {
        if (number == Integer.MIN_VALUE) {
            return new ArrayString("-2147483648");
        }

        int size = number < 0 ? stringSize(-number) + 1 : stringSize(number);

        char sign = 0;
        char buf[] = new char[size];
        if (number < 0) {
            sign = '-';
            number = -number;
        }
        //2147483648

        while (true) {
            int mod = number % 10;
            buf[--size] = Digit[mod];
            number = number / 10;
            if (number == 0) {
                break;
            }
        }

        if (sign != 0) {
            buf [--size] = sign;
        }

        return new ArrayString(buf);
    }

    @Override
    public int length() {
        return chars.length;
    }

    @Override
    public char charAt(int position) {
        if (position < 0 || position >= chars.length) {
            throw new IllegalArgumentException("越界了哦～");
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
            throw new IllegalArgumentException("越界了哦～");
        }
        if (start > end) {
            throw new IllegalArgumentException("开始不能大于结束～");
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
}
