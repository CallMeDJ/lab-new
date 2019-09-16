package homework.rebiekong.Session1;

import homework.rebiekong.MainSubject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author RebieKong
 */
public class SessionWatermelon {

    private static Logger logger = LoggerFactory.getLogger(SessionWatermelon.class);
    private static final int MAX_NUM_PER_CUSTOMER = 50;
    private static final int MIN_NUM_PER_CUSTOMER = 0;


    public static int[] sell(int[] buyNum) {
        if (buyNum == null) {
            return new int[0];
        }

        int[] res = Arrays.stream(buyNum).map(num -> {
            try {
                return sell0(num);
            } catch (Exception e) {
                logger.error(e.getMessage());
                return MIN_NUM_PER_CUSTOMER;
            }
        }).toArray();

        System.out.println(String.format("%s总共卖出去%d个。", MainSubject.MY_NAME, Arrays.stream(res).sum()));
        return res;
    }

    private static int sell0(int buyNum) throws Exception {
        if (buyNum < MIN_NUM_PER_CUSTOMER) {
            throw new Exception(String.format("客户声明购买%d个西瓜，低于计划值%d", buyNum, MIN_NUM_PER_CUSTOMER));
        }
        if (buyNum > MAX_NUM_PER_CUSTOMER) {
            logger.warn("客户声明购买{}个西瓜，超过计划值{}", buyNum, MAX_NUM_PER_CUSTOMER);
            return MAX_NUM_PER_CUSTOMER;
        }
        return buyNum;
    }


}
