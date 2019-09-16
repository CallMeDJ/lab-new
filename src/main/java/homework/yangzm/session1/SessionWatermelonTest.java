package homework.yangzm.session1;

import org.junit.Test;

public class SessionWatermelonTest {

    private int[] customer = {-1, 0, 5, 60};
    private int watermelonStore = 50;
    private int maxSell = 50;
    private int minSell = 0;

    @Test
    public void show() {
        int[] result = sell(customer);
        // 实现自己的打印数组函数。格式: [1,2,3,4,5]
        printArry(result);
    }

    public int[] sell(int[] buyNum) {
        // 如果没顾客，那就不卖
        if (buyNum == null || buyNum.length < 1) {
            return new int[0];
        }
        int count = 0;
        int[] sellResult = new int[buyNum.length];
        // 来了N个顾客，要按顺序卖，不卖的客户也要告诉他不卖（卖0）
        for(int i=0; i<buyNum.length; i++) {
            int num = buyNum[i];
            int subSell;
            try {
                subSell = sell0(num);
            } catch (Exception e) {
                subSell = 0;
            }
            sellResult[i] = subSell;
            count = count + subSell;
        }
        // 在控制台打印出最终这批卖出去多少个。术语：xxx总共卖出去N个。
        System.out.println("志明总共卖出去" + count + "个");
        return sellResult;
    }

    private int sell0(int buyNum) throws Exception {
        //如果需求小于0个，sell0 抛出异常，在 sell 捕获异常并登记为不卖（卖0）。
        if (buyNum < minSell || watermelonStore<=minSell) {
            throw new Exception("需求不能小于0");
        }
        if(buyNum <= watermelonStore) {
            //如果超过50个，只卖50个
            if (buyNum > maxSell) {
                buyNum = maxSell;
            }
            watermelonStore = watermelonStore - buyNum;
            return buyNum;
        }else {
            int sell = watermelonStore;
            watermelonStore = 0;
            return sell;
        }
    }

    public void printArry(int[] arry) {
        if (arry == null || arry.length < 1) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int count = arry.length;
        for (int subcount = 0; subcount < count; subcount++) {
            int content = arry[subcount];
            if (subcount == count - 1) {
                sb.append(content);
            } else {
                sb.append(content).append(",");
            }
        }
        sb.append("]");
        System.out.print(sb.toString());
    }
}
