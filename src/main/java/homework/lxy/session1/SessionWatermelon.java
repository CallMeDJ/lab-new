package homework.lxy.session1;

import java.util.Random;

/**

 *
 * 1、如果没顾客，那就不卖
 *
 * 2、来了N个顾客，要按顺序卖，不卖的客户也要告诉他不卖（卖0）。
 *
 * 3、单个卖的逻辑在 sell0 实现。
 *
 * 4、如果超过50个，只卖50个。
 *
 * 5、如果需求小于0个，sell0 抛出异常，在 sell 捕获异常并登记为不卖（卖0）。
 *
 * 6、在控制台打印出最终这批卖出去多少个。术语：xxx总共卖出去N个。
 *
 * 7、实现自己的打印数组函数。格式: [1,2,3,4,5];
 *
 * @author lxy
 * @date 2019-07-17 19:26
 */
public class SessionWatermelon {
    public static final String packMan = "lxy";

    public static final int upperLimit = 50;

    public static final int lowerLimit = 0;

    public static void main(String[] args) {
        int[] customersNeedNum = new int[]{5,20,51,-11,0};
        int[] sellResult = sell(customersNeedNum);
        Printer.print(sellResult);
    }

    public static int[] sell(int[] buyNum) {
        if(buyNum == null || buyNum.length == 0){
            return new int[0];
        }
        int sellNum = 0;
        int[] result = new int[buyNum.length];
        for (int i = 0; i< buyNum.length; i++) {
            int customerNeedNum = buyNum[i];
            int finalSellNum;
            if(!isHappy(customerNeedNum)){
                System.out.println("看你不顺眼，不卖");
                result[i] = 0;
                continue;
            }
            try {
                finalSellNum = sell0(customerNeedNum);
            }catch (Exception e) {
                System.out.println(e.getMessage());
                finalSellNum = 0;
            }
            sellNum += finalSellNum;
            result[i] = finalSellNum;
        }
        System.out.println(packMan+"总共卖出去"+ sellNum+"个");
        return result;
    }

    private static int sell0(int buyNum) throws Exception {
        if(buyNum < lowerLimit){
            throw new Exception("不卖（卖0)");
        }
        if(buyNum > upperLimit){
            return upperLimit;
        }else {
            return buyNum;
        }
    }

    /**
     * 看心情卖西瓜🍉,买的越多心情越好
     * @return
     */
    private static boolean isHappy(int buyNum){
        Random random = new Random();
        int moodExponent = random.nextInt(100) + buyNum;
        if(moodExponent >= 50){
            return true;
        }
        return false;
    }

    public static class Printer {
        public static void print(Object printText){
            if(printText == null){
                return;
            }
            if(printText instanceof int[]){
                StringBuilder builder = new StringBuilder("[");
                int[] printTexts = (int[]) printText;
                if(printTexts.length > 0){
                    for(int text : printTexts){
                        builder.append(text).append(",");
                    }
                    builder.deleteCharAt(builder.toString().length()-1);
                    builder.append("]");
                }
                System.out.println(builder.toString());
                return;
            }
            System.out.println(printText);
        }
    }
}
