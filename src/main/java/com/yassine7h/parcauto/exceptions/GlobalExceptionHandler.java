package com.yassine7h.parcauto.exceptions;


import com.yassine7h.parcauto.dtos.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundExceptionHandler(Exception ex) {
        return new ResponseEntity<>(
                new ErrorMessage(ex.getMessage(),new Date()),
                HttpStatus.valueOf(400)
        );
    }
}
