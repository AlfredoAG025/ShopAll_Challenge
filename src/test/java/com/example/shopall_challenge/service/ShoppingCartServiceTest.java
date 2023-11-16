package com.example.shopall_challenge.service;

import com.example.shopall_challenge.model.Carrito;
import com.example.shopall_challenge.model.Producto;
import com.example.shopall_challenge.model.Usuario;
import com.example.shopall_challenge.repository.ShoppingCartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ShoppingCartServiceTest {

    @Mock
    private ShoppingCartRepository shoppingCartRepository;
    @InjectMocks
    private ShoppingCartService shoppingCartService;
    private Carrito carrito;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        carrito = new Carrito();
        carrito.setCarrito_id(12L);
        carrito.setCantidad(10);
        carrito.setProducto(new Producto());
        carrito.setUsuario(new Usuario());

    }

    @Test
    void getShops() {
        when(shoppingCartRepository.findAll()).thenReturn(Arrays.asList(carrito));
        assertNotNull(shoppingCartService.getShoppingCart());
    }
}