package com.example.shopall_challenge.repository;
import com.example.shopall_challenge.model.Privilegio;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilegio, Long>{
}
