package com.example.springapi09.controller;


import com.example.springapi09.dto.Login.LoginRequest;
import com.example.springapi09.response.ResponseHandle;
import com.example.springapi09.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthService authService;

    @PostMapping("/signin/user")
    public ResponseEntity<Object> signinUser(
            @Valid @RequestBody LoginRequest loginRequest
    ) {
        String token = authService.loginUser(loginRequest);
        return ResponseHandle.responseBuilder("Login successfully", null, HttpStatus.OK, token);
    }

    // REGISTER USER
    @PostMapping("/signup/user")
    public ResponseEntity<Object> signupUser(
            @Valid @RequestBody LoginRequest loginRequest
    ) {
        String result = authService.registerUser(loginRequest);

        return ResponseHandle.responseBuilder(
                result,
                null,
                HttpStatus.CREATED,
                null
        );
    }

    // REGISTER ADMIN
    @PostMapping("/signup/admin")
    public ResponseEntity<Object> signupAdmin(
            @Valid @RequestBody LoginRequest loginRequest
    ) {
        String result = authService.registerAdmin(loginRequest);

        return ResponseHandle.responseBuilder(
                result,
                null,
                HttpStatus.CREATED,
                null
        );
    }
}
