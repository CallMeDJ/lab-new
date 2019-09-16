package homework.zhou.session1;

/*
    逻辑：

    1、如果没顾客，那就不卖

    2、来了N个顾客，要按顺序卖，不卖的客户也要告诉他不卖（卖0）。

    3、单个卖的逻辑在 sell0 实现。

    4、如果超过50个，只卖50个。

    5、如果需求小于0个，sell0 抛出异常，在 sell 捕获异常并登记为不卖（卖0）。

    5、在控制台打印出最终这批卖出去多少个。术语：xxx总共卖出去N个。

    6、实现自己的打印数组函数。格式: [1,2,3,4,5];
 */

public class demo {
    public static void main(String[] args) {

        int[] nums1 = {-1,0,5,60};
        int[] sell = sell(nums1);
        print(sell);

    }

    public static int[] sell(int[] buyNum){

        int sum = 0;
        if(buyNum == null)
            return new int[0];
        if (buyNum.length == 0)
            return new int[0];
        for (int i=0; i<buyNum.length; i++){
            int temp = buyNum[i];
            try {
                temp = sell0(temp);
            } catch (Exception e) {
                e.printStackTrace();
                temp = 0;
            }
            buyNum[i] = temp;
            sum += temp;
        }
        System.out.println("总共卖出去" + sum + "个");
        return buyNum;


    }

    private static int sell0(int buyNum) throws Exception {
        if (buyNum < 0){
            throw new Exception("不卖了~");
        }

        if (buyNum > 50){
            return 50;
        }

        return buyNum;
    }

    private static void print(int[] arr){
        StringBuffer sb = new StringBuffer().append('[');
        for (int i = 0; i < arr.length; i++ ){
            if (i == 0 ){
                sb.append(arr[i]);
            }
            else{
                sb.append(',').append(arr[i]);
            }
        }
        sb.append(']');
        System.out.println(sb.toString());
    }
}
