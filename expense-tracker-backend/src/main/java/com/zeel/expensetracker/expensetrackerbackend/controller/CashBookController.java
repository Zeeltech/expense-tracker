package com.zeel.expensetracker.expensetrackerbackend.controller;

import com.zeel.expensetracker.expensetrackerbackend.auth.payload.cashbook.CashBookRequest;
import com.zeel.expensetracker.expensetrackerbackend.auth.payload.cashbook.CashBookResponse;
import com.zeel.expensetracker.expensetrackerbackend.auth.payload.cashbook.ListCashBookResponse;
import com.zeel.expensetracker.expensetrackerbackend.exception.CashBookServiceException;
import com.zeel.expensetracker.expensetrackerbackend.service.CashBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/api/v1/user/cashbook")
@RequiredArgsConstructor
public class CashBookController {
    private final CashBookService cashBookService;

    @PostMapping("/create")
    public ResponseEntity<CashBookResponse> create(@RequestBody CashBookRequest cashBookRequest) {
        try {
            return new ResponseEntity<>(new CashBookResponse(cashBookService.createCashBook(cashBookRequest),
                    "Cashbook created successfully"), HttpStatus.CREATED);
        } catch (CashBookServiceException e) {
            return new ResponseEntity<>(new CashBookResponse(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ListCashBookResponse> getAll() {
        try {
            return new ResponseEntity<>(new ListCashBookResponse(cashBookService.getAllCashBook(), "Cashbook created "
                    + "successfully"), HttpStatus.CREATED);
        } catch (CashBookServiceException e) {
            return new ResponseEntity<>(new ListCashBookResponse(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<CashBookResponse> getCashBook(@PathVariable String name) {
        try {
            return new ResponseEntity<>(new CashBookResponse(cashBookService.getCashBook(new CashBookRequest(name)),
                    ""), HttpStatus.FOUND);
        } catch (CashBookServiceException e) {
            return new ResponseEntity<>(new CashBookResponse(null, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}

