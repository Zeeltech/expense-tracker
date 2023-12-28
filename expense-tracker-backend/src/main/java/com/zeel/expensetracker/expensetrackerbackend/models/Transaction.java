package com.zeel.expensetracker.expensetrackerbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "_transactions")
public class Transaction {
    @Id
    @GeneratedValue
    @Column(name = "transaction_id")
    private Integer transactionId;
    private Double amount;
    private String description;
    private Date date;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_cashbook_id")
    private CashBook cashBook;
}
