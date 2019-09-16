package homework.lxy.utils;

/**
 * Created by liuxinyan on 2019/7/21.
 */
public class PrintUtils {

    public static void printArray(int[] array){
        if(array == null){
            System.out.println("[]");
            return;
        }
        StringBuilder builder = new StringBuilder("[");
        if(array.length > 0){
            for(int text : array){
                builder.append(text).append(",");
            }
            builder.deleteCharAt(builder.toString().length()-1);
            builder.append("]");
        }
        System.out.println(builder.toString());
        return;
    }
}
