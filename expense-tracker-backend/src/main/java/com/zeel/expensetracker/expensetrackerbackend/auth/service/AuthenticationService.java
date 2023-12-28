package com.zeel.expensetracker.expensetrackerbackend.auth.service;

import com.zeel.expensetracker.expensetrackerbackend.auth.payload.authentication.AuthenticationResponse;
import com.zeel.expensetracker.expensetrackerbackend.auth.payload.authentication.LoginRequest;
import com.zeel.expensetracker.expensetrackerbackend.auth.payload.authentication.RegisterRequest;
import com.zeel.expensetracker.expensetrackerbackend.config.JwtService;
import com.zeel.expensetracker.expensetrackerbackend.exception.UserServiceException;
import com.zeel.expensetracker.expensetrackerbackend.models.Role;
import com.zeel.expensetracker.expensetrackerbackend.models.User;
import com.zeel.expensetracker.expensetrackerbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public String register(RegisterRequest request) {
        // need to check if user already exists or not
        // Built the user object
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserServiceException("User already exists");
        }
        var user =
                User.builder().firstName(request.getFirstName()).lastName(request.getLastName()).email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).role(Role.USER).build();

        // save it to repository
        repository.save(user);

        // generate token
        var jwtToken = jwtService.generateToken(user);

        // return that token
        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder().token(jwtToken).build();
        return authenticationResponse.getToken();
    }

    public String login(LoginRequest loginRequest) {
        if (userRepository.existsByEmail(loginRequest.getEmail()) == null) {
            throw new UserServiceException("User does not exists");
        }
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtService.generateToken(user);
    }
}
