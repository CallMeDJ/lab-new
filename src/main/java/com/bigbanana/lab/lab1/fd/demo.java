package com.bigbanana.lab.lab1.fd;

import com.bigbanana.lab.lab1.CalculateSum;
import org.junit.Test;

public class demo extends com.bigbanana.lab.lab1.CalculateSum {
    @Override
    public long sum(Integer... numbers) {
        long allsum = 0;
        if (numbers == null)
            return allsum;
        for (Integer i : numbers) {
            if (i == null)
                continue;
            allsum = i + allsum;
        }
        return allsum;
    }

    @Test
    public void Mytest() {
        demo demo = new demo();
        super.test(demo);
    }
}
