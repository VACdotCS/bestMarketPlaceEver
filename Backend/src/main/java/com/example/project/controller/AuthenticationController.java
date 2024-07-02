package com.example.project.controller;

import com.example.project.dto.request.AuthenticationRequest;
import com.example.project.service.AuthenticationService;
import com.example.project.dto.request.RegisterRequest;
import com.example.project.exception.InvalidCredentialsException;
import com.example.project.exception.UniqueEmailException;
import com.example.project.exception.UniquePhoneException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid
                                      @RequestBody RegisterRequest request
    ) {
        try {
            return new ResponseEntity<>(service.register(request), HttpStatus.OK);
        } catch (UniqueEmailException | UniquePhoneException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> authenticate(@Valid
            @RequestBody AuthenticationRequest request
    ) {
        try {
            return new ResponseEntity<>(service.authenticate(request), HttpStatus.OK);
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}