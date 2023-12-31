package com.example.shopall_challenge.controller;

import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.Transaccion;
import com.example.shopall_challenge.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/transaction")
public class TransactionController {
    private TransactionService service;

    @Autowired
    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public GenericResponse getTransaction() {
        return this.service.getTransaction();
    }

    @GetMapping("{transaccion_id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public GenericResponse getTransactionById(@PathVariable("transaction_id") Long transaction_id) {
        return this.service.getTransactionById(transaction_id);
    }


    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public GenericResponse addTransaction(@RequestBody Transaccion body) {
        return this.service.addTransaction(body);
    }

    @DeleteMapping("/delete/{transaction_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public GenericResponse deleteTransaction(@PathVariable("transaction_id") Long transaction_id) {
        return this.service.deleteTransaction(transaction_id);
    }

    @PutMapping("/update/{transaction_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public GenericResponse updateTransaction(@PathVariable("transaction_id") Long transaction_id, @RequestBody Transaccion body) {
        return this.service.updateTransaction(transaction_id, body);
    }
}

