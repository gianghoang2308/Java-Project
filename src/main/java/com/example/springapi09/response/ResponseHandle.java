package com.example.springapi09.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandle {
    public static ResponseEntity<Object> responseBuilder(String message, Throwable throwable, HttpStatus httpStatus, Object responseObject){
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("httpStatus", httpStatus.value());
        response.put("error", throwable == null ? null : throwable.getMessage());
        response.put("data", responseObject);
        return new ResponseEntity<>(response, httpStatus);
    }
}