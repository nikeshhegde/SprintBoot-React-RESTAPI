package com.nikesh.todo.controller;

import com.nikesh.todo.dto.JwtAuthResponse;
import com.nikesh.todo.dto.LoginDto;
import com.nikesh.todo.dto.RegisterDto;
import com.nikesh.todo.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    // Build register REST API

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){

        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    //Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        JwtAuthResponse jwtAuthResponse = authService.login(loginDto);
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }
}
