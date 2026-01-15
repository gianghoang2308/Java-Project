package com.example.springapi09.exception;

import org.springframework.http.HttpStatus;

public class ExceptionHandle {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;

    public ExceptionHandle(String message, Throwable throwable, HttpStatus httpStatus){
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
    }

    public String getMessage(){
        return message;
    }

    public Throwable getThrowable(){
        return throwable;
    }

    public HttpStatus geHttpStatus(){
        return httpStatus;
    }
}
