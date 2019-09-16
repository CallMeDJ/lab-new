package homework.seven.session1;

/**
 * 卖西瓜的小男孩儿
 *
 * 逻辑：
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
 * @author Seven-Steven
 * @date 2019-07-15 00:18
 */
public class SessionWatermelon {
  /**
   * 每次交易最大数额
   */
  private static int MAX_SELL_PER = 50;

  public static void main(String[] args) {
    // 测试卖瓜程序
    int[] buyNum = {1, -1, 70, 0, 2};
    int[] result = sell(buyNum);
    printArray(result);
    // 测试空指针
    result = sell(null);
    printArray(result);

    // 测试数组打印方法
    printArray(null);
  }

  /**
   * 批量售卖逻辑
   * @param buyNum 每位客户要购买的西瓜数量
   * @return 卖给每位客户的西瓜数量
   */
  private static int[] sell(int[] buyNum) {
    if (null == buyNum || buyNum.length == 0) {
      return new int[0];
    }
    
    int buyerNumber = buyNum.length;
    
    // 售卖计划
    int[] sellPlan = new int[buyerNumber];
    int totalSell = 0;

    int current;
    int sellNumber;
    for (int i = 0; i < buyerNumber; i++) {
      current = buyNum[i];
      try {
        sellNumber = sell0(current);
      } catch (IllegalArgumentException e) {
        sellNumber = 0;
      }

      sellPlan[i] = sellNumber;
      totalSell += sellNumber;
    }

    // 输出售卖结果
    System.out.println("小柒总共卖出" + totalSell + "个");

    return sellPlan;
  }

  /**
   * 单次售卖逻辑
   * @param buyNum 客户购买数量
   * @return 卖家售卖数量
   * @throws IllegalArgumentException 交易数量不合法异常
   */
  private static int sell0(int buyNum) throws IllegalArgumentException {
    if (buyNum > MAX_SELL_PER) {
      return MAX_SELL_PER;
    }

    if (buyNum < 0) {
      throw new IllegalArgumentException("交易数量不合法:" + buyNum);
    }

    return buyNum;
  }

  /**
   * 打印数组
   * @param array 整型数组
   */
  private static void printArray(int[] array) {
    if (null == array) {
      System.out.println("[]");
      return;
    }

    StringBuilder result = new StringBuilder().append('[');
    for (Object o : array) {
      result.append(o).append(',');
    }

    if (result.length() > 1) {
      result.deleteCharAt(result.length() - 1);
    }

    result.append(']');
    System.out.println(result);
  }
}
