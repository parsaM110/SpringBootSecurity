package com.example.springbootsec31.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {

    String extractUserName(String token);

    String generateToken(UserDetails userDetails);
}
