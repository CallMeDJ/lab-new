package homework.rebiekong;

import homework.rebiekong.Session1.SessionWatermelon;
import homework.rebiekong.utils.ConsoleUtils;

/**
 * @author RebieKong
 */
public class MainSubject {

    public static final String MY_NAME="RebieKong";

    public static void main(String... args) {
        session1();
    }

    public static void session1() {
        int[] nums1 = {-1, 0, 5, 60};
        int[] sell = SessionWatermelon.sell(nums1);
        ConsoleUtils.printIntArray(sell);
    }
}
