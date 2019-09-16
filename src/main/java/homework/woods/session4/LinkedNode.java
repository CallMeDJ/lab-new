package homework.woods.session4;

/**
 *  @Author 大蕉
 *  链表节点，可以当单链表用也可以当双链表用
 */
public class LinkedNode {
	private char value;
	private LinkedNode next;
	private LinkedNode previous;

	public char getValue() {
		return value;
	}

	public void setValue(char value) {
		this.value = value;
	}

	public LinkedNode getNext() {
		return next;
	}

	public void setNext(LinkedNode next) {
		this.next = next;
	}

	public LinkedNode getPrevious() {
		return previous;
	}

	public void setPrevious(LinkedNode previous) {
		this.previous = previous;
	}
}
