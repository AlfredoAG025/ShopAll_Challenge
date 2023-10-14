package com.example.shopall_challenge.repository;

import com.example.shopall_challenge.model.Tienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Tienda, Long> {
}
