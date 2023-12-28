package com.zeel.expensetracker.expensetrackerbackend.auth.payload.cashbook;

import com.zeel.expensetracker.expensetrackerbackend.models.CashBook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListCashBookResponse {
    private List<CashBook> cashBooks;
    private String message;
}
