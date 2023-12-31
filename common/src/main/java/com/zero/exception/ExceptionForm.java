package com.zero.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionForm {
    private String message;
    private int errorCode;
}
