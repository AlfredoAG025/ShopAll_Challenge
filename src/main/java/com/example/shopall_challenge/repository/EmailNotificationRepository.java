package com.example.shopall_challenge.repository;

import com.example.shopall_challenge.model.EmailNotificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailNotificationRepository extends JpaRepository<EmailNotificacion, Long> {
}
