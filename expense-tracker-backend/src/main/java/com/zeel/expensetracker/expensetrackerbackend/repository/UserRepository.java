package com.zeel.expensetracker.expensetrackerbackend.repository;

import com.zeel.expensetracker.expensetrackerbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User,Integer> {
    public Optional<User> findByEmail(String email);
    public Boolean existsByEmail(String email);
}
