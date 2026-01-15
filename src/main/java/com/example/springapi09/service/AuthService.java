package com.example.springapi09.service;

import com.example.springapi09.dto.Login.LoginRequest;

public interface AuthService {
    String loginUser(LoginRequest loginRequest);

    String registerUser(LoginRequest loginRequest);

    String registerAdmin(LoginRequest loginRequest);
}

