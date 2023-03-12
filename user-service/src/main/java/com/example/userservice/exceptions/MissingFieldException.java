package com.example.userservice.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MissingFieldException extends Exception {
    public MissingFieldException(String message) {
        super(message);
    }
}
