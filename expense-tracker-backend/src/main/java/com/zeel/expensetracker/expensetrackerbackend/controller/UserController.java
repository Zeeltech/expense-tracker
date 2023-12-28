package com.zeel.expensetracker.expensetrackerbackend.controller;

import com.zeel.expensetracker.expensetrackerbackend.auth.payload.authentication.AuthenticationResponse;
import com.zeel.expensetracker.expensetrackerbackend.exception.UserServiceException;
import com.zeel.expensetracker.expensetrackerbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<AuthenticationResponse> authenticate() {
        try {
            return new ResponseEntity<>(new AuthenticationResponse(service.authenticate(), "User created " +
                    "successfully"), HttpStatus.OK);
        } catch (UserServiceException e) {
            return new ResponseEntity<>(new AuthenticationResponse(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
