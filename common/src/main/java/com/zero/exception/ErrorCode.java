package com.zero.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    ALREADY_REGISTER_MEMBER(HttpStatus.BAD_REQUEST, "이미 존재하는 회원입니다."),
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "가입되지 않은 회원입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "패스워드가 일치하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String content;
}
