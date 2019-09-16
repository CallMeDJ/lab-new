package homework.woods.session1;

import homework.woods.constants.WoodsConstants;
import homework.woods.exception.BizServiceException;
import homework.woods.utils.WoodsPrinter;

import java.util.Arrays;

/**
 * 西瓜摊
 *
 * Q1、如果没顾客，那就不卖
 *
 * Q2、来了N个顾客，要按顺序卖，不卖的客户也要告诉他不卖（卖0）。
 *
 * Q3、单个卖的逻辑在 sell0 实现。
 *
 * Q4、如果超过50个，只卖50个。
 *
 * Q5、如果需求小于0个，sell0 抛出异常，在 sell 捕获异常并登记为不卖（卖0）。
 *
 * Q6、在控制台打印出最终这批卖出去多少个。术语：xxx总共卖出去N个。
 *
 * Q7、实现自己的打印数组函数。格式: [1,2,3,4,5];
 */

public class SessionWaterMelon {

    public static void main(String[] args) {
        // test Q1, Q7
        int[] test1 = {};
        test(test1);
        // test Q2, Q3, Q4, Q5, Q6, Q7
        int[] test2 = {-1,0,3,55};
        test(test2);

    }

    public static void test(int[] demandArray)
    {
        WoodsPrinter.print("demand:");
        WoodsPrinter.printArray(demandArray);
        int[] deal = sell(demandArray);
        WoodsPrinter.print("deal:");
        WoodsPrinter.printArray(deal);
        if (deal == null)
        {
            WoodsPrinter.println("woods这波卖出0个西瓜");
        }else {
            WoodsPrinter.println("woods这波卖出" + Arrays.stream(deal).sum() + "个西瓜");
        }
    }


    /**
     * 排队卖西瓜
     * @param buyNum
     * @return
     */
    public static int[] sell(int[] buyNum){
        int batchSum = 0;
        // 如果没顾客，那就不卖
        if (buyNum == null || buyNum.length == 0)
        {
            return null;
        }
        int[] dealNum = new int[buyNum.length];

        // 来了N个顾客，要按顺序卖
        for (int i = 0; i < buyNum.length; i++)
        {
            try {
                dealNum[i] = sell0(buyNum[i]);
                batchSum += dealNum[i];
            }catch (BizServiceException bpe)
            {
                dealNum[i] = 0;
            }
        }
        return dealNum;
    }

    /**
     * 单客卖西瓜
     * @param buyNum
     * @return
     */
    private static int sell0(int buyNum) throws BizServiceException {
        if (buyNum <= 0)
        {
            // 如果需求小于0个，抛出异常
            throw new BizServiceException("参数错误：buyNum = " + buyNum);
        }
        // 如果超过50个，只卖50个。
        if (buyNum > WoodsConstants.MAX_SELL_NUM_SINGLE_CUSTOM)
        {
            buyNum = WoodsConstants.MAX_SELL_NUM_SINGLE_CUSTOM;
        }
        return buyNum;
    }

}
