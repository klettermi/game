package com.example.game.global.exception;

import com.example.game.global.enums.ErrorCode;

public class IllegalRequestInputFormat extends GlobalException{

    public IllegalRequestInputFormat(ErrorCode errorCode) {
        super(errorCode);
    }
}
