package com.example.shopall_challenge.service;

import com.example.shopall_challenge.model.Categoria;
import com.example.shopall_challenge.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CategoryServiceTest {

    @Mock
    private CategoryRepository repository;
    @InjectMocks
    private  CategoryService categoryService;

    private Categoria categoria;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        categoria =  new Categoria();
        categoria.setNombre("CategoryLAO");
        categoria.setCategoria_id(new Long(11));


    }

    @Test
    void getCategories() {
        when(repository.findAll()).thenReturn(Arrays.asList(categoria));
        assertNotNull(categoryService.getCategories());
    }
}