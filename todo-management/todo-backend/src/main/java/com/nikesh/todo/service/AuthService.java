package com.nikesh.todo.service;

import com.nikesh.todo.dto.JwtAuthResponse;
import com.nikesh.todo.dto.LoginDto;
import com.nikesh.todo.dto.RegisterDto;

public interface AuthService {

    String register(RegisterDto registerDto);

    JwtAuthResponse login(LoginDto loginDto);
}
