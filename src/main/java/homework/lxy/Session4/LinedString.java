package homework.lxy.Session4;


import homework.lxy.utils.StringUtils;

/**
 * @Author 大蕉
 * 这里你自己实现的时候，要去掉 abstract
 */
public class LinedString implements StringInterface {

	private LinkedNode head;

	private int length;

	LinedString(){
		head = new LinkedNode();
	}

	LinedString(char[] chars){
		if(chars == null || chars.length == 0){
			return;
		}
		length = chars.length;
		head = new LinkedNode();
		LinkedNode lastNode = head;
		this.setValue(chars,lastNode);
	}
	LinedString(LinkedNode head, int length){
		this.head = head;
		this.length = length;
	}

	/**
	 * 根据数字返回对应的字符串
	 * @param number
	 * @return
	 */
	public static StringInterface valueOf(Integer number) throws NumberFormatException{
		if(number != null){
			int n = number;
			int length = 0;
			boolean isNegative = (n<0);
			int[] intValue = new int[8];
			do{
				intValue[length++] = n%10;
			}while ((n = n /10) > 0);

			char[] chars = new char[isNegative ? length+1 : length];
			for(int i = 0; i<length; i++){
				chars[i] = (char)('0'+intValue[i]);
			}
			if(isNegative){
				chars[length-1] = '-';
			}
			StringUtils.reverseChar(chars);
			return new LinedString(chars);
		}
		return new ArrayString();
	}

	@Override
	public int length() {
		return length;
	}

	@Override
	public char charAt(int position) {
		if(position < 0 && position > length){
			throw new StringIndexOutOfBoundsException(position);
		}
		LinkedNode targetNode = head;
		do{
			targetNode = targetNode.getNext();
			position--;
		}while (position >= 0 && targetNode != null);

		return targetNode == null ? '0' : targetNode.getValue();
	}

	@Override
	public int indexOf(char[] target) {
		if(target == null || target.length == 0 || target.length > length){
			return -1;
		}
		char[] srcChars = this.toCharArray();
		int targetLength = target.length;
		int srcLength = length;
		int[] next = getNextArray(target);
		int i = 0, j = 0;
		while ( i < srcLength && j < targetLength  ) {
			if( j == -1 || srcChars[i] == target[j]) {
				i ++;
				j ++;
			}else {
				j = next[j];
			}
		}
		if (j == targetLength){
			return i - j;
		}
		return -1;
	}

	@Override
	public StringInterface subString(int start, int end) {
		if(start > length
				|| end > length
				|| start > end){
			throw new IllegalArgumentException();
		}
		if(start < 0){
			start = 0;
		}
		int index = -1;
		int times = end-start;
		char[] chars = new char[times];
		LinkedNode currentNode = head;
		while (currentNode != null) {
			currentNode = currentNode.getNext();
			index++;
			if(index >= start && index < end){
				chars[index-start] = currentNode.getValue();
			}
		}
		return new LinedString(chars);
	}

	@Override
	public StringInterface reverse() {
		if(length <= 1){
			return new LinedString(head,length);
		}
		LinkedNode pre = head;
		LinkedNode cur = head.getNext();
		LinkedNode tmp;
		while (cur != null) {
			tmp = cur.getNext();
			cur.setNext(pre);
			cur.setPrevious(tmp);
			pre.setPrevious(cur);
			pre = cur;
			cur = tmp;
		}
		//构造新head
		LinkedNode newHead = new LinkedNode();
		newHead.setNext(pre);
		pre.setPrevious(newHead);
		//删除原有head
		LinkedNode lastNode = head.getPrevious();
		lastNode.setNext(null);
		return new LinedString(newHead,length);
	}

	@Override
	public String toString(){
		return String.valueOf(this.toCharArray());
	}

	private void setValue(char[] chars,LinkedNode head){
		LinkedNode lastNode = head;
		for(int i = 0; i<length; i++){
			LinkedNode currentNode = new LinkedNode();
			currentNode.setValue(chars[i]);
			lastNode.setNext(currentNode);
			currentNode.setPrevious(lastNode);
			lastNode = currentNode;
		}
	}

	private char[] toCharArray(){
		char[] chars = new char[length];
		LinkedNode currentNode = head.getNext();
		for(int i = 0; i<length; i++){
			chars[i] = currentNode.getValue();
			currentNode = currentNode.getNext();
		}
		return chars;
	}

	private int[] getNextArray(char[] target){
		int[] next = new int[target.length];
		next[0] = -1;
		int i = 0;
		int j = -1;
		while (i < target.length-1) {
			if(j == -1 || target[i] == target[j]){
				i++;
				j++;
				next[i] = j;
			}else {
				j = next[j];
			}
		}
		return next;
	}

	public static void main(String[] args) {
		StringInterface stringInterface  = valueOf(4321321);
		stringInterface = stringInterface.reverse();
		System.out.println(stringInterface.toString());
		StringInterface newStringInterface = stringInterface.subString(-1,3);
		System.out.println(newStringInterface);

		System.out.println(stringInterface.length());

		System.out.println(stringInterface.indexOf(new char[]{'2','3','4'}));
		System.out.println(newStringInterface.indexOf(new char[]{'2','3','4'}));
	}
}
