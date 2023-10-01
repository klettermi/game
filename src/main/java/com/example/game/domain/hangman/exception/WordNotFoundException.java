package com.example.game.domain.hangman.exception;

import com.example.game.global.enums.ErrorCode;
import com.example.game.global.exception.GlobalException;

public class WordNotFoundException extends GlobalException {
    public WordNotFoundException(ErrorCode errorCode){
        super(errorCode);
    }
}
