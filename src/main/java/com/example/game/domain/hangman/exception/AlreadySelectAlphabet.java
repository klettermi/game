package com.example.game.domain.hangman.exception;

import com.example.game.global.enums.ErrorCode;
import com.example.game.global.exception.GlobalException;

public class AlreadySelectAlphabet extends GlobalException {
    public AlreadySelectAlphabet(ErrorCode errorCode) {
        super(errorCode);
    }
}
