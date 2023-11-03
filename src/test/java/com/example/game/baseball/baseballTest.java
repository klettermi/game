package com.example.game.baseball;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static net.bytebuddy.matcher.ElementMatchers.is;

public class baseballTest {
    @DisplayName("랜덤 숫자 n만큼 추출")
    @Test
    public void settingGame(){
        // given
        int n = 3;
        ArrayList<Integer> numArray = new ArrayList<>();
        while(numArray.size() < n){
            int num = (int) (Math.random() * 9 + 1);
            numArray.add(num);
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i != j)  Assertions.assertThat((numArray.get(i) != numArray.get(j))).isTrue();
            }
        }
    }
}
