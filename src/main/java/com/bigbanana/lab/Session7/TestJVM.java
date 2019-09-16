package com.bigbanana.lab.Session7;

import org.junit.Test;

import java.util.Random;

public class TestJVM {


	@Test
	public void testGC(){

		JVM jvm = new JVM(20,10,10);

		Random random = new Random();

		int objectSize = 100;
		for(int i = 0 ; i < objectSize ; i++){
			jvm.removeObject(getNewName(random.nextInt(objectSize)));
			jvm.addObject(getNewName(i), new Object());
		}


		for(int i = 0 ; i < objectSize ; i++){
			jvm.removeObject(getPermName(random.nextInt(objectSize)));
			jvm.addPermObject(getPermName(i), new Object());
		}


	}


	private static String getNewName(int i){
		return "NEW_"+i;
	}

	private static String getPermName(int i){
		return "PERM_"+i;
	}
}
