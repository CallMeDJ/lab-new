package homework.jianxing.session4;


import homework.jianxing.utils.StringUtils;

import java.util.Arrays;
import java.util.Objects;

/**
 * Immutable linked string
 *
 * @author logow.whl
 */
public final class LinkedString implements StringInterface {

    private final LinkedNode head;
    private final int length;

    public LinkedString() {
        this(null, 0);
    }

    public LinkedString(String str) {
        this(Objects.requireNonNull(str).toCharArray());
    }

    public LinkedString(char[] chars) {
        Objects.requireNonNull(chars);
        this.length = chars.length;
        if (chars.length == 0) {
            this.head = null;
        } else {
            this.head = new LinkedNode(chars[0]);
            LinkedNode prev = this.head;
            for (int i = 1; i < chars.length; i++) {
                LinkedNode next = new LinkedNode(chars[i]);
                next.setPrevious(prev);
                prev.setNext(next);
                prev = next;
            }
        }
    }

    LinkedString(LinkedNode head, int length) {
        this.head = head;
        this.length = length;
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

        return new LinkedString(chars);
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public char charAt(int position) {
        if (position < 0 || position >= length) {
            throw new StringIndexOutOfBoundsException(position);
        }

        LinkedNode node = indexOf(position);
        return node.getValue();
    }

    // 可根据 position 与首尾节点的距离，从不同方向遍历，以优化性能
    private LinkedNode indexOf(int index) {
        int pos = 0;
        for (LinkedNode node = head; node != null; node = node.getNext()) {
            if (index == pos++) {
                return node;
            }
        }
        throw new IllegalStateException("should not happen");
    }

    @Override
    public int indexOf(char[] target) {
        Objects.requireNonNull(target);
        if (target.length == 0 || target.length > length) {
            return -1;
        }

        int max = length - target.length;
        int index = 0;
        for (LinkedNode node = head; index <= max && node != null; node = node.getNext()) {
            if (target[0] == node.getValue()) {
                LinkedNode tmp = node.getNext();
                for (int j = 1; j < target.length; j++) {
                    if (target[j] == tmp.getValue()) {
                        tmp = tmp.getNext();
                    } else {
                        return -1;
                    }
                }
                return index;
            }
            index++;
        }

        return -1;
    }

    @Override
    public StringInterface subString(int start, int end) {
        if (start < 0) {
            throw new StringIndexOutOfBoundsException(start);
        }
        if (end > length) {
            throw new StringIndexOutOfBoundsException(end);
        }
        int subLen = end - start;
        if (subLen < 0) {
            throw new StringIndexOutOfBoundsException(subLen);
        }

        char[] chars = new char[subLen];
        int count = 0;
        for (LinkedNode node = indexOf(start); node != null && count < subLen; node = node.getNext()) {
            chars[count++] = node.getValue();
        }

        return new LinkedString(chars);
    }

    @Override
    public StringInterface reverse() {
        LinkedNode tail = indexOf(length - 1);
        LinkedNode head = new LinkedNode(tail.getValue());
        LinkedNode prev = head;
        for (LinkedNode node = tail.getPrevious(); node != null; node = node.getPrevious()) {
            LinkedNode next = new LinkedNode(node.getValue());
            prev.setNext(next);
            next.setPrevious(prev);
            prev = next;
        }

        return new LinkedString(head, length);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(length);
        for (LinkedNode node = head; node != null; node = node.getNext()) {
            sb.append(node.getValue());
        }
        return sb.toString();
    }
}
