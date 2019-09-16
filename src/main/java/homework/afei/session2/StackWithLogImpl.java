package homework.afei.session2;

import java.util.ArrayList;
import java.util.List;


public class StackWithLogImpl extends StackImpl {
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		System.out.println("start isEmpty");
		System.out.println("end isEmpty");
		return super.isEmpty();
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		System.out.println("start size");
		System.out.println("end size");
		return super.size();
	}
	
	@Override
	public int peak() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("start peak");
		if(stackTop<1) {
			throw new Exception("ջ�գ�peak�쳣��");
		}else {
			System.out.println("end peak");
			return stactInt[stackTop-1];
		}
	}
	
	@Override
	public int pop() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("start pop");
		if(stackTop<1) {
			throw new Exception("ջ�գ�peak�쳣��");
		}else {
			System.out.println("end pop");
			return stactInt[--stackTop];
		}
	}
	
	@Override
	public boolean push(int value) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("start push");
		boolean result=false;
		// TODO Auto-generated method stub
		if(stackTop<stactInt.length) {
			stactInt[stackTop++]=value;
			System.out.println("push--->"+value+"�ɹ�");
			result=true;
		}else {
			throw new Exception("ջ���ˣ�pushʧ�ܣ�");
		}
		
		System.out.println("end push");
		
		if(stackTop>=stactInt.length) {//��ջ���ˣ�������������
			System.out.println("start increase stack");
			List<Integer> listNum=new ArrayList<Integer>();
			for(int i=0;i<stactInt.length;i++) {
				listNum.add(stactInt[i]);
			}
			stactInt=new int[stactInt.length*2];
			for(int i=0;i<listNum.size();i++) {
				stactInt[i]=(int)listNum.get(i);
			}
			System.out.println("end increase stack");
		}
		return result;
	}
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StackImpl stackImplWLog=new StackWithLogImpl();
		for(int i=0;i<10;i++) {
			stackImplWLog.push(i);
		}
		System.err.println("��ǰ����Ĵ�СΪ��"+stackImplWLog.stactInt.length);
		System.err.println("��ǰջ�Ĵ�СΪ��"+stackImplWLog.stackTop);
		int stackTopNum=stackImplWLog.stackTop;
		for(int i=0;i<stackTopNum;i++) {
			System.err.println("��ǰջ��Ԫ��Ϊ��"+stackImplWLog.peak());
			System.err.println("����ǰջ��Ԫ�أ�"+stackImplWLog.pop());
			System.out.println("��ǰջʣ��Ԫ�ظ���Ϊ��"+stackImplWLog.stackTop);//���Ԫ�ص�ֵ����һֱ�仯��ע�⣡
		}
	}

}
