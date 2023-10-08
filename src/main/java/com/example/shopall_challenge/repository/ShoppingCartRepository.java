package com.example.shopall_challenge.repository;

import com.example.shopall_challenge.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<Carrito, Long>{
}
