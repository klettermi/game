package com.example.game.global.util;

import java.util.ArrayList;

public class RandomUtils {
    public static int[] setRandomNumber(int size){
        ArrayList<Integer> numArray = new ArrayList<>();
        while(numArray.size() < size){
            int num = (int) (Math.random() * 9 + 1);
            numArray.add(num);
        }

        int[] nums = numArray.stream().mapToInt(i -> i).toArray();
        return nums;
    }
}
