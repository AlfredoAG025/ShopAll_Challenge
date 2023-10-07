package com.example.shopall_challenge.repository;

import com.example.shopall_challenge.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Producto, Long>{

}
