package com.bigbanana.lab.utils;

import java.util.List;

/**
 * Created by callmedj on 17/4/13.
 */
public class Printer<V> {
    public static void println(Object object){
        System.out.println(object);
    }
    public static void print(Object object){
        System.out.print(object);
    }

    public  void printList(List<V> objectList){
        for(V object : objectList){
            println(object);
        }
    }

    public static void printArray(Object[][] target,int maxLength){
        if(target == null || target.length==0){
            return;
        }
        int row = target.length;
        int column = target[0].length;
        for(int i = 0 ; i < row;i++){
            for(int j=0;j<column;j++){
                String current = target[i][j]+"";
                int needToAddZero = maxLength - current.length();
                for(int k = 0 ; k < needToAddZero ; k++){
                    print(0);
                }
                print(current+" ");
            }
            println("");
        }
    }


    public static void printArray(int[] array){
        if(array == null){
            return;
        }
        if(array.length==0){
            return;
        }

        print("[");

        for(int i = 0 ; i < array.length ; i ++){
            print(array[i]);
            if(i != array.length - 1){
                print(",");
            }

        }

        print("]");

    }


}
