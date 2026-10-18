package com.yusufcandmrz.chronicle.controller;

import com.yusufcandmrz.chronicle.dto.LoginRequest;
import com.yusufcandmrz.chronicle.dto.LoginResponse;
import com.yusufcandmrz.chronicle.dto.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface AuthController {

    ResponseEntity<Void> register(RegisterRequest request);

    ResponseEntity<LoginResponse> login(LoginRequest request);
}
