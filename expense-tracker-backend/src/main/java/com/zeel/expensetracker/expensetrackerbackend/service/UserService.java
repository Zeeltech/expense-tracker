package com.zeel.expensetracker.expensetrackerbackend.service;

import com.zeel.expensetracker.expensetrackerbackend.config.JwtService;
import com.zeel.expensetracker.expensetrackerbackend.exception.UserServiceException;
import com.zeel.expensetracker.expensetrackerbackend.models.User;
import com.zeel.expensetracker.expensetrackerbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository repository;
    private final JwtService jwtService;

    public User authenticate() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        if (repository.existsByEmail(email) == null) {
            throw new UserServiceException("User does not exists");
        }
        return repository.findByEmail(email).orElseThrow();
    }
}
