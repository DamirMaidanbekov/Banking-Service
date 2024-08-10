package com.example.bankingservice.controller;

import com.example.bankingservice.entity.Transaction;
import com.example.bankingservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction savedTransaction = transactionService.saveTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction);
    }

    @GetMapping("/exceeded-limit")
    public ResponseEntity<List<Transaction>> getExceededTransactions(@RequestParam BigDecimal limit) {
        List<Transaction> transactions = transactionService.getTransactionsExceedingLimit(limit);
        return ResponseEntity.ok(transactions);
    }
}
