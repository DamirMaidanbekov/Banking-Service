package com.example.bankingservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String accountFrom;

    @Column(nullable = false)
    private String accountTo;

    @Column(nullable = false)
    private String currencyShortname;

    @Column(nullable = false)
    private BigDecimal sum;

    @Column(nullable = false)
    private String expenseCategory;

    @Column(nullable = false)
    private LocalDateTime datetime;

}
