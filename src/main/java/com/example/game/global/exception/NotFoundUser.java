package com.example.game.global.exception;

import com.example.game.global.enums.ErrorCode;

public class NotFoundUser extends GlobalException{

    public NotFoundUser(ErrorCode errorCode) {
        super(errorCode);
    }
}
