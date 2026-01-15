package com.example.springapi09.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    public ResponseEntity<Object> handlerNotFoundException(NotFoundException notFoundException) {
        ExceptionHandle exceptionHandle = new ExceptionHandle(notFoundException.getMessage(), notFoundException.getCause(), HttpStatus.NOT_FOUND);

        return new ResponseEntity<Object>(exceptionHandle, HttpStatus.NOT_FOUND);
    }

}
