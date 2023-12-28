package com.zeel.expensetracker.expensetrackerbackend.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // For using jwtServices
    private final JwtService jwtService;

    // Inbuilt service for UserDetails
    // But we have overridden it in ApplicationConfig
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        System.out.println("In the doFilterInternal");
        final String authHeader = request.getHeader("Authorization");
        System.out.println("authHeader is " + authHeader);
        // Exit conditions
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("under authHeader is " + authHeader);
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt;

        // Removed Bearer from ahead of token
        jwt = authHeader.substring(7);

        final String userEmail;

        // Extract from JWT Token
        userEmail = jwtService.extractUsername(jwt);
        System.out.println("Extracted email " + userEmail);
        // if extracted username is not null
        // SecurityContextHolder: holds the current security context, including authentication information.
        // also checks if its authentication information is not there in SecurityContextHolder
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println("Useremail not null and not authenticated");
            // To get UserDetails from username we have inbuilt service
            // loadUserByUsername fetches user from database [ which is doable by repository but makes abstract code ]
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            System.out.println("UserDetails via loadUserByUsername is " + userDetails);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                // Represents authenticated user in Spring Security
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                        null, // password
                        userDetails.getAuthorities());
                // To attach extra information to authToken like IP Address, Session ID etc
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // Now need to set it in SecurityContextHolder, so that it is available throughout application
                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println("Set the authToken in JwtAuthenticationFilter");
            }
        }
        filterChain.doFilter(request, response);
    }
}
