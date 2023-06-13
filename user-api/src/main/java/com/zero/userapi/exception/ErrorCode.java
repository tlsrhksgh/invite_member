package com.zero.userapi.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    ALREADY_REGISTER_MEMBER(HttpStatus.BAD_REQUEST, "이미 존재하는 회원입니다.");

    private final HttpStatus httpStatus;
    private final String content;
}
