package com.arch.tests.mvc.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionEnum {

    EMPTY_EMAIL_PARAMETER(HttpStatus.BAD_REQUEST, "E-mail must not be empty.");

    private final HttpStatus httpStatus;
    private final String message;
}
