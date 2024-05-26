package com.example.project.demo;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo-controller")
@Hidden
public class DemoController {

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secured endpoint");
    }
    @GetMapping("/welcome")
    public String welcome(){
        return "Добро пожаловать ";
    }
    @GetMapping("/user")
    public String User(){
        return "User";
    }
    @GetMapping("/m")
    public String Manager(){
        return "Manager";
    }
    @GetMapping("/admin")
    public String Admin(){
        return "Admin";
    }
}