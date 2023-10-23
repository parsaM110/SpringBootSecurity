package com.example.springbootsec31.service.impl;

import com.example.springbootsec31.dto.SignUpRequest;
import com.example.springbootsec31.entity.Role;
import com.example.springbootsec31.entity.User;
import com.example.springbootsec31.repository.UserRepository;
import com.example.springbootsec31.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User signup(SignUpRequest signUpRequest){
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        return  userRepository.save(user);

    }

}
