package com.example.shopall_challenge.service;
import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.Inventario;
import com.example.shopall_challenge.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class InventoryServiceTest {

    @Mock
    private InventoryRepository repository;

    @InjectMocks
    private InventoryService service;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testGetInventory() {
        // Simula el comportamiento del repositorio
        List<Inventario> inventories = new ArrayList<>();
        when(repository.findAll()).thenReturn(inventories);

        // Llama al método del servicio
        GenericResponse response = service.getInventory();

        // Verifica el resultado
        assertEquals(HttpStatus.OK.value(), response.getCode());
        assertEquals("Correct", response.getMessage());
        assertEquals(inventories, response.getData());
    }

    @Test
    void testGetInventoryId_NotFound() {
        // Simula el comportamiento del repositorio
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        // Llama al método del servicio
        GenericResponse response = service.getInventoryId(1L);

        // Verifica el resultado
        assertEquals(HttpStatus.CONFLICT.value(), response.getCode());
        assertEquals("Inventario not found", response.getMessage());
        assertEquals(new ArrayList<>(), response.getData());
    }

    @Test
    void testGetInventoryId_Found() {
        // Simula el comportamiento del repositorio
        Inventario mockInventory = new Inventario();
        when(repository.findById(anyLong())).thenReturn(Optional.of(mockInventory));

        // Llama al método del servicio
        GenericResponse response = service.getInventoryId(1L);

        // Verifica el resultado
        assertEquals(HttpStatus.CREATED.value(), response.getCode());
        assertEquals("Inventario found", response.getMessage());
        assertEquals(List.of(mockInventory), response.getData());
    }
}