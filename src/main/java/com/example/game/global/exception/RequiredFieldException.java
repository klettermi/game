package com.example.game.global.exception;

import com.example.game.global.enums.ErrorCode;

public class RequiredFieldException extends GlobalException{
    public RequiredFieldException(ErrorCode errorCode) {
        super(errorCode);
    }
}
