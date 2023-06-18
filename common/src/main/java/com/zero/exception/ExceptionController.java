package com.zero.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<ExceptionForm> requestException(final MemberException exception) {
        log.warn("user api exception: {}", exception.getErrorCode());
        return ResponseEntity.badRequest().body(
                new ExceptionForm(exception.getMessage(), exception.getErrorCode().getHttpStatus().value())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionForm> methodArgumentNotValid(final MethodArgumentNotValidException ex) {
        log.warn("signup form field exception: {}", ex.getStatusCode());
        for(FieldError error : ex.getBindingResult().getFieldErrors()) {
            return ResponseEntity.badRequest().body(
                    new ExceptionForm(error.getDefaultMessage(), HttpStatus.BAD_REQUEST.value())
            );
        }

        return null;
    }
}
