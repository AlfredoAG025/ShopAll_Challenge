package com.example.shopall_challenge.repository;

import com.example.shopall_challenge.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaccion, Long>{
}