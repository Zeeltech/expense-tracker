package com.zeel.expensetracker.expensetrackerbackend.auth.payload.cashbook;

import com.zeel.expensetracker.expensetrackerbackend.models.CashBook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CashBookResponse {
    private CashBook cashBook;
    private String message;
}
