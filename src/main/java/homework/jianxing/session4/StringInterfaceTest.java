package homework.jianxing.session4;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class StringInterfaceTest extends TestCase {

    private String str;
    private ArrayString as;
    private LinkedString ls;

    @Before
    public void setUp() {
        str = "hello_string";
        as = new ArrayString(str);
        ls = new LinkedString(str);
    }

    @Test
    public void testLength() {
        assertEquals(str.length(), as.length());
        assertEquals(str.length(), ls.length());
    }

    @Test
    public void testCharAt() {
        int ch1 = 0;
        int ch2 = 5;
        int ch3 = str.length() - 1;
        assertEquals(str.charAt(ch1), as.charAt(ch1));
        assertEquals(str.charAt(ch1), ls.charAt(ch1));
        assertEquals(str.charAt(ch2), as.charAt(ch2));
        assertEquals(str.charAt(ch2), ls.charAt(ch2));
        assertEquals(str.charAt(ch3), as.charAt(ch3));
        assertEquals(str.charAt(ch3), ls.charAt(ch3));
    }

    @Test
    public void testIndexOf() {
        char[] c1 = {'l'};
        int i1 = str.indexOf('l');
        char[] c2 = {'x'};
        int i2 = str.indexOf('x');
        char[] c3 = {'s', 't', 'r'};
        int i3 = str.indexOf("str");
        char[] c4 = {'a', 'b', 'c'};
        int i4 = str.indexOf("abc");
        char[] c5 = {'i', 'n', 'g'};
        int i5 = str.indexOf("ing");

        assertEquals(i1, as.indexOf(c1));
        assertEquals(i1, ls.indexOf(c1));
        assertEquals(i2, as.indexOf(c2));
        assertEquals(i2, ls.indexOf(c2));
        assertEquals(i3, as.indexOf(c3));
        assertEquals(i3, ls.indexOf(c3));
        assertEquals(i4, as.indexOf(c4));
        assertEquals(i4, ls.indexOf(c4));
        assertEquals(i5, as.indexOf(c5));
        assertEquals(i5, ls.indexOf(c5));
    }

    @Test
    public void testSubstring() {
        String sub1 = str.substring(0, 3);
        String sub2 = str.substring(2, 5);
        String sub3 = str.substring(5, str.length());
        String sub4 = str.substring(0, str.length());

        assertEquals(sub1, as.subString(0, 3).toString());
        assertEquals(sub1, ls.subString(0, 3).toString());
        assertEquals(sub2, as.subString(2, 5).toString());
        assertEquals(sub2, ls.subString(2, 5).toString());
        assertEquals(sub3, as.subString(5, str.length()).toString());
        assertEquals(sub3, ls.subString(5, str.length()).toString());
        assertEquals(sub4, as.subString(0, str.length()).toString());
        assertEquals(sub4, ls.subString(0, str.length()).toString());
    }

    @Test
    public void testReverse() {
        String reverse = new StringBuilder(str).reverse().toString();
        assertEquals(reverse, as.reverse().toString());
        assertEquals(reverse, ls.reverse().toString());
    }

    @Test
    public void testValueOf() {
        String zero = Integer.toString(0);
        String min = Integer.toString(Integer.MIN_VALUE);
        String max = Integer.toString(Integer.MAX_VALUE);

        assertEquals(zero, ArrayString.valueOf(0).toString());
        assertEquals(min, ArrayString.valueOf(Integer.MIN_VALUE).toString());
        assertEquals(max, ArrayString.valueOf(Integer.MAX_VALUE).toString());
    }
}
