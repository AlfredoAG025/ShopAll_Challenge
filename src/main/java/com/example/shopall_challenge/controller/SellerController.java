package com.example.shopall_challenge.controller;

import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/seller")
public class SellerController {
    private final SellerService service;

    @Autowired
    public SellerController(SellerService service){
        this.service = service;
    }

    @GetMapping("/")
    public GenericResponse getSellers(){
        return this.service.getSellers();
    }

}
