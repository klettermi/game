package com.example.game.domain.baseball.dto;

import com.example.game.global.enums.GameEnum;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BaseballResponseDto {
    private final GameEnum win;
    private final int strike;
    private final int ball;
    private final int count;
}
