package com.example.shopall_challenge.service;

import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.Inventory;
import com.example.shopall_challenge.model.ShoppingCart;
import com.example.shopall_challenge.repository.InventoryRepository;
import com.example.shopall_challenge.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    private InventoryRepository repository;

    @Autowired
    public InventoryService(InventoryRepository repository){
        this.repository = repository;
    }

    public GenericResponse getInventory(){
        List<Inventory> inventories = this.repository.findAll();
        GenericResponse response =  new GenericResponse(200, "Correct", inventories);
        return response;
    }

    public GenericResponse getInventoryId(@PathVariable Long inventory_id){
        List<Inventory> inventories = new ArrayList<>();
        Inventory inventory;
        Optional<Inventory> inventory_opt = repository.findById(inventory_id);
        GenericResponse response = null;

        if (inventory_opt.isPresent()){
            inventory = inventory_opt.get();
            inventories.add(inventory);
            response =  new GenericResponse(201, "Inventory found", inventories);
        } else {
            response =  new GenericResponse(409, "Inventory not found", inventories);
        }

        return response;
    }

    public GenericResponse updateInventory(@PathVariable Long inventory_id, @RequestBody Inventory body){
        List<Inventory> inventories = new ArrayList<>();
        Inventory inventory;
        Optional<Inventory> inventory_opt = repository.findById(inventory_id);
        GenericResponse response = null;

        if (inventory_opt.isPresent()){
            inventory = body;
            inventories.add(inventory);
            repository.save(inventory);
            response =  new GenericResponse(201, "Inventory Updated!", inventories);
        } else {
            response =  new GenericResponse(409, "Inventory not found", inventories);
        }

        return response;
    }

    public GenericResponse addInventory(@RequestBody Inventory body){
        List<Inventory> inventories = new ArrayList<>();
        inventories.add(body);
        GenericResponse response;
        try{
            repository.save(body);
            response =  new GenericResponse(201, "Accepted", inventories);
        } catch (Exception e){
            response =  new GenericResponse(409, "Conflict", inventories);
        }
        return response;
    }

    public GenericResponse deleteInventory(@PathVariable Long inventory_id){
        List<Inventory> inventories = new ArrayList<>();
        Inventory inventory;
        Optional<Inventory> inventory_opt = repository.findById(inventory_id);
        GenericResponse response = null;

        if (inventory_opt.isPresent()){
            inventory = inventory_opt.get();
            inventories.add(inventory);
            repository.deleteById(inventory_id);
            response =  new GenericResponse(201, "Inventory with id: " + inventory_id + " deleted", inventories);
        } else {
            response =  new GenericResponse(409, "Inventory not found", inventories);
        }

        return response;
    }
}