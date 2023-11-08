package com.example.shopall_challenge.controller;

import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.Usuario;
import com.example.shopall_challenge.model.Vendedor;
import com.example.shopall_challenge.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/seller")
@PreAuthorize("hasRole('ADMIN')")
public class SellerController {
    private final SellerService service;

    @Autowired
    public SellerController(SellerService service){
        this.service = service;
    }

    @GetMapping
    public GenericResponse getSellers(){
        return this.service.getSellers();
    }

    @GetMapping("{seller_id}")
    public GenericResponse getUserById(@PathVariable("seller_id") Long seller_id){
        return this.service.getSeller(seller_id);
    }

    @PostMapping("/add")
    public GenericResponse addUser(@RequestBody Vendedor body){
        return this.service.addSeller(body);
    }

    @PutMapping("/update/{seller_id}")
    public GenericResponse updateUser(@PathVariable("seller_id") Long seller_id, @RequestBody Vendedor body){
        return this.service.updateSeller(seller_id, body);
    }

    @DeleteMapping("/delete/{seller_id}")
    public GenericResponse deleteUser(@PathVariable("seller_id") Long seller_id){
        return this.service.deleteSeller(seller_id);
    }

}
