package com.example.springbootsec31.dto;

import lombok.Data;

@Data
public class SignInRequest {
    private String email;
    private String password;
}
