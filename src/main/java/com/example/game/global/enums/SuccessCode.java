package com.example.game.global.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.OK;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {
    // HANGMAN
    /* 200 OK : 요청이 성공적으로 완료되었다는 의미입니다. */
    INIT_WORDS_SETTING_SUCCESS(OK, "랜덤한 3개의 단어가 정해졌습니다."),
    CLEAR_GAME(OK, "선택한 단어가 세팅되었습니다."),
    GET_WORD_SUCCESS(OK, "선택한 단어입니다."),

    /* 201 CREATED : 요청이 성공적이었으며 그 결과로 새로운 리소스가 생성 되었다는 의미입니다. */
    CORRECT_ALPHABET(OK, "알맞은 알파벳이 있습니다."),
    INCORRECT_ALPHABET(OK, "알맞은 알파벳이 없습니다."),
    WIN(OK, "게임을 이기셨습니다."),
    RESTART_SUCCESS(OK, "게임이 재실행 되었습니다.");

    private final HttpStatus httpStatus;
    private final String detail;

}
