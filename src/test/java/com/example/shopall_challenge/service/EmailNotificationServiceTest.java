package com.example.shopall_challenge.service;

import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.EmailNotificacion;
import com.example.shopall_challenge.repository.EmailNotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class EmailNotificationServiceTest {
    @Mock
    private EmailNotificationRepository repository;

    @InjectMocks
    private EmailNotificationService service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetNotifications() {
        // Simula el comportamiento del repositorio
        List<EmailNotificacion> notifications = new ArrayList<>();
        when(repository.findAll()).thenReturn(notifications);

        // Llama al método del servicio
        GenericResponse response = service.getNotifications();

        // Verifica el resultado
        assertEquals(200, response.getCode());
        assertEquals("Correct", response.getMessage());
        assertEquals(notifications, response.getData());
    }

    @Test
    void testGetNotificationById_NotFound() {
        // Simula el comportamiento del repositorio
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        // Llama al método del servicio
        GenericResponse response = service.getNotificationById(1L);

        // Verifica el resultado
        assertEquals(409, response.getCode());
        assertEquals("EmailNotification not found", response.getMessage());
        assertEquals(new ArrayList<>(), response.getData());
    }

    @Test
    void testGetNotificationById_Found() {
        // Simula el comportamiento del repositorio
        EmailNotificacion mockNotification = new EmailNotificacion();
        when(repository.findById(anyLong())).thenReturn(Optional.of(mockNotification));

        // Llama al método del servicio
        GenericResponse response = service.getNotificationById(1L);

        // Verifica el resultado
        assertEquals(201, response.getCode());
        assertEquals("EmailNotification found", response.getMessage());
        assertEquals(List.of(mockNotification), response.getData());
    }
}