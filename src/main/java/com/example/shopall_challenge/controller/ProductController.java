package com.example.shopall_challenge.controller;

import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.Producto;
import com.example.shopall_challenge.model.Usuario;
import com.example.shopall_challenge.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    private ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public GenericResponse getProducts() {
        return this.service.getProducts();
    }

    @GetMapping("{product_id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public GenericResponse getProductById(@PathVariable("product_id") Long product_id) {
        return this.service.getProduct(product_id);
    }


    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public GenericResponse addProduct(@RequestBody Producto body) {
        return this.service.addProduct(body);
    }

    @DeleteMapping("/delete/{product_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public GenericResponse deleteProduct(@PathVariable("product_id") Long product_id){
        return this.service.deleteProduct(product_id);
    }

    @PutMapping("/update/{product_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public GenericResponse updateUser(@PathVariable("product_id") Long product_id, @RequestBody Producto body){
        return this.service.updateProduct(product_id, body);
    }
}
