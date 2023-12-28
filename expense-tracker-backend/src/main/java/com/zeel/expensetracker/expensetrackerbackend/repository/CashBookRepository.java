package com.zeel.expensetracker.expensetrackerbackend.repository;

import com.zeel.expensetracker.expensetrackerbackend.models.CashBook;
import com.zeel.expensetracker.expensetrackerbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CashBookRepository extends JpaRepository<CashBook, Integer> {
    public Optional<CashBook> findByName(String name);

    public Boolean existsByName(String name);

    public Boolean existsByNameAndUser(String name, User user);

    public Optional<CashBook> findByNameAndUser(String name, User user);

    public Optional<List<CashBook>> findAllByUser(User user);

}
