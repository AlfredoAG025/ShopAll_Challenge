package com.example.shopall_challenge.service;


import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.Producto;
import com.example.shopall_challenge.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository){
        this.repository = repository;
    }

    public GenericResponse getProducts(){
        List<Producto> products = this.repository.findAll();
        GenericResponse response =  new GenericResponse(200, "Correct", products);
        return response;
    }

}
