package com.bidi.pocketapi;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class ApiException extends java.lang.Exception {

    private final String message;
    private final HttpStatus httpStatus;
}
