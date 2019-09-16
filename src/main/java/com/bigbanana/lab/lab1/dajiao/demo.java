package com.bigbanana.lab.lab1.dajiao;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class demo extends com.bigbanana.lab.lab1.CalculateSum{


	@Override
	public  long sum(Integer... numbers){

		return Stream.of(
		Optional.ofNullable(numbers)
				.orElse(new Integer[0])
		).filter(Objects::nonNull)
				.map( x -> x.longValue())
				.reduce( (x1,x2)->x1+x2)
		.orElse(0L);


	}

	@Test
	public void myTest(){
		demo demo = new demo();
		super.test(demo);
	}


}
