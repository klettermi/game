package com.example.game.domain.hangman.exception;

import com.example.game.global.enums.ErrorCode;
import com.example.game.global.exception.GlobalException;

public class GameOverException extends GlobalException {

    public GameOverException(ErrorCode errorCode) {
        super(errorCode);
    }
}
