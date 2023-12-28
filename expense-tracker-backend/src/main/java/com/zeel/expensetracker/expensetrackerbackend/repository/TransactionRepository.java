package com.zeel.expensetracker.expensetrackerbackend.repository;

import com.zeel.expensetracker.expensetrackerbackend.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
