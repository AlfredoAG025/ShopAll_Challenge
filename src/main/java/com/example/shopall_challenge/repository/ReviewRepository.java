package com.example.shopall_challenge.repository;

import org.springframework.stereotype.Repository;
import com.example.shopall_challenge.model.Resena;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ReviewRepository extends JpaRepository<Resena, Long>{
}
