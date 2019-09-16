package homework.tonemy.session2;

public class StackWithLogImpl extends StackImpl {

	@Override
	public boolean push(int value) {
		// TODO Auto-generated method stub
		System.out.println("=====>The stack start push :" + value);
		boolean res = super.push(value);
		System.out.println("=====>The stack push : " + res);
		System.out.println("=====>The stack end push");
		System.out.println("===================>");
		return res;
	}

	@Override
	public int pop() {
		// TODO Auto-generated method stub
		System.out.println("=====>The stack start pop ");
		int res = super.pop();
		System.out.println("=====>The stack  pop : " + res);
		System.out.println("=====>The stack end pop ");
		System.out.println("===================>");
		return res;
	}

	@Override
	public int peak() {
		// TODO Auto-generated method stub
		System.out.println("=====>The stack start peak ");
		int res = super.peak();
		System.out.println("=====>The stack  peak :" + res);
		System.out.println("=====>The stack end peak ");
		System.out.println("===================>");
		return res;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		System.out.println("=====>The stack start getSize ");
		int res = super.size();
		System.out.println("=====>The stack getSize is :" + res);
		System.out.println("=====>The stack end getSize ");
		System.out.println("===================>");
		return res;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		System.out.println("=====>The stack start get IsEmpty ");
		boolean res = super.isEmpty();
		System.out.println("=====>The stack end get IsEmpty ");
		System.out.println("===================>");
		return res;
	}
	
}
