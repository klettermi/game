package com.example.game.baseball;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class baseballTest {
    @DisplayName("랜덤 숫자 n만큼 추출")
    @Test
    public void settingGame(){
        // given
        int n = 3;
        ArrayList<Integer> numArray = new ArrayList<>();

        // when
        while(numArray.size() < n){
            int num = (int) (Math.random() * 9 + 1);
            numArray.add(num);
        }

        // then
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i != j)  assertThat((numArray.get(i) != numArray.get(j))).isTrue();
            }
        }
    }

    @DisplayName("사용자 입력 받기 성공")
    @Test
    public void parserUserInputNumsSuccess(){
        // given
        String input = "123";
        int size = 3;

        // when
        if(input.length() != size) throw new IllegalArgumentException();
        String[] numList = input.split("");
        int[] parseInt = new int[size];

        for(int i = 0; i < size; i++){
            if(Integer.valueOf(numList[i]) == null) throw new IllegalArgumentException();
            parseInt[i] = Integer.valueOf(numList[i]);
        }

        // then
        assertThat(parseInt.length).isEqualTo(3);
    }

    @DisplayName("사용자 입력 받기 실패")
    @Test
    public void parserUserInputNumsFail(){
        // given
        String input = "12a";
        int size = 3;

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            if(input.length() != size) throw new IllegalArgumentException();
            String[] numList = input.split("");
            int[] parseInt = new int[size];

            for(int i = 0; i < size; i++){
                if(Integer.valueOf(numList[i]) == null) throw new IllegalArgumentException();
                parseInt[i] = Integer.valueOf(numList[i]);
            }
        });
    }
}
