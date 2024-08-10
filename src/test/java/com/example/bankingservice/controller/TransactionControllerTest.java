package com.example.bankingservice.controller;

import com.example.bankingservice.entity.Transaction;
import com.example.bankingservice.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void testCreateTransaction() throws Exception {
        String transactionJson = "{\"accountFrom\":\"123\",\"accountTo\":\"456\",\"currencyShortname\":\"USD\",\"sum\":1500.00,\"expenseCategory\":\"product\",\"datetime\":\"" + LocalDateTime.now() + "\"}";

        mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transactionJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.sum").value(1500.00));
    }

    @Test
    public void testGetExceededTransactions() throws Exception {
        Transaction t = new Transaction();
        t.setAccountFrom("123");
        t.setAccountTo("456");
        t.setCurrencyShortname("USD");
        t.setSum(new BigDecimal("1500.00"));
        t.setExpenseCategory("product");
        t.setDatetime(LocalDateTime.now());

        transactionRepository.save(t);

        mockMvc.perform(get("/api/transactions/exceeded-limit")
                        .param("limit", "1000.00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].sum").value(1500.00));
    }
}
