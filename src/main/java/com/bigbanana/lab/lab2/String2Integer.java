package com.bigbanana.lab.lab2;

import org.junit.Assert;

public abstract class String2Integer {

	public abstract Integer string2Integer(String target);


	public void test(String2Integer impl){
		String test1 = "42   ";
		Assert.assertEquals(impl.string2Integer(test1).intValue(),42);


		String test2 = "  -11";
		Assert.assertEquals(impl.string2Integer(test2).intValue(),-11);


		String test3 = "12837MyNameIsBig";
		Assert.assertEquals(impl.string2Integer(test3).intValue(),12837);

		String test4 = "namesBig998";
		Assert.assertEquals(impl.string2Integer(test4).intValue(),998);

		Assert.assertEquals(impl.string2Integer(null).intValue(),0);

		String test5 = "999999999999";
		Assert.assertEquals(impl.string2Integer(test5).intValue(),Integer.MIN_VALUE);



	}

}
