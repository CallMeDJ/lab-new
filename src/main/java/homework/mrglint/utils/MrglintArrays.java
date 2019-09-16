package homework.mrglint.utils;

/**
 * @author luhuancheng
 * @date 2019/7/17
 */
public class MrglintArrays {

    /**
     * 以[1,2,3,4]形式的字符串输出数组内容
     * @param data 数组
     * @return
     */
    public static String toString(int[] data) {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < data.length; i++) {
            result.append(data[i]);
            if (i != data.length - 1) {
                result.append(",");
            }
        }
        result.append("]");
        return result.toString();
    }
}
