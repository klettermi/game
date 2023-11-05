package com.example.game.global.util;

import com.example.game.global.exception.GlobalException;
import com.example.game.global.exception.IllegalRequestInputFormat;

import java.util.HashSet;

import static com.example.game.global.enums.ErrorCode.BASEBALL_GAME_NOT_FOUND;
import static com.example.game.global.enums.ErrorCode.NOT_REQUIRED_INPUT;

public class ParserUtils {
    public static int[] parserUserInput(String input, int size) throws IllegalArgumentException{
        if(input.length() != size) throw new IllegalArgumentException();
        String[] numList = input.split("");
        int[] parseInt = new int[size];
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < input.length(); i++){
            try{
                parseInt[i] = Integer.parseInt(numList[i]);
                if(set.isEmpty()) set.add(parseInt[i]);
                else if(set.contains(parseInt[i])) throw new IllegalRequestInputFormat(NOT_REQUIRED_INPUT);
            }catch (IllegalArgumentException e){
                throw new IllegalRequestInputFormat(NOT_REQUIRED_INPUT);
            }
        }
        return parseInt;
    }
}
