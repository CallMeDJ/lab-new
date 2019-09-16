package com.bigbanana.lab.lab1.alyenc;

import org.junit.Test;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @description 哈哈哈哈哈哈嗝
 * @package com.bigbanana.lab.lab1.alyenc
 * @author alyenc
 * @email alyenc@outlook.com
 * @date 2018/7/6 17:14
 * @version v1.0.0
 */
public class demo extends com.bigbanana.lab.lab1.CalculateSum{


	@Override
	public  long sum(Integer... numbers){
		return Arrays.stream(Optional.ofNullable(numbers)
				.orElse(new Integer[0]))
				.filter(Objects::nonNull)
				.mapToLong(Integer::longValue)
				.sum();
	}

	@Test
	public void myTest(){
		demo demo = new demo();
		super.test(demo);
	}
}
