package com.zeel.expensetracker.expensetrackerbackend.auth.controller;

import com.zeel.expensetracker.expensetrackerbackend.auth.payload.authentication.AuthenticationResponse;
import com.zeel.expensetracker.expensetrackerbackend.auth.payload.authentication.LoginRequest;
import com.zeel.expensetracker.expensetrackerbackend.auth.payload.authentication.RegisterRequest;
import com.zeel.expensetracker.expensetrackerbackend.auth.service.AuthenticationService;
import com.zeel.expensetracker.expensetrackerbackend.exception.UserServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
        try {
            return new ResponseEntity<>(new AuthenticationResponse(null,
                    authenticationService.register(registerRequest)), HttpStatus.CREATED);
        } catch (UserServiceException e) {
            return new ResponseEntity<>(new AuthenticationResponse(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            return new ResponseEntity<>(new AuthenticationResponse(null, authenticationService.login(loginRequest)),
                    HttpStatus.OK);
        } catch (UserServiceException e) {
            return new ResponseEntity<>(new AuthenticationResponse(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
