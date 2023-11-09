package com.example.shopall_challenge.service;

import com.example.shopall_challenge.model.Tienda;
import com.example.shopall_challenge.model.Vendedor;
import com.example.shopall_challenge.repository.ShopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ShopServiceTest {
@Mock
private ShopRepository shopRepository;
@InjectMocks
private ShopService shopService;
private Tienda tienda;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        tienda=new Tienda();
        tienda.setTienda_id(8L);
        tienda.setNombre_tienda("Tiendalao");
        tienda.setDescripcion("Tienda de Lao chida");
        tienda.setVendedor(new Vendedor());

    }

    @Test
    void getShops() {
        when(shopRepository.findAll()).thenReturn(Arrays.asList(tienda));
        assertNotNull(shopService.getShops());
    }
}