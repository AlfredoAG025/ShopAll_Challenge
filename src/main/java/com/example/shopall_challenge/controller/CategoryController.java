package com.example.shopall_challenge.controller;


import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.Categoria;
import com.example.shopall_challenge.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    private CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }


    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public GenericResponse getCategories() {
        return this.service.getCategories();
    }


    @GetMapping("{category_id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public GenericResponse getCategoryById(@PathVariable("category_id") Long category_id) {
        return this.service.getCategoryById(category_id);
    }


    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public GenericResponse addCategory(@RequestBody Categoria body) {
        return this.service.addCategory(body);
    }


    @DeleteMapping("/delete/{category_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public GenericResponse deleteCategory(@PathVariable("category_id") Long category_id) {
        return this.service.deleteCategory(category_id);
    }


    @PutMapping("/update/{category_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public GenericResponse updateCategory(@PathVariable("category_id") Long category_id, @RequestBody Categoria body) {
        return this.service.updateCategory(category_id, body);
    }
}
