package com.zeel.expensetracker.expensetrackerbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "_cashbooks")
public class CashBook {
    @Id
    @GeneratedValue
    @Column(name = "cashbook_id")
    private Integer cashBookId;

    @OneToMany(mappedBy = "cashBook", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id")
    private User user;
}
