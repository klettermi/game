package com.example.game.domain.baseball.exception;

import com.example.game.global.enums.ErrorCode;
import com.example.game.global.exception.GlobalException;

public class BaseballGameNotFoundException extends GlobalException {
    public BaseballGameNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
