package com.example.shopall_challenge.service;

import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.Usuario;
import com.example.shopall_challenge.model.Vendedor;
import com.example.shopall_challenge.repository.SellerRepository;
import com.example.shopall_challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
