package com.example.shopall_challenge.service;

import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.Inventory;
import com.example.shopall_challenge.model.Producto;
import com.example.shopall_challenge.model.ShoppingCart;
import com.example.shopall_challenge.repository.ProductRepository;
import com.example.shopall_challenge.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {
    private ShoppingCartRepository repository;
    private final ProductRepository repositoryProducto;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository repository,ProductRepository repositoryProducto){
        this.repository = repository;
        this.repositoryProducto= repositoryProducto;
    }

    public GenericResponse getShoppingCart(){
        List<Carrito> shoppingCarts = this.repository.findAll();
        GenericResponse response =  new GenericResponse(200, "Correct", shoppingCarts);
        return response;
    }

    public GenericResponse getShoppingCartById(@PathVariable Long carrito_id){
        List<Carrito> shoppingCarts = new ArrayList<>();
        Carrito shoppingCart;
        Optional<Carrito> carrito_opt = repository.findById(carrito_id);
        GenericResponse response = null;

        if (carrito_opt.isPresent()){
            shoppingCart = carrito_opt.get();
            shoppingCarts.add(shoppingCart);
            response =  new GenericResponse(201, "Shopping Cart found", shoppingCarts);
        } else {
            response =  new GenericResponse(409, "Shopping Cart not found", shoppingCarts);
        }

        return response;
    }

    public GenericResponse updateShoppingCart(@PathVariable Long carrito_id, @RequestBody Carrito body){
        List<Carrito> shoppingCarts = new ArrayList<>();
        Carrito shoppingCart;
        Optional<Carrito> user_opt = repository.findById(carrito_id);
        GenericResponse response = null;

        if (user_opt.isPresent()){
            shoppingCart = body;
            shoppingCarts.add(shoppingCart);
            repository.save(shoppingCart);
            response =  new GenericResponse(201, "Shopping Cart Updated!", shoppingCarts);
        } else {
            response =  new GenericResponse(409, "Shopping Cart not found", shoppingCarts);
        }

        return response;
    }
    public GenericResponse addShoppingCart(@RequestBody ShoppingCart body){
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        //Monto total
        Long idProducto= body.getProducto().getProducto_id();
        int cantidad= body.getCantidad();
        body.setMonto_total(monto(idProducto,cantidad));
        shoppingCarts.add(body);
        GenericResponse response;
        try{
            repository.save(body);
            response =  new GenericResponse(201, "Accepted", shoppingCarts);
        } catch (Exception e){
            response =  new GenericResponse(409, "Conflict", shoppingCarts);
        }
        return response;
    }

    public float monto(@RequestBody Long id,Integer cantidad){
        Producto producto = repositoryProducto.findById(id).get();
        float monto_total = (float) (cantidad * producto.getPrecio());
        return monto_total;
    }

    public GenericResponse deleteShoppingCart(@PathVariable Long carrito_id){
        List<Carrito> shoppingCarts = new ArrayList<>();
        Carrito shoppingCart;
        Optional<Carrito> user_opt = repository.findById(carrito_id);
        GenericResponse response = null;

        if (user_opt.isPresent()){
            shoppingCart = user_opt.get();
            shoppingCarts.add(shoppingCart);
            repository.deleteById(carrito_id);
            response =  new GenericResponse(201, "Shopping Cart with id: " + carrito_id + " deleted", shoppingCarts);
        } else {
            response =  new GenericResponse(409, "Shopping Cart not found", shoppingCarts);
        }

        return response;
    }
}