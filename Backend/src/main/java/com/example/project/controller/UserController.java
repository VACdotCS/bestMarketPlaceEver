package com.example.project.controller;

import com.example.project.dto.request.ChangeCardUserRequest;
import com.example.project.dto.request.ChangeInfoUserRequest;
import com.example.project.dto.request.ChangePasswordRequest;
import com.example.project.exception.*;
import com.example.project.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
    private UserService Userservice;

    @PatchMapping("/password")
    public ResponseEntity<?> changePassword(@Valid
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        try {
            Userservice.changePassword(request, connectedUser);
            return new ResponseEntity<>("Пароль успешно изменен",HttpStatus.OK);
        } catch (UserIncorrectPasswordException | UserMismatchPasswordException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/card")
    public ResponseEntity<?> ChangeUserCard(@Valid
            @RequestBody ChangeCardUserRequest request,
            Principal connectedUser
    ) {
        try {
            Userservice.changeUserCard(request, connectedUser);
            return new ResponseEntity<>("Данные карты успешно сохранены", HttpStatus.OK);
        }catch (IncorrectDateException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInfoUser(@PathVariable Integer id,Principal connectedUser) {
        try {
            return new ResponseEntity<>(Userservice.getInfoUser(connectedUser), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> ChangeUserInfo(@Valid @RequestBody ChangeInfoUserRequest request,
            @PathVariable Integer id,
            Principal connectedUser
    ) {try
    {
        return new ResponseEntity<>(Userservice.changeUserInfo(request, connectedUser), HttpStatus.OK);
    }catch(UniqueEmailException | UniquePhoneException e)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}

    @GetMapping
    public ResponseEntity<?> getUserAll() {
        return new ResponseEntity<>(Userservice.getAllUsers(), HttpStatus.OK);
    }
}

