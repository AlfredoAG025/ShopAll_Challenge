package com.example.shopall_challenge.repository;

import com.example.shopall_challenge.model.Rol;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RoleRepository extends JpaRepository<Rol, Long>{
}

