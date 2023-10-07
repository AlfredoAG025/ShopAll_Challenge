package com.example.shopall_challenge.service;

import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.Usuario;
import com.example.shopall_challenge.model.Vendedor;
import com.example.shopall_challenge.repository.SellerRepository;
import com.example.shopall_challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SellerService {
    private final SellerRepository repository;

    @Autowired
    public SellerService(SellerRepository repository){
        this.repository = repository;
    }

    public GenericResponse getSellers(){
        List<Vendedor> sellers = this.repository.findAll();
        GenericResponse response =  new GenericResponse(200, "Correct", sellers);
        return response;
    }

    public GenericResponse getSeller(@PathVariable Long seller_id){
        List<Vendedor> sellers = new ArrayList<>();
        Vendedor seller;
        Optional<Vendedor> seller_opt = repository.findById(seller_id);
        GenericResponse response = null;

        if (seller_opt.isPresent()){
            seller = seller_opt.get();
            sellers.add(seller);
            response =  new GenericResponse(201, "User found", sellers);
        } else {
            response =  new GenericResponse(409, "User not found", sellers);
        }

        return response;
    }

    public GenericResponse addSeller(@RequestBody Vendedor body){
        List<Vendedor> sellers = new ArrayList<>();
        sellers.add(body);

        GenericResponse response;
        try{
            repository.save(body);
            response =  new GenericResponse(201, "Accepted", sellers);
        } catch (Exception e){
            response =  new GenericResponse(409, "Conflict", sellers);
        }
        return response;
    }

    public GenericResponse updateSeller(@PathVariable Long seller_id, @RequestBody Vendedor body){
        List<Vendedor> sellers = new ArrayList<>();
        Vendedor seller;
        Optional<Vendedor> seller_opt = repository.findById(seller_id);
        GenericResponse response = null;

        if (seller_opt.isPresent()){
            seller = body;
            sellers.add(seller);
            repository.save(seller);
            response =  new GenericResponse(201, "Seller Updated!", sellers);
        } else {
            response =  new GenericResponse(409, "Seller not found", sellers);
        }

        return response;
    }

    public GenericResponse deleteSeller(@PathVariable Long seller_id){
        List<Vendedor> sellers = new ArrayList<>();
        Vendedor seller;
        Optional<Vendedor> seller_opt = repository.findById(seller_id);
        GenericResponse response = null;

        if (seller_opt.isPresent()){
            seller = seller_opt.get();
            sellers.add(seller);
            repository.deleteById(seller_id);
            response =  new GenericResponse(201, "Seller with id: " + seller_id + " deleted", sellers);
        } else {
            response =  new GenericResponse(409, "Seller not found", sellers);
        }

        return response;
    }



}
