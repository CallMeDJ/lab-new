package homework.stark.session4;

/**
 * @author zhangwei
 * @version V1.0
 * @Date 2019-08-12 08:05
 * @Description: 链式String
 * 参考了mrglint的作业
 */
public class LinkedString implements StringInterface{

    private LinkedNode head;
    private LinkedNode tail;
    private int length;

    public LinkedString(char[] data) {
        for (char e : data) {
            LinkedNode node = new LinkedNode();
            node.setValue(e);
            node.setPrevious(tail);
            if (head == null) {
                head = node;
                tail = head;
            } else {
                tail.setNext(node);
                tail = node;
            }
        }
        length = data.length;
    }

    private final static char[] NUMBERS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private final static int [] SIZE_TABLE = { 9, 99, 999, 9999, 99999, 999999, 9999999,
            99999999, 999999999, Integer.MAX_VALUE };

    /**
     * 借用一下JDK的代码，用于获取转换后的字符串长度
     * @param x
     * @return
     */
    static int stringSize(int x) {
        for (int i=0; ; i++) {
            if (x <= SIZE_TABLE[i]) {
                return i + 1;
            }
        }
    }

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
        return new LinkedString(result);
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public char charAt(int position) {
        if (position < 0 || position >= length) {
            throw new IllegalArgumentException("参数非法");
        }

        LinkedNode node = node(position);
        return node.getValue();
    }

    private LinkedNode node(int index) {
        // 根据索引的值，决定是从头部开始遍历还是从尾部开始遍历
        if (index < (length >> 1)) {
            LinkedNode x = head;
            for (int i = 0; i < index; i++) {
                x = x.getNext();
            }
            return x;
        } else {
            LinkedNode x = tail;
            for (int i = length - 1; i > index; i--) {
                x = x.getPrevious();
            }
            return x;
        }
    }

    @Override
    public int indexOf(char[] target) {
        // 原串头指针
        int i = 0;
        LinkedNode iNode = head;
        // 原串尾指针
        int j = 0;
        LinkedNode jNode = head;
        // 匹配串指针
        int x = 0;
        // 当原串剩余长度小于匹配串长度时，退出循环
        while (length - i >= target.length) {
            if (jNode.getValue() == target[x]) {
                // 匹配串指针到达最后一个字符，匹配成功
                if (x == target.length - 1) {
                    return i;
                }
                x++;
                j++;
                jNode = jNode.getNext();
            } else {
                x = 0;
                j = i + 1;
                jNode = iNode.getNext();
                i++;
                iNode = iNode.getNext();
            }
        }
        return -1;
    }

    @Override
    public StringInterface subString(int start, int end) {
        if (start < 0 || end < 0 || start >= length || end >= length) {
            throw new IllegalArgumentException("参数非法");
        }
        if (start > end) {
            throw new IllegalArgumentException("参数非法");
        }
        LinkedNode startNode = node(start);
        int resultLength = end - start + 1;
        char[] result = new char[resultLength];
        for (int i = 0; i < resultLength; i++) {
            result[i] = startNode.getValue();
            startNode = startNode.getNext();
        }
        return new LinkedString(result);
    }

    @Override
    public StringInterface reverse() {
        char[] result = new char[length];
        LinkedNode node = tail;
        int i = 0;
        while (node != null) {
            result[i++] = node.getValue();
            node = node.getPrevious();
        }
        return new LinkedString(result);
    }
}
