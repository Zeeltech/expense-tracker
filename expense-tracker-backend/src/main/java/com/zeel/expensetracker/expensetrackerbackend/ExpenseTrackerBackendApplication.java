package com.zeel.expensetracker.expensetrackerbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ExpenseTrackerBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpenseTrackerBackendApplication.class, args);
    }

}
