package com.example.springbootsec31.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String accessToken;
    private String refreshToken;
}
