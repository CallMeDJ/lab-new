package homework.afei.session2;

import java.util.ArrayList;
import java.util.List;


public class StackImpl implements Stack {
	protected  int stackTop=0;
	protected int[] stactInt=new int[6];
	
	@Override//ջ�Ĵ�С
	public int size() {
		// TODO Auto-generated method stub
		
		return stackTop;
	}
	
	
	@Override//�ж�ջ�Ƿ�Ϊ��
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(stackTop==0) {
			return true;
		}else {
			return false;
		}
		
	}
	
	@Override//����ջ��Ԫ��
	public int peak() throws Exception {
		// TODO Auto-generated method stub
		if(stackTop<1) {
			throw new Exception("ջ�գ�peak�쳣��");
		}else {
			return stactInt[stackTop-1];
		}
		
	}
	

	@Override//����ջ��Ԫ��ֵ
	public int pop() throws Exception {
		if(stackTop<1) {
			throw new Exception("ջ�գ�peak�쳣��");
		}else {
			return stactInt[--stackTop];
		}
	}
	
	@Override//��ջ�����Ԫ��
	public boolean push(int value) throws Exception {
		boolean result=false;
		// TODO Auto-generated method stub
		if(stackTop<stactInt.length) {
			stactInt[stackTop++]=value;
			System.out.println("push--->"+value+"�ɹ�");
			result=true;
		}else {
			throw new Exception("ջ���ˣ�pushʧ�ܣ�");
		}
		
		if(stackTop>=stactInt.length) {//��ջ���ˣ�������������
			List<Integer> listNum=new ArrayList<Integer>();
			for(int i=0;i<stactInt.length;i++) {
				listNum.add(stactInt[i]);
			}
			stactInt=new int[stactInt.length*2];
			for(int i=0;i<listNum.size();i++) {
				stactInt[i]=(int)listNum.get(i);
			}
		}
		
		return result;
		
	}
	

	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StackImpl stackImpl=new StackImpl();
		for(int i=0;i<10;i++) {
			stackImpl.push(i);
		}
		System.err.println("��ǰ����Ĵ�СΪ��"+stackImpl.stactInt.length);
		System.err.println("��ǰջ�Ĵ�СΪ��"+stackImpl.stackTop);
		int stackTopNum=stackImpl.stackTop;
		for(int i=0;i<stackTopNum;i++) {
			System.err.println("��ǰջ��Ԫ��Ϊ��"+stackImpl.peak());
			System.err.println("����ǰջ��Ԫ�أ�"+stackImpl.pop());
			System.out.println("��ǰջʣ��Ԫ�ظ���Ϊ��"+stackImpl.stackTop);//���Ԫ�ص�ֵ����һֱ�仯��ע�⣡
		}
//		System.err.println("��ǰջ��Ԫ��Ϊ��"+stackImpl.isEmpty());
//		System.err.println("��ǰջ��Ԫ��Ϊ��"+stackImpl.size());
//		System.err.println("��ǰջ��Ԫ��Ϊ��"+stackImpl.peak());
//		System.err.println("����ǰջ��Ԫ�أ�"+stackImpl.pop());
//		System.out.println("�쳣֮�󣬴��뻹�᲻�����ִ�У�");
		
	}

}
