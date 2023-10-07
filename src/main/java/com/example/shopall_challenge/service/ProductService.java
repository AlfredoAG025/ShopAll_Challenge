package com.example.shopall_challenge.service;


import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.Producto;
import com.example.shopall_challenge.model.Producto;
import com.example.shopall_challenge.model.Usuario;
import com.example.shopall_challenge.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public GenericResponse getProduct(@PathVariable Long product_id){
        List<Producto> users = new ArrayList<>();
        Producto product;
        Optional<Producto> product_opt = repository.findById(product_id);
        GenericResponse response = null;

        if (product_opt.isPresent()){
            product = product_opt.get();
            users.add(product);
            response =  new GenericResponse(201, "Product found", users);
        } else {
            response =  new GenericResponse(409, "Product not found", users);
        }

        return response;
    }

    public GenericResponse addProduct(@RequestBody Producto body){
        List<Producto> products = new ArrayList<>();
        products.add(body);
        GenericResponse response;
        try{
            repository.save(body);
            response =  new GenericResponse(201, "Accepted", products);
        } catch (Exception e){
            response =  new GenericResponse(409, "Conflict", products);
        }
        return response;
    }

    public GenericResponse deleteProduct(@PathVariable Long product_id){
        List<Producto> products = new ArrayList<>();
        Producto product;
        Optional<Producto> product_opt = repository.findById(product_id);
        GenericResponse response = null;

        if (product_opt.isPresent()){
            product = product_opt.get();
            products.add(product);
            repository.deleteById(product_id);
            response =  new GenericResponse(201, "Product with id: " + product_id + " deleted", products);
        } else {
            response =  new GenericResponse(409, "Product not found", products);
        }

        return response;
    }

    public GenericResponse updateProduct(@PathVariable Long product_id, @RequestBody Producto body){
        List<Producto> products = new ArrayList<>();
        Producto product;
        Optional<Producto> product_opt = repository.findById(product_id);
        GenericResponse response = null;

        if (product_opt.isPresent()){
            product = body;
            products.add(product);
            repository.save(product);
            response =  new GenericResponse(201, "Product Updated!", products);
        } else {
            response =  new GenericResponse(409, "Product not found", products);
        }

        return response;
    }


}
