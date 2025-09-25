package com.hendreoestevao.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsopportedMathOperationException extends RuntimeException {

    public UnsopportedMathOperationException(String message) {
        super(message);
    }
}
