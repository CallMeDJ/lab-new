package com.bigbanana.lab.lab1.rebiekong;

import org.junit.Test;

import java.util.Arrays;
import java.util.Objects;

public class demo extends com.bigbanana.lab.lab1.CalculateSum {


    @Override
    public long sum(Integer... numbers) {
        if (numbers == null) {
            return 0L;
        }
        return Arrays.stream(numbers)
                .filter(Objects::nonNull)
                .mapToLong(Long::valueOf)
                .reduce(0, (left, right) -> left + right);
    }

    @Test
    public void myTest() {
        demo demo = new demo();
        super.test(demo);
    }


}
