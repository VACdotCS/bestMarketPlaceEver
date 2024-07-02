package com.example.project.service;


import com.example.project.dto.request.RegisterRequest;
import com.example.project.config.JwtService;
import com.example.project.dto.request.AuthenticationRequest;
import com.example.project.dto.response.AuthenticationResponse;
import com.example.project.entity.Role;
import com.example.project.entity.User;
import com.example.project.exception.InvalidCredentialsException;
import com.example.project.exception.UniqueEmailException;
import com.example.project.exception.UniquePhoneException;
import com.example.project.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.project.common.Constants;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepo repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) throws UniqueEmailException, UniquePhoneException {
        if (repository.existsByEmail(request.getEmail())) {
            throw new UniqueEmailException(Constants.UNIQUE_EMAIL);
        }
        if (repository.existsByPhone(request.getPhone())) {
            throw new UniquePhoneException(Constants.UNIQUE_PHONE);
        }
        var user = User.builder()
                .username(request.getUsername())
                .phone(request.getPhone())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .Token(jwtToken)
                .role(user.getRole())
                .user_id(user.getUser_id())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws InvalidCredentialsException{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getPhone(),
                            request.getPassword()
                    )
            );
        var user = repository.findByPhone(request.getPhone()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .Token(jwtToken)
                .role(user.getRole())
                .user_id(user.getUser_id())
                .build();
        } catch (Exception e) {
            throw new InvalidCredentialsException(Constants.INVALID_LOGIN_OR_PASSWORD);
        }
    }
}