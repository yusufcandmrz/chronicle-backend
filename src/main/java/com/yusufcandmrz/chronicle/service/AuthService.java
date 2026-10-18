package com.yusufcandmrz.chronicle.service;

import com.yusufcandmrz.chronicle.dto.LoginRequest;
import com.yusufcandmrz.chronicle.dto.LoginResponse;
import com.yusufcandmrz.chronicle.dto.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}
