package com.example.shopall_challenge.repository;

import com.example.shopall_challenge.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario, Long>{

    Optional<Usuario> findOneByEmail(String email);
}
