package homework.lucien.session2;

public class StackWithLogImpl extends StackImpl {

	public StackWithLogImpl() {
	}

	public StackWithLogImpl(int capacity) {
		super(capacity);
	}

	@Override
	public int push(int e) {
		WritelogBefore("push");
		int retsize = super.push(e);
		WritelogAfter("push");
		return retsize;
	}

	@Override
	public int pop() {
		WritelogBefore("pop");
		int retsize = super.pop();
		WritelogAfter("pop");
		return retsize;
	}

	@Override
	public int peak() {
		WritelogBefore("peak");
		int retsize = super.peak();
		WritelogAfter("peak");
		return retsize;
	}

	private void WritelogBefore(String operation) {
		System.out.println("Before " + operation + ": " + this.toString());
	}

	private void WritelogAfter(String operation) {
		System.out.println("After " + operation + ": " + this.toString());
	}
}
