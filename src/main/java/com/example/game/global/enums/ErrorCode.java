package com.example.game.global.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    /* 400 BAD_REQUEST : 이 응답은 잘못된 문법으로 인해 서버가 요청을 이해할 수 없다는 의미입니다. */
    WORD_NOT_FOUND(BAD_REQUEST, "저장되지 않은 단어입니다."),
    BASEBALL_GAME_NOT_FOUND(BAD_REQUEST, "저장되지 않은 게임입니다."),
    NOT_REQUIRED_INPUT(BAD_REQUEST, "잘못된 입력입니다."),
    USER_NOT_FOUND(BAD_REQUEST, "유저를 찾을 수 없습니다."),
    /* 401 UNAUTHORIZED : 인증되지 않았다는 의미입니다. */

    /* 403 FORBIDDEN : 클라이언트가 콘텐츠에 접근할 권리를 가지고 있지 않다는 의미입니다.*/
    GAME_OVER(FORBIDDEN, "게임 오버"),
    ALREADY_SELECT_ALPHABET(FORBIDDEN, "이미 선택한 알파벳입니다.");
    private final HttpStatus httpStatus;
    private final String detail;
}
