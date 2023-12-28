package com.zeel.expensetracker.expensetrackerbackend.controller;

import com.zeel.expensetracker.expensetrackerbackend.models.User;
import com.zeel.expensetracker.expensetrackerbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/authenticate")
    public ResponseEntity<User> authenticate() {
        return ResponseEntity.ok(service.authenticate());
    }
}
