package com.zeel.expensetracker.expensetrackerbackend.auth.payload.authentication;

import com.zeel.expensetracker.expensetrackerbackend.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private User user;
    private String token;
}
