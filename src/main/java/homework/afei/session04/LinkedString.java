package session004;

import java.util.Objects;

public class LinkedString implements StringInterface {

	LinkedNode head;
	int length; //链表的长度。
	
	//这个构造方法是什么意思,看不懂呀！
//	public LinkedString() {
//        this(null, 0);
//    }
//
//    public LinkedString(String str) {
//        this(Objects.requireNonNull(str).toCharArray());
//    }

    public LinkedString(char[] chars) {
        Objects.requireNonNull(chars);
        this.length = chars.length;
        if (chars.length == 0) {
            this.head = null;
        } else {
            this.head = new LinkedNode(chars[0]);
            LinkedNode prev = this.head;
            for (int i = 1; i < chars.length; i++) {
                LinkedNode next = new LinkedNode(chars[i]);
                next.setPrevious(prev);
                prev.setNext(next);
                prev = next;
            }
        }
    }
    
    LinkedString(LinkedNode head, int length) {
        this.head = head;
        this.length = length;
    }
	
	
	
	@Override
	public int length() {
		// TODO Auto-generated method stub
		return length;
	}

	
    private LinkedNode indexOf(int position) {
    	if(position<=0) {
    		try {
				throw  new Exception("索引值不合理！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
        int pos = 0;
        for (LinkedNode node = head; node != null; node = node.getNext()) {
            if (position == pos++) {
                return node;
            }
        }
        try {
			throw new Exception("没找到数据。");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
	
	
	@Override
	public char charAt(int position) {
		// TODO Auto-generated method stub
		if (position < 0 || position >= length) {
            throw new StringIndexOutOfBoundsException(position);
        }

        LinkedNode node = indexOf(position);
        return node.getValue();
	}

	@Override
	public int indexOf(char[] target) {
		// TODO Auto-generated method stub
		Objects.requireNonNull(target);
        if (target.length == 0 || target.length > length) {
            return -1;
        }
        int max = length - target.length;
        int index = 0;
        for (LinkedNode node = head; index <= max && node != null; node = node.getNext()) {
            if (target[0] == node.getValue()) {
                LinkedNode tmp = node.getNext();
                for (int j = 1; j < target.length; j++) {
                    if (target[j] == tmp.getValue()) {
                        tmp = tmp.getNext();
                    } else {
                        return -1;
                    }
                }
                return index;
            }
            index++;
        }

        return -1;
	}

	@Override
	public StringInterface subString(int start, int end) {
		// TODO Auto-generated method stub
		 if (start < 0) {
	            throw new StringIndexOutOfBoundsException(start);
	        }
	        if (end > length) {
	            throw new StringIndexOutOfBoundsException(end);
	        }
	        int subLen = end - start;
	        if (subLen < 0) {
	            throw new StringIndexOutOfBoundsException(subLen);
	        }

	        char[] chars = new char[subLen];
	        int count = 0;
	        for (LinkedNode node = indexOf(start); node != null && count < subLen; node = node.getNext()) {
	            chars[count++] = node.getValue();
	        }
	        return new LinkedString(chars);
	}

	@Override
	public StringInterface reverse() {
		// TODO Auto-generated method stub
		LinkedNode tail = indexOf(length - 1);
        LinkedNode head = new LinkedNode(tail.getValue());
        LinkedNode prev = head;
        for (LinkedNode node = tail.getPrevious(); node != null; node = node.getPrevious()) {
            LinkedNode next = new LinkedNode(node.getValue());
            prev.setNext(next);
            next.setPrevious(prev);
            prev = next;
        }
        return new LinkedString(head, length);
	}

}
