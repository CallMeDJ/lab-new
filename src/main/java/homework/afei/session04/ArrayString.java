package session004;

import java.util.Objects;

public class ArrayString implements StringInterface {
	
	char[] chAy;  //这里只是定义一个字符串数组。
	
	//传入String对象的构造函数。
	public ArrayString(String str) {
        Objects.requireNonNull(str);
        this.chAy = str.toCharArray();
    }
	
	//传入空对象的构造函数。
	public ArrayString() {
        this.chAy = "".toCharArray();//如果没有传String，就按传空处理。
    }
	
	
	//传入字符数组的构造函数，为了实现返回子字符串方法。
		public ArrayString(char[] chars) {
			this.chAy=chars;
	        this.chAy = "".toCharArray();//如果没有传String，就按传空处理。
	    }
	
	
	public static void main(String[] args) {
		ArrayString as=new ArrayString("afei") ;
		int len=as.length();
		char pos=as.charAt(3);
		char[] test001= {'a','f'};
		int existNum=as.indexOf(test001);//入参是一个字符数组，不是要给字符。
		
		
		
		System.out.println("字符串长度为："+len);
		System.out.println("地N个字符的值为：："+pos);
		System.out.println("在字符串的位置为：："+existNum);
		
				
	}
	
	
	
	@Override
	public int length() {
		// TODO Auto-generated method stub
		return this.chAy.length;
	}

	@Override
	public char charAt(int position) {
		// TODO Auto-generated method stub
		if ((position < 0) || (position >= this.chAy.length)) {
            throw new StringIndexOutOfBoundsException(position);
        }
        return this.chAy[position];
		
	}

	//这里需要注意，传入的是一个字符数组，不是一个字符。
	@Override
	public int indexOf(char[] target) {
		// TODO Auto-generated method stub
        Objects.requireNonNull(target);
        if (target.length == 0 || target.length > this.chAy.length) {
            return -1;
        }

        int max = this.chAy.length - target.length;
        for (int i = 0; i <= max; i++) {
            if (target[0] == this.chAy[i]) {
                for (int j = 1; j < target.length; j++) {
                    if (target[j] != this.chAy[i + j]) {
                        return -1;
                    }
                }
                return i;
            }
        }

        return -1;
	}

	@Override
	public StringInterface subString(int start, int end) {
		// TODO Auto-generated method stub
//		ArrayString si=new ArrayString(); --新建一个字符串构造函数即可！
		
		if(start<0 || end>this.chAy.length ||end<start) {
			try {
				throw new Exception("入参不合理！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		int subStrLen=end-start;
		char[] char1=new char[subStrLen];
		for(int i=0;i<subStrLen;i++) {
			char1[i]=this.chAy[start];
			start++;
		}
		
		return new ArrayString(char1);
	}

	
	/**
	 * 首尾翻转字符串，要求只能占用 O(1) 的额外空间
	 * @return
	 */
	@Override
	public StringInterface reverse() {
		// TODO Auto-generated method stub
		char[] char2=new char[this.length()];
		int index=0;
		for(int i=this.length();i>0;i--) {
			char2[index]=this.chAy[i];
			index++;
		}
		
		return new ArrayString(char2);
	}

}
