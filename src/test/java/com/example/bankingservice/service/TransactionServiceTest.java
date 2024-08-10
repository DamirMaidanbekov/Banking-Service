package com.example.bankingservice.service;

import com.example.bankingservice.entity.Transaction;
import com.example.bankingservice.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTransactionsExceedingLimit() {
        Transaction t1 = new Transaction();
        t1.setId(1L);
        t1.setAccountFrom("123");
        t1.setAccountTo("456");
        t1.setCurrencyShortname("USD");
        t1.setSum(new BigDecimal("1500.00"));
        t1.setExpenseCategory("product");
        t1.setDatetime(LocalDateTime.now());

        Transaction t2 = new Transaction();
        t2.setId(2L);
        t2.setAccountFrom("123");
        t2.setAccountTo("456");
        t2.setCurrencyShortname("USD");
        t2.setSum(new BigDecimal("500.00"));
        t2.setExpenseCategory("service");
        t2.setDatetime(LocalDateTime.now());

        when(transactionRepository.findAll()).thenReturn(Arrays.asList(t1, t2));

        List<Transaction> transactions = transactionService.getTransactionsExceedingLimit(new BigDecimal("1000.00"));

        assertEquals(1, transactions.size());
        assertEquals(t1, transactions.get(0));
    }

    @Test
    public void testSaveTransaction() {
        Transaction t = new Transaction();
        t.setId(1L);
        t.setAccountFrom("123");
        t.setAccountTo("456");
        t.setCurrencyShortname("USD");
        t.setSum(new BigDecimal("1500.00"));
        t.setExpenseCategory("product");
        t.setDatetime(LocalDateTime.now());

        when(transactionRepository.save(t)).thenReturn(t);

        Transaction savedTransaction = transactionService.saveTransaction(t);

        assertEquals(t, savedTransaction);
    }
}
