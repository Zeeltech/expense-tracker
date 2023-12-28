package com.zeel.expensetracker.expensetrackerbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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

    private String name;

    @OneToMany(mappedBy = "cashBook", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Transaction> transactions;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id")
    private User user;
}
