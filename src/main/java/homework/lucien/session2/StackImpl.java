package homework.lucien.session2;

public class StackImpl implements Stack {

	private static final int DEFAULT_CAPACITY_SIZE = 16;

	private int[] elements;
	private int size;
	private int index;

	public StackImpl() {
		this(DEFAULT_CAPACITY_SIZE);
	}

	public StackImpl(int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException("-----------capacity·Ç·¨------------");
		}

		elements = new int[capacity];
		size = 0;
		index = -1;
	}

	@Override
	public int push(int e) {
		if (index >= elements.length - 1) {
			throw new IllegalStateException("stack overflow");
		}

		elements[++index] = e;
		size++;

		return size;
	}

	@Override
	public int pop() {
		if (index < 0) {
			throw new IllegalStateException("stack underflow");
		}

		int e = elements[index--];
		size--;

		return e;
	}

	@Override
	public int peak() {
		return elements[index];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (int i = 0; i <= index; i++) {
			if (i == 0) {
				sb.append(elements[i]);
			} else {
				sb.append(", ").append(elements[i]);
			}
		}
		sb.append(']');
		return sb.toString();
	}
}