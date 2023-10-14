package com.example.shopall_challenge.controller;

import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.Tienda;
import com.example.shopall_challenge.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
public class ShopController {
    private ShopService service;

    @Autowired
    public ShopController(ShopService service){
        this.service = service;
    }

    @GetMapping
    public GenericResponse getShops(){
        return this.service.getShops();
    }

    @GetMapping("{shop_id}")
    public GenericResponse getShopById(@PathVariable("shop_id") Long shop_id){
        return this.service.getShopById(shop_id);
    }


    @PostMapping("/add")
    public GenericResponse addShop(@RequestBody Tienda body){
        return this.service.addShop(body);
    }

    @DeleteMapping("/delete/{shop_id}")
    public GenericResponse deleteShop(@PathVariable("shop_id") Long shop_id){
        return this.service.deleteShop(shop_id);
    }

    @PutMapping("/update/{shop_id}")
    public GenericResponse updateShop(@PathVariable("shop_id") Long shop_id, @RequestBody Tienda body){
        return this.service.updateShop(shop_id, body);
    }
}
