package com.zeel.expensetracker.expensetrackerbackend.exception;

public class UserServiceException extends RuntimeException {
    public UserServiceException(String message) {
        super(message);
    }
}
