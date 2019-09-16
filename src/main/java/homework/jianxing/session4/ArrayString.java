package homework.jianxing.session4;


import homework.jianxing.utils.StringUtils;

import java.util.Arrays;
import java.util.Objects;

/**
 * Immutable array string
 *
 * @Author logow.whl
 */
public final class ArrayString implements StringInterface {

    private static final char[] EMPTY_STRING = "".toCharArray();

    private final char[] chars;

    public ArrayString() {
        this.chars = EMPTY_STRING;
    }

    public ArrayString(String str) {
        Objects.requireNonNull(str);
        this.chars = str.toCharArray();
    }

    public ArrayString(char[] chars) {
        this(Objects.requireNonNull(chars), false);
    }

    ArrayString(char[] chars, boolean share) {
        this.chars = share ? chars : Arrays.copyOf(chars, chars.length);
    }

    /**
     * 根据数字返回对应的字符串
     */
    public static StringInterface valueOf(Integer number) {
        Objects.requireNonNull(number);

        long value = number.longValue();
        boolean isNegative = value < 0;
        value = isNegative ? -value : value;

        int len = 0;
        int[] digits = new int[8];
        do {
            digits[len++] = (int) (value % 10);
            if (len == digits.length) {
                digits = Arrays.copyOf(digits, digits.length * 2);
            }
        } while ((value = value / 10) > 0);

        char[] chars = new char[isNegative ? len + 1 : len];
        for (int i = 0; i < len; i++) {
            chars[i] = (char) ('0' + digits[i]);
        }

        if (isNegative) {
            chars[chars.length - 1] = '-';
        }

        StringUtils.reverseArray(chars);

        return new ArrayString(chars, true);
    }

    @Override
    public int length() {
        return chars.length;
    }

    @Override
    public char charAt(int position) {
        if (position < 0 || position >= chars.length) {
            throw new StringIndexOutOfBoundsException(position);
        }
        return chars[position];
    }

    @Override
    public int indexOf(char[] target) {
        Objects.requireNonNull(target);
        if (target.length == 0 || target.length > chars.length) {
            return -1;
        }

        int max = chars.length - target.length;
        for (int i = 0; i <= max; i++) {
            if (target[0] == chars[i]) {
                for (int j = 1; j < target.length; j++) {
                    if (target[j] != chars[i + j]) {
                        return -1;
                    }
                }
                return i;
            }
        }

        return -1;
    }

    @Override
    public StringInterface subString(int start, int end) {
        if (start < 0) {
            throw new StringIndexOutOfBoundsException(start);
        }
        if (end > chars.length) {
            throw new StringIndexOutOfBoundsException(end);
        }
        int subLen = end - start;
        if (subLen < 0) {
            throw new StringIndexOutOfBoundsException(subLen);
        }

        char[] chars = Arrays.copyOfRange(this.chars, start, end);
        return new ArrayString(chars, true);
    }

    @Override
    public StringInterface reverse() {
        char[] chars = Arrays.copyOf(this.chars, this.chars.length);
        StringUtils.reverseArray(chars);
        return new ArrayString(chars, true);
    }

    @Override
    public String toString() {
        return new String(this.chars);
    }
}
