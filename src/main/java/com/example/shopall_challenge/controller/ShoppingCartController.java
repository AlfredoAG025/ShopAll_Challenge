package com.example.shopall_challenge.controller;
import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.Carrito;
import com.example.shopall_challenge.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/shoppingcart")
@PreAuthorize("hasRole('ADMIN')")
public class ShoppingCartController {
    private ShoppingCartService service;

    @Autowired
    public ShoppingCartController(ShoppingCartService service){
        this.service = service;
    }

    @GetMapping
    public GenericResponse getShoppingCart(){
        return this.service.getShoppingCart();
    }

    @GetMapping("{carrito_id}")
    public GenericResponse getShoppingCartById(@PathVariable("carrito_id") Long category_id){
        return this.service.getShoppingCartById(category_id);
    }


    @PostMapping("/add")
    public GenericResponse  addShoppingCart(@RequestBody Carrito body){
        return this.service.addShoppingCart(body);
    }

    @DeleteMapping("/delete/{carrito_id}")
    public GenericResponse deleteShoppingCart(@PathVariable("carrito_id") Long carrito_id){
        return this.service.deleteShoppingCart(carrito_id);
    }

    @PutMapping("/update/{carrito_id}")
    public GenericResponse updateShoppingCart(@PathVariable("carrito_id") Long carrito_id, @RequestBody Carrito body){
        return this.service.updateShoppingCart(carrito_id, body);
    }
}