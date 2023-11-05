package com.example.game.global.util;

import java.util.ArrayList;
import java.util.HashSet;

public class RandomUtils {
    public static int[] setRandomNumber(int size){
        ArrayList<Integer> numArray = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        while(numArray.size() < size){
            int num = (int) (Math.random() * 9 + 1);
            if(!set.contains(num)) {
                numArray.add(num);
                set.add(num);
            }
        }

        return numArray.stream().mapToInt(i -> i).toArray();
    }
}
