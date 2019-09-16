package homework.stark.common;

public class Printer {
    public static void printArray(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        String startString = "[";
        String endString = "]";
        StringBuilder sb = new StringBuilder();
        sb.append(startString);
        for (int arr : array) {
            sb.append(arr);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(endString);
        System.out.println(sb);
    }
}
