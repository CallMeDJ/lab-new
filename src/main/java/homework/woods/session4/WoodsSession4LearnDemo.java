package homework.woods.session4;

import homework.woods.utils.WoodsPrinter;

/**
 * @author: woods
 * @date: 2019/8/6
 * @description: 学习时撸的代码
 */
public class WoodsSession4LearnDemo {

    public static void main(String[] args) {

//        String s = "BBC ABCDAB ABCDABCDABDE";
        String p = "ABCDABD";
        String pp = "ABCDABCE";

        WoodsPrinter.printArray(kmpNextArray(p));
        WoodsPrinter.printArray(kmpNextArray(pp));

//        String s = "eeeeeeabceeeabc";
//        String p = "abc";
//
//        WoodsPrinter.println(violent(s, p));
//
//        WoodsPrinter.println("========================================");
//
//        WoodsPrinter.println(violentMatch(s, p));
    }



    /**
     * 示例
     * @param destStr
     * @return
     */
    public static int[] kmpNextArray(String destStr){

        int[] next = new int[destStr.length()];
        next[0] = -1;
        int k = -1, j = 0;
        while (j < destStr.length()-1)
        {
            if (k == -1 || destStr.charAt(k) == destStr.charAt(j))
            {
                ++k;
                ++j;
                next[j] = k;
            }else {
                k = next[k];
            }
        }
        return next;
    }

    public static int violent(String s, String p)
    {
        int sLength = s.length();
        int pLength = p.length();
        int sIndex = 0;
        int pIndex = 0;

        int compareCounts = 0;

        while (sIndex < sLength && pIndex < pLength)
        {
            compareCounts ++;
            if (s.charAt(sIndex) == p.charAt(pIndex))
            {
                sIndex ++ ;
                pIndex ++ ;
            }else {
                sIndex = sIndex - (pIndex -1);
                pIndex = 0;
            }
        }

        if (pIndex == pLength) {
            WoodsPrinter.println("compareCounts = " + compareCounts);
            return sIndex - pIndex;
        }

        WoodsPrinter.println("compareCounts = " + compareCounts);
        return -1;
    }

    /**
     * 模拟暴力匹配算法
     * @param s
     * @param p
     * @return
     */
    public static int violentMatch(String s, String p)
    {
        char[] sChar = s.toCharArray();
        char[] pChar = p.toCharArray();
        int sIndex = 0;

        int compareCounts = 0;

        while (sIndex <= sChar.length-pChar.length ) {
            for (int i = sIndex, j = 0; i < sChar.length; i++) {

                compareCounts ++ ;
                while (pChar[j] == sChar[i]) {

                    if (j == pChar.length-1)
                    {
                        WoodsPrinter.println("compareCounts = " + compareCounts);
                        return sIndex;
                    }

                    j++;
                    i++;
                    compareCounts ++;
                }
                sIndex ++;
                break;
            }
        }

        WoodsPrinter.println("compareCounts = " + compareCounts);
        return -1;
    }

}
