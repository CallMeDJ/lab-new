package homework.joeyzhao.session1;

import java.util.Arrays;
//import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
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
 * 5、在控制台打印出最终这批卖出去多少个。术语：xxx总共卖出去N个。
 *
 * 6、实现自己的打印数组函数。格式: [1,2,3,4,5];
 */
public class SessionWatermelon {


    public static void main(String[] args) {
        int[][] customers_batches={
                {},
                {0,51,-3,20,10,10},
                {-1,52,0,20}
        };
        for (int[] batch : customers_batches){
            int[] sales=SessionWatermelon.sell(batch);
            SessionWatermelon.printSales(sales);
        }
    }

    public static int[] sell(int[] buyNum){
        int[] sales = new int[buyNum.length];
        int total=0;
        int sale;
        for (int i=0;i<buyNum.length;i++) {
            try{
                sale=SessionWatermelon.sell0(buyNum[i]);
            }catch (Exception e){
                sale=0;
            }
            sales[i]=sale;
            total+=sale;
        }
        System.out.println(String.format("JZ总共卖出去%d个。",total));
        return sales;
    }

    private static int sell0 (int buyNum) throws Exception{
        if(buyNum>50){
            return 50;
        }else if(buyNum<0){
            throw new Exception("购买个数为负数");
        }else{
            return buyNum;
        }
    }

    private static void printSales(int[] nums){

        //https://stackoverflow.com/questions/63150/whats-the-best-way-to-build-a-string-of-delimited-items-in-java
//        StringJoiner joiner=new StringJoiner(",");
//        for (int n:nums)joiner.add(Integer.toString(n));
//        String result=joiner.toString();

        //https://stackoverflow.com/questions/38425623/java-join-array-of-primitives-with-separator
        String result = Arrays.stream(nums)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));

        System.out.println("["+result+"]");
    }
}