package homework.lucien.session1;

public class SessionWatermelon {
	//---------------------小男孩卖西瓜---------------------------
	/**   逻辑：
		1、如果没顾客，那就不卖
		2、来了N个顾客，要按顺序卖，不卖的客户也要告诉他不卖（卖0）。
		3、单个卖的逻辑在 sell0 实现。
		4、如果超过50个，只卖50个。
		5、如果需求小于0个，sell0 抛出异常，在 sell 捕获异常并登记为不卖（卖0）。
		5、在控制台打印出最终这批卖出去多少个。术语：xxx总共卖出去N个。
		6、实现自己的打印数组函数。格式: [1,2,3,4,5];
	 * */
	public static void main(String[] args) {
		int[] num = {-2,1,50,51};
		int[] sell = sell(num);
		printArray(sell);
	}
	
	public static int[] sell(int[] buyNum) {
		//判断数组为空或null
		if(null==buyNum||buyNum.length==0) {
			return new int[0];
		}
		//创建一个新数组存放输出的数组结果
		int[] result = new int[buyNum.length];
		int totalBuynum = 0;
		
		for(int i=0;i<buyNum.length;i++) {
			int currentBuynum = buyNum[i];
			int sellnum = 0;
			try {
				sellnum = sell0(currentBuynum);
			} catch (Exception e) {
				sellnum = 0;
			}
			result[i] = sellnum;
			totalBuynum+=sellnum;
			
		}
		System.out.println("lucien总共卖出"+totalBuynum+"个西瓜");
		
		return result;
	}
	
	private static int sell0(int buynum) throws Exception{
		if(buynum<0) {
			throw new Exception("没西瓜卖了~~~~~~~~~~~~");
		}
		if(buynum>50) {
			return 50;
		}
		return buynum;
	}
	//实现数组打印功能
	private static void printArray(int[] arr) {
		if(null==arr||arr.length==0) {
			System.out.println("[]");
			return;	
		}
		StringBuilder strb = new StringBuilder().append('[');
		for(int i=0;i<arr.length;i++) {
			if(i==0) {
				strb.append(arr[i]);
			}else {
				strb.append(',').append(arr[i]);
			}
		}
		strb.append(']');
		System.out.println(strb.toString());
		
	}
	

}
