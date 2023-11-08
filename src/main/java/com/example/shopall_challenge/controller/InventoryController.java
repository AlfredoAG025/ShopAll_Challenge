package com.example.shopall_challenge.controller;

import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.Inventario;
import com.example.shopall_challenge.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/inventory")
@PreAuthorize("hasRole('ADMIN')")
public class InventoryController {
    private InventoryService service;

    @Autowired
    public InventoryController(InventoryService service){
        this.service = service;
    }

    @GetMapping
    public GenericResponse getInventory(){
        return this.service.getInventory();
    }

    @GetMapping("{inventario_id}")
    public GenericResponse getInventoryById(@PathVariable("inventario_id") Long inventory_id){
        return this.service.getInventoryId(inventory_id);
    }


    @PostMapping("/add")
    public GenericResponse addInventory(@RequestBody Inventario body){
        return this.service.addInventory(body);
    }

    @DeleteMapping("/delete/{inventory_id}")
    public GenericResponse deleteInventory(@PathVariable("inventory_id") Long inventory_id){
        return this.service.deleteInventory(inventory_id);
    }

    @PutMapping("/update/{inventory_id}")
    public GenericResponse updateInventory(@PathVariable("inventory_id") Long inventory_id, @RequestBody Inventario body){
        return this.service.updateInventory(inventory_id, body);
    }
}
