package homework.mrglint.Session1;

import homework.mrglint.utils.MrglintArrays;

/**
 * @author luhuancheng
 */
public class SessionWatermelon {
    public static void main(String[] args) {
        int[] buyNum = new int[]{100, -1, 10, 3, 20, 30};
        int[] result = sell(buyNum);
        System.out.println(MrglintArrays.toString(result));
    }

    public static int[] sell(int[] buyNum) {
		if (buyNum == null || buyNum.length == 0) {
		    return new int[0];
        }
        int total = 0;
		int[] result = new int[buyNum.length];
		for (int i = 0; i < buyNum.length; i++) {
		    int haveSell;
            try {
                haveSell = sell0(buyNum[i]);
            }catch (Exception e) {
                haveSell = 0;
            }
            result[i] = haveSell;
            total += haveSell;
        }

        System.out.println(String.format("mrglint总共卖出去%s个", total));
        return result;
    }

    private static int sell0(int buyNum) {
        if (buyNum < 0) {
            throw new IllegalArgumentException("概不赊账");
        }
        if (buyNum > 50) {
            buyNum = 50;
        }

        return buyNum;
    }

}
