package com.zeel.expensetracker.expensetrackerbackend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
// To expose SecurityFilterChain Bean
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    // To add filter to any requests
    private final JwtAuthenticationFilter jwtAuthFilter;

    // To provide authentication provider
    private final AuthenticationProvider authenticationProvider;

    // Bean responsible for configuring all http security
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Cross-Site Request Forgery
                .csrf(AbstractHttpConfigurer::disable)
                // To apply authorization rules on httprequests
                .authorizeHttpRequests(req ->
                        // all requests matching below uri is permitted
                        req.requestMatchers("/api/v1/auth/**").permitAll()
                                // any other type of requests
                                .anyRequest()
                                // need to be authenticated
                                .authenticated())
                // stateless session
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // authenticationProvider will authenticate
                .authenticationProvider(authenticationProvider)
                // adding custom filter before Authenticated Filter
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        // Now applied on all httpRequests
        return http.build();
    }
}
