package homework.tonemy.session5;


import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Created by tonemy on 2019/8/18.
 */
public class Main {
    public static void main(String[] args) {
        Task test_0= new Task();
        test_0.TestHashMap();

        Task test_1 = new Task();
        test_1.TestBitMap();

        Task test_2 = new Task();
        test_2.TestBloomFilter();
    }

    static class Task {

        /**
         * HashMap的测试
         */
        public void TestHashMap() {
            HashMap<String, String> map = new HashMap<>();
            map.put("1", "123");
            map.put("2", "1234");
            System.out.println(map.get("2"));
            map.put("2", "12");//测试冲突
            System.out.println(map.get("2"));
            //测试是否包含键值key
            System.out.println(map.containsKey("2"));
            System.out.println(map.containsKey("0"));
            map.remove("2");
            System.out.println(map.containsKey("2"));
        }

        /**
         * BitMap的测试
         */
        public void TestBitMap() {
            BitMap<Integer> bitMap = new BitMap(1000000000);
            int[] random = RandomCommon(0, 10000000, 10);
            System.out.println("产生不同的随机数: "+Arrays.toString(random));
            for(int i = 0; i < random.length; i ++) {
                bitMap.add(random[i]);
            }
            System.out.println(bitMap.size());
            //检测去重效果
            for(int i = 0; i < random.length; i ++) {
                bitMap.add(random[i]);
            }
            System.out.println(bitMap.size());
        }

        /**
         * BloomFilter的测试
         */
        public void TestBloomFilter() {
            BloomFilter<String> bloomFilter = new BloomFilter<>();
            String[] str = {"xxy", "xyx", "sdf", "sdd", "dfs"};
            for (int i = 0; i < 5; i ++) {
                bloomFilter.add(str[i]);
            }
            System.out.println(bloomFilter.size());
            //测试去重
            for (int i = 0; i < 5; i ++) {
                bloomFilter.add(str[i]);
            }
            System.out.println(bloomFilter.size());
        }

        /**
         * 产生n个不同的随机数
         * @param min 范围最小值
         * @param max 范围最大值
         * @param n 随机数的数量
         */
        public int[] RandomCommon(int min, int max, int n){
            if (n > (max - min + 1) || max < min) {
                return null;
            }
            int[] result = new int[n];
            int count = 0;
            while(count < n) {
                int num = (int) (Math.random() * (max - min)) + min;
                boolean flag = true;
                for (int j = 0; j < n; j++) {
                    if(num == result[j]){
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    result[count] = num;
                    count++;
                }
            }
            return result;
        }

    }

}
