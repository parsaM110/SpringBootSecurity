package com.example.springbootsec31.service;

import com.example.springbootsec31.dto.JwtAuthenticationResponse;
import com.example.springbootsec31.dto.SignInRequest;
import com.example.springbootsec31.dto.SignUpRequest;
import com.example.springbootsec31.entity.User;

public interface AuthenticationService {

    User signup(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signin(SignInRequest signInRequest);
}
