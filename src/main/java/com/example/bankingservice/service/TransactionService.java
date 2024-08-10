package com.example.bankingservice.service;

import com.example.bankingservice.entity.Transaction;
import com.example.bankingservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsExceedingLimit(BigDecimal limit) {
        return transactionRepository.findAll().stream()
                .filter(transaction -> transaction.getSum().compareTo(limit) > 0)
                .toList();
    }
}
