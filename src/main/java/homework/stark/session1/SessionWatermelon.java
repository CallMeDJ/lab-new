package homework.stark.session1;

import homework.stark.common.Printer;

/**
 * 实现一个卖西瓜的摊摊函数
 * 1、如果没顾客，那就不卖
 * 2、来了N个顾客，要按顺序卖，不卖的客户也要告诉他不卖（卖0）。
 * 3、单个卖的逻辑在 sell0 实现。
 * 4、如果超过50个，只卖50个。
 * 5、如果需求小于0个，sell0 抛出异常，在 sell 捕获异常并登记为不卖（卖0）。
 * 6、在控制台打印出最终这批卖出去多少个。术语：xxx总共卖出去N个。
 * 7、实现自己的打印数组函数。格式: [1,2,3,4,5];
 *
 * @auther Stark
 * @date 2019-07-15
 */

public class SessionWatermelon {
    /**
     * 单次可售卖最大数量
     */
    private static int MAX_SOLD_NUM = 50;

    public static void main(String[] args) {
        int[] testCases = new int[]{1, -2, 0, 4, 30, 70};
        int[] testCasesResult = sell(testCases);
        Printer.printArray(testCasesResult);
    }

    /**
     * 按顾客所需售卖逻辑
     * @param buyNum 顾客购买数量意愿
     * @return 顾客实际购买数量
     */
    public static int[] sell(int[] buyNum) {
        if (buyNum == null || buyNum.length == 0) {
            return new int[]{0};
        }
        // 卖给顾客数量
        int[] soldNum = new int[buyNum.length];
        // 卖出的总数量
        int sumNum = 0;

        for (int i = 0; i < buyNum.length; i++) {
            try {
                soldNum[i] = sell0(buyNum[i]);
                sumNum += soldNum[i];
            } catch (Exception e) {
                soldNum[i] = 0;
            }
        }

        System.out.println("Stark 总共卖出去" + sumNum + "个");
        return soldNum;
    }

    /**
     * 单个售卖逻辑
     * @param buyNum 顾客购买数量意愿
     * @return 顾客实际购买数量
     * @throws Exception
     */
    private static int sell0(int buyNum) throws Exception {

        if (buyNum > MAX_SOLD_NUM) {
            return MAX_SOLD_NUM;
        } else if (buyNum < 0) {
            throw new Exception("数量不合法");
        } else {
            return buyNum;
        }
    }
}
