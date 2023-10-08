package com.example.shopall_challenge.repository;

import com.example.shopall_challenge.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventario, Long>{
}
