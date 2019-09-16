package homework.jianxing.utils;

import java.util.Objects;

public abstract class StringUtils {

    public static String toString(int[] arr) {
        Objects.requireNonNull(arr);
        return toString(arr, 0, arr.length);
    }

    public static String toString(int[] arr, int off, int len) {
        if (arr == null) {
            throw new NullPointerException();
        }
        if (off < 0 || len < 0 || len > arr.length - off) {
            throw new IndexOutOfBoundsException();
        }

        if (len == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                sb.append(arr[off + i]);
            } else {
                sb.append(", ").append(arr[off + i]);
            }
        }
        sb.append(']');

        return sb.toString();
    }

    public static void reverseArray(char[] arr) {
        Objects.requireNonNull(arr);
        int len = arr.length;
        for (int i = (len - 2) >> 1; i >= 0; i--) {
            int j = len - i - 1;
            char tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
}
