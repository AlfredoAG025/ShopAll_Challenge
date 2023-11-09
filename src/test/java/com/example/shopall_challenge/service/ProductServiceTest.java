package com.example.shopall_challenge.service;

import com.example.shopall_challenge.model.Categoria;
import com.example.shopall_challenge.model.Producto;
import com.example.shopall_challenge.model.Vendedor;
import com.example.shopall_challenge.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService productService;

    private Producto producto;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        producto =  new Producto();
        producto.setProducto_id(new Long(1));
        producto.setCategoria(new Categoria());
        producto.setPrecio(new Double(1000));
        producto.setNombre("LaoProduct");
        producto.setVendedor(new Vendedor());
    }

    @Test
    void getProducts() {
        when(repository.findAll()).thenReturn(Arrays.asList(producto));
        assertNotNull(productService.getProducts());
    }
}