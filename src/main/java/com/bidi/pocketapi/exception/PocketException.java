package com.bidi.pocketapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Setter
@Getter
public class PocketException extends Exception{
    private final String message;
    private final HttpStatus httpStatus;

}
