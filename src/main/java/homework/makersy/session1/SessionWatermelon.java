package homework.makersy.session1;

/**
  Created by makersy on 2019
 */
/**
逻辑：

1、如果没顾客，那就不卖

2、来了N个顾客，要按顺序卖，不卖的客户也要告诉他不卖（卖0）。

3、单个卖的逻辑在  sell0 实现。

4、如果超过50个，只卖50个。

5、如果需求小于0个，sell0 抛出异常，在 sell 捕获异常并登记为不卖（卖0）。

5、在控制台打印出最终这批卖出去多少个。术语：xxx总共卖出去N个。

6、实现自己的打印数组函数。格式: [1,2,3,4,5];
 */
public class SessionWatermelon {
    public static void main(String[] args) {
        int[] buyNum = {1, -1, 3, 50, 12};
        print(sell(buyNum));
    }

    public static int[] sell(int[] buyNum){
        if (buyNum == null || buyNum.length <= 0) {
            return new int[0];
        }

        int[] sellNum = new int[buyNum.length];
        int totalSell = 0;

        for (int i = 0; i < buyNum.length; i++) {
            int realSell;
            try {
                realSell = sell0(buyNum[i]);
            } catch (Exception e) {
                realSell = 0;
            }
            sellNum[i] = realSell;
            totalSell += realSell;
        }
        System.out.println("makersy 共卖出 " + totalSell + " 个");
        return sellNum;
    }

    private static int sell0(int buyNum) throws Exception {
        if (buyNum < 0) {
            throw new Exception("购买数量应大于0");
        }
        if (buyNum > 50) {
            return 50;
        }
        return buyNum;
    }

    private static void print(int[] arr) {
        if ( arr == null || arr.length == 0 ) {
            System.out.println("[]");
        } else {
            System.out.print("[" + arr[0]);
            for (int i = 1; i < arr.length; i++) {
                System.out.print("," + arr[i]);
            }
            System.out.println("]");
        }
    }
}
