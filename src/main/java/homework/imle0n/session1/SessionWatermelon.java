package homework.imle0n.session1;

/**
 * @author iMLe0n
 * @version 1.0.0
 * @date 19-7-14 下午10:58
 */
public class SessionWatermelon {
    public static int MaxNum = 50;

    public static void main(String[] args) {
        int[] nums1 = {-1, 0, 10, 51};
        int[] sell = sell(nums1);
        arrayPrint(sell);
    }

    public static int[] sell(int[] buyNum) {
        if (buyNum == null || buyNum.length == 0) {
            return new int[0];
        }

        int[] result = new int[buyNum.length];
        int buyNumCount = 0;

        for (int i = 0; i < buyNum.length; i++) {
            int currentBuyNUm = buyNum[i];
            int sellNum = 0;
            try {
                sellNum = sell0(currentBuyNUm);
            } catch (Exception e) {
                System.err.println(e.getMessage() + " --------> " + currentBuyNUm);
                sellNum = 0;
            }

            result[i] = sellNum;
            buyNumCount += sellNum;
        }

        System.out.println("iMLe0n总卖出" + buyNumCount + "个");
        return result;
    }

    private static int sell0(int buyNum) throws Exception {
        if (buyNum < 0) {
            throw new Exception("售卖数不能为负数!");
        }
        if (buyNum > MaxNum) {
            return MaxNum;
        }
        return buyNum;
    }

    private static void arrayPrint(int[] array) {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                stringBuilder.append(array[i]);
            } else {
                stringBuilder.append(",").append(array[i]);
            }
        }
        stringBuilder.append("]");
        System.out.println(stringBuilder.toString());
    }

}
