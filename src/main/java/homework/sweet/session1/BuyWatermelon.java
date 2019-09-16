package homework.sweet.session1;

/**
 * 一个卖西瓜的操作
 * @author sweet
 */
public class BuyWatermelon {
    public static void main(String[] args) {
        int[] testNum1 = {-2,0,2,30,51,100};
//        int[] testNum2 = {};
//        int[] testNum3 = new int[10];

        int[] resultArray1 = sell(testNum1);
        printIntArr(resultArray1);
    }

    public static int[] sell(int[] buyNum){
        int count = 0;
        //先判空再判断长度，否则会出现 NullPointException
        if (buyNum == null) return new int[0];
        if (buyNum.length == 0) return new int[0];

        //用一个新的数组来存放处理后的结果，不影响原来的数据
        int[] resultBuyNum = new int[buyNum.length];

        for (int i = 0; i < buyNum.length; i++) {
            int currentNum = buyNum[i];
            int tmpResult = 0;
            try {
               tmpResult = sell0(currentNum);
            } catch (Exception e) {
                tmpResult = 0;
                e.printStackTrace();
            }
            resultBuyNum[i] = tmpResult;
            count += tmpResult;
        }

        System.out.println("testNum1 一共卖出：" + count + " 个西瓜。");
        return  resultBuyNum;
    }

    public static int sell0(int tmp) throws Exception{
        if (tmp < 0){
            throw new Exception("数量不合格");
        }
        if (tmp > 50) {
            tmp = 50;
        }
        return tmp;
    }

    /**
     * 实现一个数组打印功能
     * @param arr
     */
    public static void printIntArr(int[] arr){

        System.out.print("[");
        if (arr == null || arr.length == 0)
            System.out.print("]");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1)
                System.out.print(",");
        }
        System.out.println("]");
    }
}
