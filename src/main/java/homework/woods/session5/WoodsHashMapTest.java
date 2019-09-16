package homework.woods.session5;

import homework.woods.utils.WoodsPrinter;

/**
 * @author: woods
 * @date: 2019/8/25
 * @description:
 */
public class WoodsHashMapTest {

    public static void main(String[] args) {
        WoodsHashMap testHashMap = new WoodsHashMap<String, String>();
        testHashMap.put("woods", "woods");
        testHashMap.put("woods1", "woods1");
        // 刻意制造hash碰撞，使得 hash("woods2") == hash("woods1")
        testHashMap.put("woods2", "woods2");
        WoodsPrinter.println(testHashMap.size);

        WoodsHashMap.Entry<String, String> entry2 = testHashMap.getEntry("woods2");
        WoodsPrinter.println("woods2 key = " + entry2.key);
        WoodsPrinter.println("woods2 value = " + entry2.value);
        WoodsPrinter.println("woods2 hash = " + entry2.hash);

        // 单链效果
        WoodsHashMap.Entry<String, String> entry1 = entry2.next;
        WoodsPrinter.println("woods1 key = " + entry1.key);
        WoodsPrinter.println("woods1 value = " + entry1.value);
        WoodsPrinter.println("woods1 hash = " + entry1.hash);

    }
}
