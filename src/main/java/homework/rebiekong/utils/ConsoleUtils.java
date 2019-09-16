package homework.rebiekong.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ConsoleUtils {

    public static void printIntArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.print("[]");
        } else {
            System.out.print("[");
            System.out.print(Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(",")));
            System.out.print("]");
        }
    }
}
