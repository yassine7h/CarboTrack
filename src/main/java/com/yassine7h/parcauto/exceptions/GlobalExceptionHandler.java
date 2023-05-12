package com.yassine7h.parcauto.exceptions;


import com.yassine7h.parcauto.dtos.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorMessage(HttpStatus.NOT_FOUND,ex.getMessage(),new Date()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ResourceExistException.class)
    public ResponseEntity<ErrorMessage> resourceExistExceptionHandler(ResourceExistException ex) {
        return new ResponseEntity<>(
                new ErrorMessage(HttpStatus.CONFLICT,ex.getMessage(),new Date()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(NotCompatibleException.class)
    public ResponseEntity<ErrorMessage> notCompatibleExceptionHandler(NotCompatibleException ex) {
        return new ResponseEntity<>(
                new ErrorMessage(HttpStatus.valueOf(400),ex.getMessage(),new Date()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> exceptionHandler(Exception ex) {
        return new ResponseEntity<>(
                new ErrorMessage(HttpStatus.BAD_REQUEST,ex.getMessage(),new Date()),
                HttpStatus.BAD_REQUEST
        );
    }
}
