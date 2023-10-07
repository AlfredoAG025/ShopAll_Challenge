package com.example.shopall_challenge.repository;

import com.example.shopall_challenge.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Vendedor, Long> {
}
