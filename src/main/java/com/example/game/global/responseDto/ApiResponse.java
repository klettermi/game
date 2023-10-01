package com.example.game.global.responseDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    // 성공 유무
    private final boolean success;

    // HTTP 응답 코드
    private final int statusCode;

    // 요약 메시지
    private final String msg;

    // 요청에서 요구한 데이터
    private final T data;

    // 에러 메시지
    private final T errors;

    public ApiResponse(boolean success, int statusCode, String msg, T data, T errors) {
        this.success = success;
        this.statusCode = statusCode;
        this.msg = msg;
        this.data = data;
        this.errors = errors;
    }
}
