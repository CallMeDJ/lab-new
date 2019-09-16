package homework.tonemy.session1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.function.IntPredicate;

/*
 * 功能：
 * 1. 如果没顾客，那就不卖
 * 2. 来了N个顾客，要按顺序卖，不卖的客户也要告诉他不卖（卖0）
 * 3. 单个卖的逻辑在sell0实现
 * 4. 如果超过50个，只卖50个（每个顾客每次）
 * 5. 如果需求小于0个，sell0抛出异常，在sell捕获异常并登记为不卖（卖0）
 * 6. 在控制台打印出最终这批卖出去多少个，术语：xxx总共卖出去N个
 * 7. 实现自己的打印数组函数，格式：[1,2,3,4,5]
 *
 * @Author: Tonemy
 * @Date : 2019.7.14
 * 测试数据：
 *		-1 5 8 89 -4 5 91
 *		-1
 *		空
 */
public	class SessionWatermelon	{
	public	static void	main(String[]	args) throws IOException	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		String line = reader.readLine().trim();
		String[] data = line.split(" ");
		int len = data.length;
		if (line.length() <= 0) {
			len=0;
		}
		int[] buyNum = new int[len];
		for(int i = 0; i < buyNum.length; i ++) {
			buyNum[i] = Integer.parseInt(data[i]);
		}
		int[] res = sell(buyNum);
		System.out.println("售出记录:"+printArrays(res));
		reader.close();
		writer.close();
	}
	public	static	int[]	sell(int[]	buyNum){
		int sum = 0;
		if(buyNum.length <= 0) {
			System.out.println("共卖出去"+sum+"个");
			return new int[0];
		}
		int num[] = new int[buyNum.length];
		for(int i = 0; i < buyNum.length; i ++) {
			int s = 0;
			try {
				s = sell0(buyNum[i]);
			} catch (NegativeException e) {
				System.out.println(e);
			} finally {
				if(s > 50) {
					s = 50;
				}
				sum += s;
				num[i] = s;
			}
		}
		System.out.println("共卖出去"+sum+"个");
		return	num;
	}
	private static	int sell0(int	buyNum) throws NegativeException {
		if(buyNum < 0) {
			throw new NegativeException("卖出0!");
		}
		return	buyNum;
	}
	private static String printArrays(int[] arr) {
		if(arr.length <= 0) {
			return "[]";
		}
		String res = "";
		for(int a : arr) {
			res += a +",";
		}
		return "["+res.substring(0, res.length() - 1) +"]";
	}
}
class  NegativeException extends RuntimeException {
	/**
	 *  小于0 的异常
	 */
	private static final long serialVersionUID = 1L;
	public NegativeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}


 