package com.example.shopall_challenge.service;

import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.Tienda;
import com.example.shopall_challenge.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShopService{
    private ShopRepository repository;

    @Autowired
    public ShopService(ShopRepository repository){
        this.repository = repository;
    }

    public GenericResponse getShops(){
        List<Tienda> shops = this.repository.findAll();
        GenericResponse response =  new GenericResponse(200, "Correct", shops);
        return response;
    }

    public GenericResponse getShopById(@PathVariable Long shop_id){
        List<Tienda> shops = new ArrayList<>();
        Tienda shop;
        Optional<Tienda> shop_opt = repository.findById(shop_id);
        GenericResponse response = null;

        if (shop_opt.isPresent()){
            shop = shop_opt.get();
            shops.add(shop);
            response =  new GenericResponse(201, "Shop found", shops);
        } else {
            response =  new GenericResponse(409, "Shop not found", shops);
        }

        return response;
    }

    public GenericResponse updateShop(@PathVariable Long shop_id, @RequestBody Tienda body){
        List<Tienda> shops = new ArrayList<>();
        Tienda shop;
        Optional<Tienda> user_opt = repository.findById(shop_id);
        GenericResponse response = null;

        if (user_opt.isPresent()){
            shop = body;
            shops.add(shop);
            repository.save(shop);
            response =  new GenericResponse(201, "Shop Updated!", shops);
        } else {
            response =  new GenericResponse(409, "Shop not found", shops);
        }

        return response;
    }

    public GenericResponse addShop(@RequestBody Tienda body){
        List<Tienda> shops = new ArrayList<>();
        shops.add(body);
        GenericResponse response;
        try{
            repository.save(body);
            response =  new GenericResponse(201, "Accepted", shops);
        } catch (Exception e){
            response =  new GenericResponse(409, "Conflict", shops);
        }
        return response;
    }

    public GenericResponse deleteShop(@PathVariable Long user_id){
        List<Tienda> shops= new ArrayList<>();
        Tienda shop;
        Optional<Tienda> user_opt = repository.findById(user_id);
        GenericResponse response = null;

        if (user_opt.isPresent()){
            shop = user_opt.get();
            shops.add(shop);
            repository.deleteById(user_id);
            response =  new GenericResponse(201, "Shop with id: " + user_id + " deleted", shops);
        } else {
            response =  new GenericResponse(409, "Shop not found", shops);
        }

        return response;
    }
}
