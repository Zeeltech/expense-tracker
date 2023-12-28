package com.zeel.expensetracker.expensetrackerbackend.service;

import com.zeel.expensetracker.expensetrackerbackend.auth.payload.cashbook.CashBookRequest;
import com.zeel.expensetracker.expensetrackerbackend.exception.CashBookServiceException;
import com.zeel.expensetracker.expensetrackerbackend.exception.UserServiceException;
import com.zeel.expensetracker.expensetrackerbackend.models.CashBook;
import com.zeel.expensetracker.expensetrackerbackend.models.User;
import com.zeel.expensetracker.expensetrackerbackend.repository.CashBookRepository;
import com.zeel.expensetracker.expensetrackerbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CashBookService {
    private final UserRepository userRepository;
    private final CashBookRepository cashBookRepository;

    public CashBook createCashBook(CashBookRequest cashBookRequest) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (userRepository.existsByEmail(email) == null) {
            throw new UserServiceException("User does not exists");
        }

        User user = userRepository.findByEmail(email).orElseThrow();

        if (cashBookRepository.existsByNameAndUser(cashBookRequest.getName(), user)) {
            throw new CashBookServiceException("Cashbook with given name already exists");
        }

        CashBook cashBook = CashBook.builder().name(cashBookRequest.getName()).user(user).build();
        user.getCashBooks().add(cashBook);

        userRepository.save(user);
        return cashBook;
    }

    public List<CashBook> getAllCashBook() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        if (userRepository.existsByEmail(email) == null) {
            throw new UserServiceException("User does not exists");
        }

        User user = userRepository.findByEmail(email).orElseThrow();

        return cashBookRepository.findAllByUser(user).orElseThrow();
    }

    public CashBook getCashBook(CashBookRequest cashBookRequest) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        if (userRepository.existsByEmail(email) == null) {
            throw new UserServiceException("User does not exists");
        }

        User user = userRepository.findByEmail(email).orElseThrow();

        if (!cashBookRepository.existsByNameAndUser(cashBookRequest.getName(), user)) {
            throw new CashBookServiceException("Cashbook with given name does not exists");
        }

        return cashBookRepository.findByNameAndUser(cashBookRequest.getName(), user).orElseThrow();
    }
}
