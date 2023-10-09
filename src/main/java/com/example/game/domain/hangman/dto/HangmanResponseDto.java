package com.example.game.domain.hangman.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HangmanResponseDto {
    private final boolean win;
    private final boolean correct;
    private final String currentWord;
    private final int count;
}
