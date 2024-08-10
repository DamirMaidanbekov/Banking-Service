package com.example.bankingservice.repository;

import com.example.bankingservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountFrom(String accountFrom);
}
