package homework.woods.utils;

import homework.woods.session4.ArrayString;
import homework.woods.session4.LinkedString;
import homework.woods.session4.StringInterface;

/**
 * @author: woods
 * @date: 2019/8/10
 * @description:
 */
public class KMPMatcher {


    public static int match(StringInterface source, char[] dest)
    {
        int sIndex = 0;
        int dIndex = 0;
        int[] nextArray = getNextArray(dest);

        while (sIndex < source.length() && dIndex < dest.length)
        {
            if (dIndex == -1 ||source.charAt(sIndex) == dest[dIndex])
            {
                sIndex ++;
                dIndex ++;
            }else {
                dIndex = nextArray[dIndex];
            }
        }

        if (dIndex == dest.length)
        {
            return sIndex - dIndex;
        }

        return -1;
    }


    /**
     * 目标串下一个匹配位置数组
     * @param dest
     * @return
     */
    public static int[] getNextArray(char[] dest)
    {
        int[] nextArray = new int[dest.length];
        nextArray[0] = -1;
        int k = -1, j = 0;
        while (j < dest.length-1)
        {
            if (k == -1 || dest[k] == dest[j])
            {
                ++k;
                ++j;
                nextArray[j] = k;
            }else {
                k = nextArray[k];
            }
        }

        return nextArray;
    }

    public static void main(String[] args) {
        String source = "BBC ABCDAB ABCDABCDABDE";
        String dest = "BC A";

        WoodsPrinter.println(match(new LinkedString(source.toCharArray()), dest.toCharArray()));
        WoodsPrinter.println(match(new ArrayString(source.toCharArray()), dest.toCharArray()));
    }
}
