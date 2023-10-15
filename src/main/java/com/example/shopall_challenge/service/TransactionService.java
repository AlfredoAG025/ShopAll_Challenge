package com.example.shopall_challenge.service;
import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.Transaccion;
import com.example.shopall_challenge.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private TransactionRepository repository;

    @Autowired
    public TransactionService(TransactionRepository repository){
        this.repository = repository;
    }

    public GenericResponse getTransaction(){
        List<Transaccion> transactions = this.repository.findAll();
        GenericResponse response =  new GenericResponse(200, "Correct", transactions);
        return response;
    }

    public GenericResponse getTransactionById(@PathVariable Long transaccion_id){
        List<Transaccion> transactions = new ArrayList<>();
        Transaccion transaction;
        Optional<Transaccion> transaccion_opt = repository.findById(transaccion_id);
        GenericResponse response = null;

        if (transaccion_opt.isPresent()){
            transaction = transaccion_opt.get();
            transactions.add(transaction);
            response =  new GenericResponse(201, "Transaccion found", transactions);
        } else {
            response =  new GenericResponse(409, "Transaccion not found", transactions);
        }

        return response;
    }

    public GenericResponse updateTransaction(@PathVariable Long transaccion_id, @RequestBody Transaccion body){
        List<Transaccion> transactions = new ArrayList<>();
        Transaccion transaction;
        Optional<Transaccion> transaction_opt = repository.findById(transaccion_id);
        GenericResponse response = null;

        if (transaction_opt.isPresent()){
            transaction = body;
            transactions.add(transaction);
            repository.save(transaction);
            response =  new GenericResponse(201, "Transaccion Updated!", transactions);
        } else {
            response =  new GenericResponse(409, "Transaccion not found", transactions);
        }

        return response;
    }

    public GenericResponse addTransaction(@RequestBody Transaccion body){
        List<Transaccion> transactions = new ArrayList<>();
        transactions.add(body);
        GenericResponse response;
        try{
            repository.save(body);
            response =  new GenericResponse(201, "Accepted", transactions);
        } catch (Exception e){
            response =  new GenericResponse(409, "Conflict", transactions);
        }
        return response;
    }

    public GenericResponse deleteTransaction(@PathVariable Long transaccion_id){
        List<Transaccion> transactions = new ArrayList<>();
        Transaccion transaction;
        Optional<Transaccion> transaction_opt = repository.findById(transaccion_id);
        GenericResponse response = null;

        if (transaction_opt.isPresent()){
            transaction = transaction_opt.get();
            transactions.add(transaction);
            repository.deleteById(transaccion_id);
            response =  new GenericResponse(201, "Shopping Cart with id: " + transaccion_id + " deleted", transactions);
        } else {
            response =  new GenericResponse(409, "Shopping Cart not found", transactions);
        }

        return response;
    }
}
