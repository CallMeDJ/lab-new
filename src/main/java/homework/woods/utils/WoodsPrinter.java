package homework.woods.utils;

/**
 * @author: woods
 * @date: 2019/7/21
 * @description:
 */
public class WoodsPrinter {

    public static void print(Object value)
    {
        System.out.print(value);
    }

    public static void println(Object value)
    {
        System.out.println(value);
    }

    /**
     * 打印int数组
     * @param array
     */
    public static void printArray(int[] array)
    {
        if (array == null)
        {
            println("[]");
            return;
        }
        if (array.length == 0)
        {
            println("[]");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i=0; i<array.length; i++)
        {
            sb.append(array[i]);
            if (i != array.length-1)
            {
                sb.append(",");
            }
        }
        sb.append("]");
        println(sb.toString());
    }
}
