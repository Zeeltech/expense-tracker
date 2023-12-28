package com.zeel.expensetracker.expensetrackerbackend.auth;

import com.zeel.expensetracker.expensetrackerbackend.config.JwtService;
import com.zeel.expensetracker.expensetrackerbackend.models.Role;
import com.zeel.expensetracker.expensetrackerbackend.models.User;
import com.zeel.expensetracker.expensetrackerbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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

    public ResponseEntity<String> register(RegisterRequest request) {
        // need to check if user already exists or not
        // Built the user object
        if (userRepository.existsByEmail(request.getEmail())) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }
        var user =
                User.builder().firstName(request.getFirstName()).lastName(request.getLastName()).email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).role(Role.USER).build();

        // save it to repository
        repository.save(user);

        // generate token
        var jwtToken = jwtService.generateToken(user);

        // return that token
        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder().token(jwtToken).build();
        return new ResponseEntity<String>("User Registered successfully with token : " + authenticationResponse.getToken(), HttpStatus.OK);
    }
}
