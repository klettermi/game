package com.example.game.global.exception;

import com.example.game.global.responseDto.ApiResponse;
import com.example.game.global.util.ResponseUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ApiResponse<?> handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage());
        return ResponseUtils.error(e.getMessage());
    }

    @ExceptionHandler(GlobalException.class)
    public ApiResponse<?> handleGlobalException(GlobalException e) {
        log.error(e.getErrorCode().getDetail());
        return ResponseUtils.error(e.getErrorCode());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiResponse<?> missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
        log.error(e.getMessage());
        return ResponseUtils.error(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<?> validationExceptionHandler(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        Map<String, String> errors = new LinkedHashMap<>();
        e.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(
                        error.getField(), error.getDefaultMessage()
                ));
        return ResponseUtils.error(HttpStatus.BAD_REQUEST, errors);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ApiResponse<?> handleJsonProcessingException(JsonProcessingException e) {
        log.error(e.getMessage());
        return ResponseUtils.error(e.getMessage());
    }
}
