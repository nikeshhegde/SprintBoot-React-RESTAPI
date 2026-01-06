package com.nikesh.todo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderImpl {

    static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println(passwordEncoder.encode("nikesh"));
        System.out.println(passwordEncoder.encode("admin"));
    }
}
