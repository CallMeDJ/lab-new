package com.bigbanana.lab.lab1.seven;

import org.junit.Test;

public class demo extends com.bigbanana.lab.lab1.CalculateSum{

    @Override
    public long sum(Integer... numbers){
        long sum = 0;

        if (numbers != null && numbers.length > 0) {
            for (Integer number : numbers) {
                if (number != null) {
                    sum += number;
                }
            }
        }

        return sum;
    }

    @Test
    public void myTest(){
        demo demo = new demo();
        super.test(demo);
    }
}
