package com.zeel.expensetracker.expensetrackerbackend.auth;

import com.zeel.expensetracker.expensetrackerbackend.config.JwtService;
import com.zeel.expensetracker.expensetrackerbackend.models.User;
import com.zeel.expensetracker.expensetrackerbackend.repository.UserRepository;
import com.zeel.expensetracker.expensetrackerbackend.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final UserService userService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        return service.register(registerRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest,
                                                        HttpServletResponse response) {

        if (userRepository.existsByEmail(loginRequest.getEmail()) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        var jwtToken = jwtService.generateToken(user);
        return new ResponseEntity<>(new AuthenticationResponse(jwtToken), HttpStatus.OK);
    }
}
