package homework.woods.session4;

import homework.woods.utils.KMPMatcher;
import homework.woods.utils.WoodsPrinter;

import java.util.Arrays;

/**
 * @author: woods
 * @date: 2019/8/10
 * @description:
 */
public class ArrayString implements StringInterface {
    private char[] value;

    public ArrayString(){
        this.value = "".toCharArray();
    }

    public ArrayString(char[] arr)
    {
        if (arr == null || arr.length == 0)
        {
            value = "".toCharArray();
            return;
        }
        this.value = arr;
    }

    public ArrayString(Character[] arr)
    {
        if (arr == null || arr.length == 0)
        {
            value = "".toCharArray();
            return;
        }
        this.value = new char[arr.length];
        for (int n = 0; n < arr.length; n ++)
        {
            value[n] = arr[n].charValue();
        }
    }
    public ArrayString(int[] arr)
    {
        if (arr == null || arr.length == 0)
        {
            value = "".toCharArray();
            return;
        }
        this.value = new char[arr.length];
        for (int n = 0; n < arr.length; n ++)
        {
            value[n] = (char) arr[n];
        }
    }

    public ArrayString(String string)
    {
        this.value = string.toCharArray();
    }

    /**
     * 根据数字返回对应的字符串
     * @param number
     * @return
     */
    public static StringInterface valueOf(Integer number) throws NumberFormatException{

        char[] numberArray;
        // 取绝对值
        int positiveNumber = Math.abs(number);
        // 是否正整数
        boolean positive = false;
        if (positiveNumber == number)
        {
            positive = true;
//            numberArray = new char[Integer.SIZE/8];
            numberArray = new char[16];
        }else {
            numberArray = new char[17];
//            numberArray = new char[Integer.SIZE/8 + 1];
        }
        int index = numberArray.length - 1;

        while (positiveNumber >= 10)
        {
            numberArray[index--] = (char)((positiveNumber%10) + '0');
            positiveNumber = positiveNumber/10;
        }
        numberArray[index] = (char)(positiveNumber + '0');

        if (!positive)
        {
            numberArray[--index] = '-';
        }

        char[] result = Arrays.copyOfRange(numberArray, index, numberArray.length);

        return new ArrayString(result);
    }

    @Override
    public int length() {
        return value.length;
    }

    @Override
    public char charAt(int position) {
        if ((position < 0) || (position >= value.length)) {
            throw new StringIndexOutOfBoundsException(position);
        }
        return value[position];
    }

    @Override
    public int indexOf(char[] target) {

        if (target.length > value.length)
        {
            return -1;
        }

        return KMPMatcher.match(this, target);
    }

    @Override
    public StringInterface subString(int start, int end) {
        if (start > value.length || end > value.length || start > end)
        {
            throw new StringIndexOutOfBoundsException(start);
        }

        char[] dest = Arrays.copyOfRange(value, start, end);
        return new ArrayString(dest);
    }

    @Override
    public StringInterface reverse() {
        int n = value.length;
        for (int i=0; i < n/2; i ++)
        {
            char mid = value[i];
            value[i] = value[n-i-1];
            value[n-i-1] = mid;
        }

        return new ArrayString(value);
    }

    @Override
    public String toString(){
        return new String(value);
    }


    public static void main(String[] args) {

        StringInterface as = ArrayString.valueOf(1234511111);
        for (int i=0; i < as.length(); i ++)
        {
            WoodsPrinter.print(as.charAt(i));
        }

//        StringInterface as = new ArrayString("abcdefg");
//        as = as.reverse();
//        for (int i =0; i < as.length(); i ++)
//        {
//            WoodsPrinter.print(as.charAt(i));
//        }
    }
}
