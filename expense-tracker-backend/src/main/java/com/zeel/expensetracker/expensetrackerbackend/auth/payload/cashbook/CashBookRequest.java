package com.zeel.expensetracker.expensetrackerbackend.auth.payload.cashbook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CashBookRequest {
    private String name;
}
